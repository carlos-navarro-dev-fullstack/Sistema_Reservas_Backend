package com.company.roombook.presentation.mapper;

import com.company.roombook.domain.model.Room;
import com.company.roombook.presentation.dto.request.RoomRequest;
import com.company.roombook.presentation.dto.response.RoomResponse;

public class RoomDTOMapper {

    // REQUEST → DOMAIN
    public static Room toDomain(Long id, RoomRequest request) {
        return new Room(
                id,
                request.getName(),
                request.getCapacity()
        );
    }

    // DOMAIN → RESPONSE
    public static RoomResponse toResponse(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getName(),
                room.getCapacity()
        );
    }
}