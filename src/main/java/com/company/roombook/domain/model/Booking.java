package com.company.roombook.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private Long id;
    private Long roomId;
    private Long userId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String status; // PENDING, CONFIRMED, CANCELLED
}