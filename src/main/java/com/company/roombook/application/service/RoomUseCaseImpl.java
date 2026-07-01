package com.company.roombook.application.service;

import com.company.roombook.application.usecase.RoomUseCase;
import com.company.roombook.domain.model.Room;
import com.company.roombook.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomUseCaseImpl implements RoomUseCase {

    private final RoomRepository roomRepository;

    public RoomUseCaseImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room update(Long id, Room room) {

        Room existing = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Room updated = new Room(
                existing.getId(),
                room.getName(),
                room.getCapacity()
        );

        return roomRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}