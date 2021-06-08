package com.example.app1.controller;

import com.example.app1.dto.Message;
import com.example.app1.dto.ModifiedAnswer;
import com.example.app1.dto.User;
import com.example.app1.service.ConsumerService;
import com.example.app1.service.SupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.CountDownLatch;


@RestController
public class UserController {

    private final SupplierService supplierService;
    private final ConsumerService consumerService;


    public UserController(SupplierService supplierService, ConsumerService consumerService) {
        this.supplierService = supplierService;
        this.consumerService = consumerService;
    }

    @PostMapping("/user")
    public ModifiedAnswer test(@RequestBody User user) throws InterruptedException {
        int id = Math.abs(new Random().nextInt() % 10000); // как я поняла, id это вписываемые данные (типо номер дока), иначе генерировался бы в базе
        var message = new Message(id, user.getName(), user.getPhoneNumber());
        supplierService.output(message);
        consumerService.setCountDownLatch(new CountDownLatch(1));
        consumerService.getCountDownLatch().await();
        return consumerService.getModifiedAnswer();
    }
}
