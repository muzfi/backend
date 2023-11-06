package com.example.muzfi.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;


}
