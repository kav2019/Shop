package ru.kovshov.shop.shop.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.kovshov.shop.shop.models.Burger;
import ru.kovshov.shop.shop.models.Order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository{
    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderBurgerInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("order_burger")
                .usingGeneratedKeyColumns("id");
        this.orderBurgerInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("burger_order_burger");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Burger> burgers = order.getDesignBurger();
        for (Burger burger : burgers) {
            saveBurgerToOrder(burger, orderId);
        }
        return order;
    }

    private long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());
        long orderId =orderInserter
                .executeAndReturnKey(values)
                .longValue();
        return orderId;
    }

    private void saveBurgerToOrder(Burger burger, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("burgerOrder", orderId);
        values.put("burger", burger.getId());
        orderBurgerInserter.execute(values);
    }

}
