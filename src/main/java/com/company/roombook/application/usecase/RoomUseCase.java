package com.company.roombook.application.usecase;

import com.company.roombook.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomUseCase {

    Room create(Room room);

        Optional<Room> getById(Long id);

    List<Room> getAll();

    void delete(Long id);

    Room update(Long id, Room room);
}