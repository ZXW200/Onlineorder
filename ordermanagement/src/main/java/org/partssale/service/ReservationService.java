package org.partssale.service;


import org.partssale.domain.dto.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    Reservation findById(Long id);
    void create(Reservation reservation);
    void update(Reservation reservation);
    void delete(Long id);
}
