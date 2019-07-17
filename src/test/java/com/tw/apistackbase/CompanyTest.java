package com.tw.apistackbase;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CompanyTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void return_company_list_when_get_all_company() throws Exception {
        String content = mockMvc.perform(get("/companies")).andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);
        assertEquals(1, jsonArray.getJSONObject(0).getInt("companyId"));
        assertEquals("alibaba", jsonArray.getJSONObject(0).getString("companyName"));
        assertEquals(10000, jsonArray.getJSONObject(0).getInt("employeesNumber"));
    }

    @Test
    public void return_a_company_when_get_a_company() throws Exception {
        String content = mockMvc.perform(get("/companies/1")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONObject jsonArray = new JSONObject(content);
        assertEquals(1, jsonArray.getInt("companyId"));
        assertEquals("alibaba", jsonArray.getString("companyName"));
        assertEquals(10000, jsonArray.getInt("employeesNumber"));
    }

    @Test
    public void return_employee_list_when_get_specific_employee() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/companies/1/employees")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());

        assertEquals(1, jsonArray.getJSONObject(0).getInt("id"));
        assertEquals("Tony", jsonArray.getJSONObject(0).getString("name"));

        assertEquals(2, jsonArray.getJSONObject(1).getInt("id"));
        assertEquals("Jerry", jsonArray.getJSONObject(1).getString("name"));
    }

    @Test
    public void return__employees_when_get_age_employee() throws Exception {
        String content = mockMvc.perform(get("/companies").param("page","1").param("pageSize","5")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);
        assertEquals(1, jsonArray.getJSONObject(0).getInt("companyId"));
        assertEquals("alibaba", jsonArray.getJSONObject(0).getString("companyName"));

        assertEquals(2, jsonArray.getJSONObject(1).getInt("companyId"));
        assertEquals("tencent", jsonArray.getJSONObject(1).getString("companyName"));

    }


    @Test
    public void return_status_is_created_when_put_new_company() throws Exception {
        Map<String,String> company = new HashMap();
        company.put("companyId","3");
        company.put("companyName","Glen");
        company.put("employeesNumber","1000");

        JSONObject jsonObject = new JSONObject(company);
        String objectJson = jsonObject.toString();

        this.mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectJson)).andExpect(status().isCreated());
    }

    @Test
    public void return_new_company_list_when_update_a_company() throws Exception {

        String content = mockMvc.perform(put("/companies/1")).andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);
        assertEquals(1, jsonArray.getJSONObject(0).getInt("companyId"));
        assertEquals("wangyi", jsonArray.getJSONObject(0).getString("companyName"));
        assertEquals(10000, jsonArray.getJSONObject(0).getInt("employeesNumber"));
    }

    @Test
    public void return_new_company_list_when_delete_a_company() throws Exception {

        String content = mockMvc.perform(delete("/companies/1")).andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);

        assertEquals(1, jsonArray.length());

    }

}
