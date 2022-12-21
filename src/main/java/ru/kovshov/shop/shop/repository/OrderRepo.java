package ru.kovshov.shop.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kovshov.shop.shop.models.Order;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByZip(String deliveryZip);
    List<Order> readByZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    List<Order> findByNameAndCityIgnoreCase(String deliverytTo, String deliveryCity);
    List<Order> findByCityOrderByName(String city);

//    @Query("Oreder o where o.city = 'Moscow'")
//    List<Order> readOrderDeliveredToMoscow();
}
