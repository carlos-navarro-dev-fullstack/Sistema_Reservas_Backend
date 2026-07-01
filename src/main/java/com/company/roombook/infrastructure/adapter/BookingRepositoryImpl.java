package com.company.roombook.infrastructure.adapter;

import com.company.roombook.domain.model.Booking;
import com.company.roombook.domain.repository.BookingRepository;
import com.company.roombook.infrastructure.persistence.entity.BookingEntity;
import com.company.roombook.infrastructure.persistence.repository.BookingJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    private final BookingJpaRepository jpaRepository;

    public BookingRepositoryImpl(BookingJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Booking save(Booking booking) {
        BookingEntity entity = toEntity(booking);
        BookingEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Booking> findByRoomId(Long roomId) {
        return jpaRepository.findByRoomId(roomId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Booking> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private BookingEntity toEntity(Booking b) {
        BookingEntity e = new BookingEntity();
        e.setId(b.getId());
        e.setRoomId(b.getRoomId());
        e.setUserId(b.getUserId());
        e.setStartTime(b.getStartTime());
        e.setEndTime(b.getEndTime());
        e.setStatus(b.getStatus());
        return e;
    }

    private Booking toDomain(BookingEntity e) {
        return new Booking(
                e.getId(),
                e.getRoomId(),
                e.getUserId(),
                e.getStartTime(),
                e.getEndTime(),
                e.getStatus()
        );
    }
}