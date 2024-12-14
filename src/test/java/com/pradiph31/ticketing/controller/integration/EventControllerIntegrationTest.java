package com.pradiph31.ticketing.controller.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradiph31.ticketing.TicketingApplication;
import com.pradiph31.ticketing.dto.event.EventRequestDTO;
import com.pradiph31.ticketing.dto.event.EventUpdateDTO;
import jakarta.servlet.ServletContext;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TicketingApplication.class)
@WebAppConfiguration
class EventControllerIntegrationTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                  .build();
  }

  @Test
  void eventControllerWhenServletContextGivenWAC() {
    ServletContext servletContext = webApplicationContext.getServletContext();

    assertNotNull(servletContext);
    assertInstanceOf(MockServletContext.class, servletContext);
    assertNotNull(webApplicationContext.getBean("eventController"));
  }

  @Test
  void whenGetEventsThenVerifyResponse() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(get("/api/events"))
                                      .andDo(System.out::println)
                                      .andExpect(status().isOk())
                                      .andExpect(jsonPath("$").isArray())
                                      .andReturn();

    assertNotNull(mvcResult);
    assertNotNull(mvcResult.getResponse());
    assertEquals(200,
                 mvcResult.getResponse()
                          .getStatus());
    assertEquals("application/json",
                 mvcResult.getResponse()
                          .getContentType());
  }

  @Test
  void whenGetEventByIdThenVerifyResponse() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(get("/api/events/{eventId}", 2))
                                      .andDo(System.out::println)
                                      .andExpect(status().isOk())
                                      .andExpect(jsonPath("$.eventId").value(2))
                                      .andReturn();

    assertNotNull(mvcResult);
    assertNotNull(mvcResult.getResponse());
    assertEquals(200,
                 mvcResult.getResponse()
                          .getStatus());
    assertEquals("application/json",
                 mvcResult.getResponse()
                          .getContentType());
  }

  @Test
  void whenPostEventThenVerifyResponse() throws Exception {
    EventRequestDTO eventRequestDTO = new EventRequestDTO("Bubbling Heights",
                                                          "East Coast Music Festival",
                                                          List.of(),
                                                          "New York, NY",
                                                          LocalDateTime.of(2025, 9, 1, 0, 0),
                                                          LocalDateTime.of(2025, 9, 2, 0, 0));
    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/events")
                                                   .contentType("application/json")
                                                   .content(objectMapper.writeValueAsString(
                                                       eventRequestDTO)))
                                      .andDo(System.out::println)
                                      .andExpect(status().isOk())
                                      .andExpect(jsonPath("$.eventId").value(3))
                                      .andReturn();
    assertNotNull(mvcResult);
    assertNotNull(mvcResult.getResponse());
    assertEquals(200,
                 mvcResult.getResponse()
                          .getStatus());
    assertEquals("application/json",
                 mvcResult.getResponse()
                          .getContentType());
  }

  @Test
  void whenPutEventThenVerifyResponse() throws Exception {
    EventUpdateDTO eventUpdateDTO = new EventUpdateDTO("Bubbling Heights",
                                                       "East Coast Music Festival",
                                                       List.of("Outdoor", "Music"),
                                                       "New York, NY",
                                                       LocalDateTime.of(2025, 9, 1, 0, 0),
                                                       LocalDateTime.of(2025, 9, 2, 0, 0),
                                                       false);
    MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put("/api/events/{eventId}",
                                                                          1)
                                                   .contentType("application/json")
                                                   .content(objectMapper.writeValueAsString(
                                                       eventUpdateDTO)))
                                      .andDo(System.out::println)
                                      .andExpect(status().isOk())
                                      .andExpect(jsonPath("$.eventId").value(1))
                                      .andExpect(jsonPath("$.isAvailable").value(false))
                                      .andReturn();
    assertNotNull(mvcResult);
    assertNotNull(mvcResult.getResponse());
    assertEquals(200,
                 mvcResult.getResponse()
                          .getStatus());
    assertEquals("application/json",
                 mvcResult.getResponse()
                          .getContentType());
  }

  @Test
  void deleteEventThenVerifyResponse() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(delete("/api/events/{eventId}", 1))
                                      .andDo(System.out::println)
                                      .andExpect(status().isOk())
                                      .andExpect(jsonPath("$").value(true))
                                      .andReturn();

    assertNotNull(mvcResult);
    assertNotNull(mvcResult.getResponse());
    assertEquals(200,
                 mvcResult.getResponse()
                          .getStatus());
    assertEquals("application/json",
                 mvcResult.getResponse()
                          .getContentType());
  }

  @Test
  void getEventsWithTicketsThenVerifyResponse() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(get("/api/events/{eventId}/tickets", 2))
                                      .andDo(System.out::println)
                                      .andExpect(status().isOk())
                                      .andExpect(jsonPath("$.tickets").isArray())
                                      .andExpect(jsonPath("$.tickets.length()").value(1))
                                      .andReturn();

    assertNotNull(mvcResult);
    assertNotNull(mvcResult.getResponse());
    assertEquals(200,
                 mvcResult.getResponse()
                          .getStatus());
    assertEquals("application/json",
                 mvcResult.getResponse()
                          .getContentType());
  }

}
