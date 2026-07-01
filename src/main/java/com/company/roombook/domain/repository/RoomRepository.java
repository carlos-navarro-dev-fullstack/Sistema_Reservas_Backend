package com.company.roombook.domain.repository;

import com.company.roombook.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    Room save(Room room);

    Optional<Room> findById(Long id);

    List<Room> findAll();

    void deleteById(Long id);
}