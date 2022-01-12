package com.macys.macysordermessageconsumer;

import com.macys.macysordermessageconsumer.controller.MOMessageConsumerController;
import com.macys.macysordermessageconsumer.service.MOMessageConsumerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MOMessageConsumerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    MOMessageConsumerService service;

    @MockBean
    MOMessageConsumerController controller;

    @Test
    void testControllerNotNull() {
        Assertions.assertNotNull(controller);
    }

    @Test
    void testServiceProduceXmlMessage() throws Exception {

        given(service.getXmlMessage("rabbitmq")).willReturn(new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK));

        MvcResult result = mvc.perform(get("/macy/consumer/xml")
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .accept(MediaType.APPLICATION_XML_VALUE)
                        .header("x-messaging-queue", "rabbitmq"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(MediaType.APPLICATION_XML_VALUE, result.getResponse().getContentType());
    }

    @Test
    void testServiceProduceJsonMessage() throws Exception {

        given(service.getJsonMessage("gcp")).willReturn(new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK));

        MvcResult result = mvc.perform(get("/macy/consumer/json")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .header("x-messaging-queue", "gcp"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
    }

}
