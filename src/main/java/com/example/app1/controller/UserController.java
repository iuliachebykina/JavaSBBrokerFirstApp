package com.example.app1.controller;

import com.example.app1.dto.Message;
import com.example.app1.dto.ModifiedAnswer;
import com.example.app1.dto.User;
import com.example.app1.service.ConsumerService;
import com.example.app1.service.SupplierService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;


@RestController
public class UserController {

    private final SupplierService supplierService;
    private final ConsumerService consumerService;
    private final CountDownLatch cdl;


    public UserController(SupplierService supplierService, ApplicationContext context) {
        this.supplierService = supplierService;
        this.cdl = context.getBean(CountDownLatch.class);
        this.consumerService = context.getBean(ConsumerService.class);
    }

    @PostMapping("/user")
    public ModifiedAnswer test(@RequestBody User user) throws InterruptedException {
        int id = 1234;
        var message = new Message(id, user.getName(), user.getPhoneNumber());
        supplierService.output(message);
        cdl.await();
        consumerService.input();
        return consumerService.getModifiedAnswer();
    }
}
