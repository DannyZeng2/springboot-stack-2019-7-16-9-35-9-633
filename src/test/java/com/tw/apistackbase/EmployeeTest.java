package com.tw.apistackbase;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void return_company_list_when_get_all_company() throws Exception {
        String content = mockMvc.perform(get("/employees")).andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);
        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("Tony", jsonArray.getJSONObject(0).getString("name"));
        assertEquals(22, jsonArray.getJSONObject(0).getInt("age"));

        assertEquals(2, jsonArray.getJSONObject(1).getInt("id"));
        assertEquals("Jerry", jsonArray.getJSONObject(1).getString("name"));
        assertEquals(25, jsonArray.getJSONObject(1).getInt("age"));
    }

    @Test
    public void return_a_company_when_get_a_company() throws Exception {
        String content = mockMvc.perform(get("/employees/1")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONObject jsonArray = new JSONObject(content);
        assertEquals(1, jsonArray.getInt("id"));
        assertEquals("Tony", jsonArray.getString("name"));
        assertEquals(22, jsonArray.getInt("age"));
    }
}
