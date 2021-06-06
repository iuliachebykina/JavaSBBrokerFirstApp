package com.example.app1.service;

import com.example.app1.dto.Answer;
import com.example.app1.dto.ModifiedAnswer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

@Service
@Data
public class ConsumerService {
    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final CountDownLatch latch;
    private ModifiedAnswer modifiedAnswer;

    public ConsumerService(CountDownLatch latch) {
        this.latch = latch;
    }


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
        try{
            modifiedAnswer = new ModifiedAnswer(answer.getId(), answer.getState(), answer.getTime());//не уверена, как лучше делать
            latch.countDown();

        } catch (Exception e) {
            log.error(e.getMessage());
        }

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
