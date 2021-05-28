package com.example.app1.service;


import com.example.app1.dto.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    private final StreamBridge streamBridge;
    private static final Logger log = LoggerFactory.getLogger(SupplierService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public SupplierService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void output(Message message) throws JsonProcessingException {
        streamBridge.send("output-out-0", MessageBuilder.withPayload(message).build());
        log.info("Отправлено " + mapper.writeValueAsString(message));
    }
}
