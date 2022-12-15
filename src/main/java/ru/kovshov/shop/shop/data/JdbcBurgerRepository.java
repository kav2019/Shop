package ru.kovshov.shop.shop.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.kovshov.shop.shop.models.Burger;
import ru.kovshov.shop.shop.models.Ingredient;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcBurgerRepository implements BurgerRepsitory{

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcBurgerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }


    @Override
    public Burger save(Burger burger) {
        long burgerId = saveBurgerInfo(burger);
        burger.setId(burgerId);
        for (Ingredient ingredient : burger.getIngredients()) {
            saveIngredientToTaco(ingredient, burgerId);
        }
        return burger;
    }

    private long saveBurgerInfo(Burger burger) {
        burger.setCreatedAt(new Date());
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "insert into Burger (name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(
                Arrays.asList(
                        burger.getName(),
                        new Timestamp(burger.getCreatedAt().getTime()))
        );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long burgerId) {
        jdbc.update(
                "insert into burger_ingredient (burger_id, ingredient_id) " +
                        "values (?, ?)",
                burgerId, ingredient.getId()
        );
    }
}
