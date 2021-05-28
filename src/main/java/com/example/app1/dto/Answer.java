package com.example.app1.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private String id;
    private State state;
    private String time;
    private String message;
}

enum State {
    VALID,
    INVALID
}