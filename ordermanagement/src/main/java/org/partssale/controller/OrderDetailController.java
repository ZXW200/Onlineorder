package org.partssale.controller;

import org.partssale.domain.dto.OrderDetail;
import org.partssale.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        return ResponseEntity.ok(orderDetailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(orderDetailService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.create(orderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetail);
    }

    @PutMapping
    public ResponseEntity<Void> updateOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.update(orderDetail);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.delete(id);
        return ResponseEntity.ok().build();
    }
}

