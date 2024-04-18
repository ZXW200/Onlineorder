package org.partssale.mapper;

import org.apache.ibatis.annotations.*;
import org.partssale.domain.dto.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> findAll();

    Order findById(Long id);

    @Insert("INSERT INTO orders (user_id, table_id, order_time, user_name,status) VALUES (#{userId}, #{tableId}, #{orderTime}, #{userName},#{status})")
    void insert(Order order);

    void update(Order order);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    void delete(Long id);
}
