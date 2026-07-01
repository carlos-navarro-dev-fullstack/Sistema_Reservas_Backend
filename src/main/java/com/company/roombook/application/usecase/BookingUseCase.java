package com.company.roombook.application.usecase;

import com.company.roombook.presentation.dto.response.BookingResponse;
import com.company.roombook.domain.model.Booking;

import java.util.List;

public interface BookingUseCase {

    BookingResponse create(Booking booking);

    BookingResponse getById(Long id);

    List<BookingResponse> getAll();

    List<BookingResponse> getByRoomId(Long roomId);

    List<BookingResponse> getByUserId(Long userId);

    void delete(Long id);
}