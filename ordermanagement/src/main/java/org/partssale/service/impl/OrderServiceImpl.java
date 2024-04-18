package org.partssale.service.impl;

import org.partssale.domain.dto.Order;
import org.partssale.mapper.OrderMapper;
import org.partssale.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderMapper.findById(id);
    }

    @Override
    public void create(Order order) {
        order.setOrderTime(new Timestamp(System.currentTimeMillis())); // Set current timestamp
        orderMapper.insert(order);
    }

    @Override
    public void update(Order order) {
        orderMapper.update(order);
    }

    @Override
    public void delete(Long id) {
        orderMapper.delete(id);
    }
}

