package com.company.roombook.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {

    private Long roomId;
    private Long userId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String status;
}