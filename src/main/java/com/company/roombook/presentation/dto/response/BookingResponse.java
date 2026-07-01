package com.company.roombook.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long id;

    private Long roomId;
    private String roomName;
    private Long userId;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String status;
}