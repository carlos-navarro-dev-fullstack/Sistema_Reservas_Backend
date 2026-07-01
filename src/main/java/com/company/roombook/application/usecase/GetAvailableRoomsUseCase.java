package com.company.roombook.application.usecase;

import com.company.roombook.domain.model.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface GetAvailableRoomsUseCase {

    List<Room> execute(LocalDateTime start, LocalDateTime end);
}