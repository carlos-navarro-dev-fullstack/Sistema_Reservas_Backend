package com.company.roombook.application.service;

import com.company.roombook.application.usecase.CreateBookingUseCase;
import com.company.roombook.domain.model.Booking;
import com.company.roombook.domain.repository.BookingRepository;
import com.company.roombook.presentation.dto.request.CreateBookingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateBookingUseCaseImpl implements CreateBookingUseCase {

    private final BookingRepository bookingRepository;

    public CreateBookingUseCaseImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking execute(CreateBookingRequest request) {

        // 1. traer reservas de la misma sala
        List<Booking> existing = bookingRepository.findByRoomId(request.getRoomId());

        // 2. validar conflicto de horarios
        boolean conflict = existing.stream().anyMatch(b ->
                request.getStartTime().isBefore(b.getEndTime()) &&
                        request.getEndTime().isAfter(b.getStartTime())
        );

        if (conflict) {
            throw new RuntimeException("Room already booked in this time range");
        }

        // 3. construir dominio
        Booking booking = new Booking(
                null,
                request.getRoomId(),
                request.getUserId(),
                request.getStartTime(),
                request.getEndTime(),
                "CONFIRMED"
        );

        // 4. guardar
        return bookingRepository.save(booking);
    }
}