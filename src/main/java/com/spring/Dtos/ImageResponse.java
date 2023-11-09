package com.spring.Dtos;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {
    private String imagName;
    private String message;
    private boolean success;
    private HttpStatus status;
}
