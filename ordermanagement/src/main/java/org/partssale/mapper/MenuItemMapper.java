package org.partssale.mapper;

import org.apache.ibatis.annotations.*;
import org.partssale.domain.dto.MenuItem;

import java.util.List;

@Mapper
public interface MenuItemMapper {

    @Select("SELECT * FROM menu_items")
    List<MenuItem> findAll();

    @Select("SELECT * FROM menu_items WHERE id = #{id}")
    MenuItem findById(Long id);

    @Insert("INSERT INTO menu_items(name, description, price, category, available,productImage) VALUES(#{name}, #{description}, #{price}, #{category}, #{available}, #{productImage})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(MenuItem menuItem);

    @Update("UPDATE menu_items SET name=#{name}, description=#{description}, price=#{price}, category=#{category}, available=#{available},productImage=#{productImage} WHERE id=#{id}")
    void update(MenuItem menuItem);

    @Delete("DELETE FROM menu_items WHERE id = #{id}")
    void delete(Long id);
}
