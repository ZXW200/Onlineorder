package org.partssale.mapper;

import org.apache.ibatis.annotations.*;
import org.partssale.domain.dto.Reservation;

import java.util.List;

@Mapper
public interface ReservationMapper {

    List<Reservation> findAll();

    Reservation findById(Long id);


    int insert(Reservation reservation);

    void update(Reservation reservation);

    void delete(Long id);
}
