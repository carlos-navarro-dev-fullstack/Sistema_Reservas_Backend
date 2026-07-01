package com.company.roombook.domain.repository;

import com.company.roombook.domain.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {

    Booking save(Booking booking);

    Optional<Booking> findById(Long id);

    List<Booking> findAll();

    List<Booking> findByRoomId(Long roomId);

    List<Booking> findByUserId(Long userId);

    void deleteById(Long id);
}