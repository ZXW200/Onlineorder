package org.partssale.service;


import org.partssale.domain.dto.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    void create(Order order);
    void update(Order order);
    void delete(Long id);
}
