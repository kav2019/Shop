package ru.kovshov.shop.shop.data;

import ru.kovshov.shop.shop.models.Order;

public interface OrderRepository {
    Order save(Order order);
}
