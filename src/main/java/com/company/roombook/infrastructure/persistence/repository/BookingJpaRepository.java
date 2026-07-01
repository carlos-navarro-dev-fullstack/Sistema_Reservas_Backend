package com.company.roombook.infrastructure.persistence.repository;

import com.company.roombook.infrastructure.persistence.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingJpaRepository
        extends JpaRepository<BookingEntity, Long> {

    List<BookingEntity> findByRoomId(Long roomId);

    List<BookingEntity> findByUserId(Long userId);
}