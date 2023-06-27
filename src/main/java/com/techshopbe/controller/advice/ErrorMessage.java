package com.techshopbe.controller.advice;

import lombok.*;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
