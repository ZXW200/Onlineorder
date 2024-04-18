package org.partssale.service.impl;

import org.partssale.domain.dto.Reservation;
import org.partssale.mapper.ReservationMapper;
import org.partssale.service.ReservationService;
import org.partssale.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public List<Reservation> findAll() {
//        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<>();
//        return reservationMapper.selectList(queryWrapper)
        return reservationMapper.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationMapper.findById(id);
    }

    @Override
    public void create(Reservation reservation) {
        reservationMapper.insert(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(reservation.getReservationTime());
        reservation.setReservationTime(DateUtils.parseDate(DateUtils.dateTimeNow(format)));
        reservationMapper.update(reservation);
    }

    @Override
    public void delete(Long id) {
        reservationMapper.delete(id);
    }
}
