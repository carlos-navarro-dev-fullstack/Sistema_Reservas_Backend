package com.company.roombook.application.usecase;

import com.company.roombook.domain.model.Booking;
import com.company.roombook.presentation.dto.request.CreateBookingRequest;

public interface CreateBookingUseCase {

    Booking execute(CreateBookingRequest request);
}