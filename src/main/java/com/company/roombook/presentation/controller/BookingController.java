package com.company.roombook.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.company.roombook.application.usecase.BookingUseCase;
import com.company.roombook.domain.model.Booking;
import com.company.roombook.presentation.dto.request.CreateBookingRequest;
import com.company.roombook.presentation.dto.response.BookingResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    private final BookingUseCase bookingUseCase;

    public BookingController(BookingUseCase bookingUseCase) {
        this.bookingUseCase = bookingUseCase;
    }

    @PostMapping
    public BookingResponse create(@RequestBody CreateBookingRequest request) {

        log.info("Creando booking: roomId={}, userId={}, start={}, end={}",
                request.getRoomId(),
                request.getUserId(),
                request.getStartTime(),
                request.getEndTime()
        );

        Booking booking = new Booking(
                null,
                request.getRoomId(),
                request.getUserId(),
                request.getStartTime(),
                request.getEndTime(),
                "CONFIRMED"
        );

        BookingResponse response = bookingUseCase.create(booking);

        log.info("Booking creado correctamente con estado CONFIRMED");

        return response;
    }

    @GetMapping("/{id}")
    public BookingResponse getById(@PathVariable Long id) {
        log.info("Consultando booking por id={}", id);

        return bookingUseCase.getById(id);
    }

    @GetMapping
    public List<BookingResponse> getAll() {
        log.info("Listando todos los bookings");

        return bookingUseCase.getAll();
    }

    @GetMapping("/room/{roomId}")
    public List<BookingResponse> getByRoom(@PathVariable Long roomId) {
        log.info("Buscando bookings por roomId={}", roomId);

        return bookingUseCase.getByRoomId(roomId);
    }

    @GetMapping("/user/{userId}")
    public List<BookingResponse> getByUser(@PathVariable Long userId) {
        log.info("Buscando bookings por userId={}", userId);

        return bookingUseCase.getByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.warn("Eliminando booking con id={}", id);

        bookingUseCase.delete(id);

        log.info("Booking eliminado correctamente id={}", id);
    }
}