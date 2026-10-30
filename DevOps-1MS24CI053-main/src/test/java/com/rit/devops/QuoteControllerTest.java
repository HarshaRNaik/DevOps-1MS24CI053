package com.rit.devops;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void returnsAQuoteAsJson() throws Exception {
        String response = mockMvc.perform(get("/quote"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString();

        JsonNode quote = objectMapper.readTree(response).get("quote");
        assertTrue(quote.asText().startsWith("Automate")
            || quote.asText().startsWith("It works")
            || quote.asText().startsWith("Write"));
    }

    @Test
    void returnsAllQuotesAsJson() throws Exception {
        String response = mockMvc.perform(get("/quotes"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString();

        JsonNode quotes = objectMapper.readTree(response);
        assertEquals(3, quotes.size());
    }

    @Test
    void reportsHealthyStatus() throws Exception {
        mockMvc.perform(get("/health"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith("application/json"))
            .andExpect(content().json("{\"status\":\"OK\"}"));
    }
}
