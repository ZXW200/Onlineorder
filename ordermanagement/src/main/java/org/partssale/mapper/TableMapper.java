package org.partssale.mapper;

import org.apache.ibatis.annotations.*;
import org.partssale.domain.dto.Table;

import java.util.List;

@Mapper
public interface TableMapper {
    // Other methods...

    List<Table> findAll();

    Table findById(Long id);
    @Insert("INSERT INTO tables (number, seating_capacity, status) VALUES (#{number}, #{seatingCapacity}, #{status})")
    void insert(Table table);

    @Update("UPDATE tables SET number = #{number}, seating_capacity = #{seatingCapacity}, status = #{status} WHERE id = #{id}")
    void update(Table table);


    @Delete("DELETE FROM tables WHERE id = #{id}")
    void delete(Long id);
}
