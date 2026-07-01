package com.company.roombook.infrastructure.adapter;

import com.company.roombook.domain.model.Room;
import com.company.roombook.domain.repository.RoomRepository;
import com.company.roombook.infrastructure.persistence.entity.RoomEntity;
import com.company.roombook.infrastructure.persistence.repository.RoomJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private final RoomJpaRepository jpaRepository;

    public RoomRepositoryImpl(RoomJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Room save(Room room) {
        RoomEntity entity = toEntity(room);
        RoomEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Room> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public java.util.Optional<Room> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }

    private RoomEntity toEntity(Room r) {
        RoomEntity e = new RoomEntity();
        e.setId(r.getId());
        e.setName(r.getName());
        e.setCapacity(r.getCapacity());
        return e;
    }

    private Room toDomain(RoomEntity e) {
        return new Room(
                e.getId(),
                e.getName(),
                e.getCapacity()
        );
    }
}