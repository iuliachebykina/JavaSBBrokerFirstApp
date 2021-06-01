package com.example.app1.service;

import com.example.app1.dto.Answer;
import com.example.app1.dto.ModifiedAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.SneakyThrows;
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
        return this::listen;
    }


    public void listen(Answer answer) {
        try {
            log.info("Получено " + mapper.writeValueAsString(answer));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        var modifiedAnswer = new ModifiedAnswer(answer.getId(), answer.getState(), answer.getTime());//не уверена, как лучше делать
        System.out.println(modifiedAnswer);
        /*SimpleFilterProvider filterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);
        filterProvider.addFilter("myFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("message"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.setFilterProvider(filterProvider);
        try {
            var str = mapper.writeValueAsString(answer);
            System.out.println(str);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }*/
    }

}
