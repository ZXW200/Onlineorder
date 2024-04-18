package org.partssale.controller;

import org.partssale.common.JsonData;
import org.partssale.domain.dto.Order;
import org.partssale.service.OrderDetailService;
import org.partssale.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/getDetail/{id}")
    public JsonData getDetail(@PathVariable Long id) {
        return JsonData.buildSuccess(orderDetailService.getDetail(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        orderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping()
    public ResponseEntity<Void> updateOrder(@RequestBody Order order) {
        if (orderService.findById(Long.valueOf(order.getId())) != null) {
            order.setId(order.getId());
            orderService.update(order);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.findById(id) != null) {
            orderService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
