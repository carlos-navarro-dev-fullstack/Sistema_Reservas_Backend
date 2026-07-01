package com.company.roombook.infrastructure.persistence.repository;

import com.company.roombook.infrastructure.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository
        extends JpaRepository<RoomEntity, Long> {
}