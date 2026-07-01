package com.company.roombook.application.service;

import com.company.roombook.application.usecase.GetAvailableRoomsUseCase;
import com.company.roombook.domain.model.Booking;
import com.company.roombook.domain.model.Room;
import com.company.roombook.domain.repository.BookingRepository;
import com.company.roombook.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAvailableRoomsUseCaseImpl implements GetAvailableRoomsUseCase {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public GetAvailableRoomsUseCaseImpl(RoomRepository roomRepository,
                                        BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Room> execute(LocalDateTime start, LocalDateTime end) {

        List<Room> rooms = roomRepository.findAll();

        return rooms.stream().filter(room -> {

            List<Booking> bookings = bookingRepository.findByRoomId(room.getId());

            boolean conflict = bookings.stream().anyMatch(b ->
                    start.isBefore(b.getEndTime()) &&
                            end.isAfter(b.getStartTime())
            );

            return !conflict;

        }).collect(Collectors.toList());
    }
}