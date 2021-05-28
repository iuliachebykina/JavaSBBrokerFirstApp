package com.example.app1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private int id;
    private String name;
    private String phoneNumber;
}