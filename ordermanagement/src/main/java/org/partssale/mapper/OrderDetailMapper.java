package org.partssale.mapper;

import org.apache.ibatis.annotations.*;
import org.partssale.domain.dto.OrderDetail;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    List<OrderDetail> findAll();

    @Select("SELECT * FROM order_details WHERE id = #{id}")
    OrderDetail findById(Long id);

    @Select("SELECT * FROM order_details WHERE order_id = #{id}")
    List<OrderDetail> getDetail(Long id);

    @Insert("INSERT INTO order_details (order_id, menu_item_id, quantity, price) VALUES (#{orderId}, #{menuItemId}, #{quantity}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(OrderDetail orderDetail);

    void update(OrderDetail orderDetail);

    @Delete("DELETE FROM order_details WHERE id = #{id}")
    void delete(Long id);
}
