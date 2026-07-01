package com.company.roombook.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.company.roombook.application.usecase.RoomUseCase;
import com.company.roombook.domain.model.Room;
import com.company.roombook.presentation.dto.request.CreateRoomRequest;
import com.company.roombook.presentation.dto.request.RoomRequest;
import com.company.roombook.presentation.dto.response.RoomResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.roombook.presentation.mapper.RoomDTOMapper.toResponse;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    private final RoomUseCase roomUseCase;

    public RoomController(RoomUseCase roomUseCase) {
        this.roomUseCase = roomUseCase;
    }

    @PostMapping
    public RoomResponse create(@RequestBody CreateRoomRequest dto) {

        log.info("Creando room: name={}, capacity={}", dto.getName(), dto.getCapacity());

        Room room = new Room(null, dto.getName(), dto.getCapacity());

        Room created = roomUseCase.create(room);

        log.info("Room creado con id={}", created.getId());

        return new RoomResponse(
                created.getId(),
                created.getName(),
                created.getCapacity()
        );
    }

    @GetMapping
    public List<RoomResponse> getAll() {

        log.info("Listando todas las rooms");

        return roomUseCase.getAll().stream()
                .map(r -> new RoomResponse(r.getId(), r.getName(), r.getCapacity()))
                .toList();
    }

    @GetMapping("/{id}")
    public RoomResponse getById(@PathVariable Long id) {

        log.info("Buscando room por id={}", id);

        Room room = roomUseCase.getById(id)
                .orElseThrow(() -> {
                    log.warn("Room no encontrada id={}", id);
                    return new RuntimeException("Room not found");
                });

        return new RoomResponse(
                room.getId(),
                room.getName(),
                room.getCapacity()
        );
    }

    @PutMapping("/{id}")
    public RoomResponse update(@PathVariable Long id,
                               @RequestBody RoomRequest request) {

        log.info("Actualizando room id={}, name={}, capacity={}",
                id, request.getName(), request.getCapacity());

        Room room = roomUseCase.update(
                id,
                new Room(id, request.getName(), request.getCapacity())
        );

        log.info("Room actualizada correctamente id={}", id);

        return toResponse(room);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        log.warn("Eliminando room id={}", id);

        roomUseCase.delete(id);

        log.info("Room eliminada correctamente id={}", id);
    }
}