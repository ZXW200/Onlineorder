package org.partssale.service.impl;


import org.partssale.domain.dto.OrderDetail;
import org.partssale.mapper.OrderDetailMapper;
import org.partssale.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailMapper.findAll();
    }

    @Override
    public OrderDetail findById(Long id) {
        return orderDetailMapper.findById(id);
    }

    @Override
    public void create(OrderDetail orderDetail) {
        orderDetailMapper.insert(orderDetail);
    }

    @Override
    public void update(OrderDetail orderDetail) {
        orderDetailMapper.update(orderDetail);
    }

    @Override
    public void delete(Long id) {
        orderDetailMapper.delete(id);
    }

    @Override
    public List<OrderDetail> getDetail(Long id) {
        return orderDetailMapper.getDetail(id);
    }
}

