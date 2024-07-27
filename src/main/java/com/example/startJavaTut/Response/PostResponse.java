package com.example.startJavaTut.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PostResponse {
    private  Long id;
    private  String title;
    private  String description;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;
}
