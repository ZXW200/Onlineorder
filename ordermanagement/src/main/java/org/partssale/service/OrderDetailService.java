package org.partssale.service;


import org.partssale.domain.dto.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    OrderDetail findById(Long id);
    void create(OrderDetail orderDetail);
    void update(OrderDetail orderDetail);
    void delete(Long id);

    List<OrderDetail> getDetail(Long id);
}

