package com.example.app1.service;

import com.example.app1.dto.Answer;
import com.example.app1.dto.ModifiedAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class ConsumerService {
    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Bean
    public Consumer<Answer> input() {
        return answer -> {
            try {
                listen(answer);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        };
    }

    public void listen(Answer answer) throws JsonProcessingException {
        log.info("Получено " + mapper.writeValueAsString(answer));
        var modifiedAnswer = new ModifiedAnswer(answer.getId(), answer.getState(), answer.getTime());
        System.out.println(modifiedAnswer);
    }

}
