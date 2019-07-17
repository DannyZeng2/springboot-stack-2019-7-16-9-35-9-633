package com.tw.apistackbase;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void return_employees_list_when_get_all_employees() throws Exception {
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
    public void return_a_employees_when_get_a_employees() throws Exception {
        String content = mockMvc.perform(get("/employees/1")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONObject jsonArray = new JSONObject(content);
        assertEquals(1, jsonArray.getInt("id"));
        assertEquals("Tony", jsonArray.getString("name"));
        assertEquals(22, jsonArray.getInt("age"));
    }

    @Test
    public void return_employees_when_get_current_page_employee() throws Exception {
        String content = mockMvc.perform(get("/employees").param("page","1").param("pageSize","5")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);
        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("Tony", jsonArray.getJSONObject(0).getString("name"));

        assertEquals(2, jsonArray.getJSONObject(1).getInt("id"));
        assertEquals("Jerry", jsonArray.getJSONObject(1).getString("name"));

    }

    @Test
    public void return_male_employees_when_get_male_employee() throws Exception {
        String content = mockMvc.perform(get("/employees").param("gender","male")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);
        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("Tony", jsonArray.getJSONObject(0).getString("name"));

        assertEquals(2, jsonArray.getJSONObject(1).getInt("id"));
        assertEquals("Jerry", jsonArray.getJSONObject(1).getString("name"));

    }
    @Test
    public void return_status_is_created_when_put_new_company() throws Exception {
        Map<String,String> employee = new HashMap();
        employee.put("id","3");
        employee.put("name","Glen");
        employee.put("age","33");
        employee.put("gender","male");
        employee.put("salary","12000");


        JSONObject jsonObject = new JSONObject(employee);
        String objectJson = jsonObject.toString();

        this.mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson)).andExpect(status().isCreated());
    }

    @Test
    public void return_new_company_list_when_update_a_company() throws Exception {

        String content = mockMvc.perform(put("/employees/1")).andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);

        assertEquals("Sally", jsonArray.getJSONObject(0).getString("name"));
        assertEquals("female", jsonArray.getJSONObject(0).getString("gender"));
    }

    @Test
    public void return_new_company_list_when_delete_a_company() throws Exception {

        String content = mockMvc.perform(delete("/employees/1")).andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);

        assertEquals(1, jsonArray.length());

    }
}
