package org.dsnkostic.discmaster.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class GameListControllerIntTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void test() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(
        MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("asd"));
  }

}
