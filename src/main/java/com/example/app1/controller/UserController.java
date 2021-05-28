package com.example.app1.controller;

import com.example.app1.dto.Message;
import com.example.app1.dto.User;
import com.example.app1.service.ConsumerService;
import com.example.app1.service.SupplierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final SupplierService supplierService;
    private final ConsumerService consumerService;



    public UserController(SupplierService supplierService, ConsumerService consumerService) {
        this.supplierService = supplierService;
        this.consumerService = consumerService;
    }

    @PostMapping("/user")
    public void test(@RequestBody User user) throws JsonProcessingException {
        int id = 1234;
        var message = new Message(id, user.getName(), user.getPhoneNumber());
        supplierService.output(message);
        consumerService.input();
    }
}
