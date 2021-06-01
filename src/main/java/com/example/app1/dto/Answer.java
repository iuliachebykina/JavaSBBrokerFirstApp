package com.example.app1.dto;



import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@JsonFilter("myFilter")
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