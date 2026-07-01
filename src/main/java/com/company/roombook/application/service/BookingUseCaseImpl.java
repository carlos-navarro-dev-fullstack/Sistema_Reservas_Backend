package com.company.roombook.application.service;

import com.company.roombook.application.usecase.BookingUseCase;
import com.company.roombook.domain.model.Booking;
import com.company.roombook.domain.model.Room;
import com.company.roombook.domain.model.User;
import com.company.roombook.domain.repository.BookingRepository;
import com.company.roombook.domain.repository.RoomRepository;
import com.company.roombook.domain.repository.UserRepository;
import com.company.roombook.presentation.dto.response.BookingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingUseCaseImpl implements BookingUseCase {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public BookingUseCaseImpl(
            BookingRepository bookingRepository,
            RoomRepository roomRepository,
            UserRepository userRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    @Override
    public BookingResponse create(Booking booking) {

        Booking saved = bookingRepository.save(
                new Booking(
                        null,
                        booking.getRoomId(),
                        booking.getUserId(),
                        booking.getStartTime(),
                        booking.getEndTime(),
                        "CONFIRMED"
                )
        );

        return toResponse(saved);
    }

    // GET BY ID
    @Override
    public BookingResponse getById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return toResponse(booking);
    }

    // GET ALL
    @Override
    public List<BookingResponse> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // GET BY ROOM
    @Override
    public List<BookingResponse> getByRoomId(Long roomId) {
        return bookingRepository.findByRoomId(roomId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // GET BY USER
    @Override
    public List<BookingResponse> getByUserId(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // DELETE
    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    // =========================
    // MAPPER CENTRAL
    // =========================
    private BookingResponse toResponse(Booking booking) {

        String roomName = roomRepository.findById(booking.getRoomId())
                .map(Room::getName)
                .orElse("Unknown room");

        String userName = userRepository.findById(booking.getUserId())
                .map(User::getName)
                .orElse("Unknown user");

        return new BookingResponse(
                booking.getId(),
                booking.getRoomId(),
                roomName,
                booking.getUserId(),
                userName,
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStatus()
        );
    }
}