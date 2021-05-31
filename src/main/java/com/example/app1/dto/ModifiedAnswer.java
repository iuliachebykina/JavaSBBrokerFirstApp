package com.example.app1.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModifiedAnswer {
    private String id;
    private State state;
    private String time;
}
