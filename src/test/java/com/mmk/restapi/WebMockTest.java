package com.mmk.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
public class WebMockTest {

    private static final String issPeopleJSON = """
            {"message":"success","number":"7","people":[
                {"name":"Jasmin Moghbeli","craft":"ISS"},
                {"name":"Andreas Mogensen","craft":"ISS"},
                {"name":"Satoshi Furukawa","craft":"ISS"},
                {"name":"Konstantin Borisov","craft":"ISS"},
                {"name":"Oleg Kononenko","craft":"ISS"},
                {"name":"Nikolai Chub","craft":"ISS"},
                {"name":"Loral O'Hara","craft":"ISS"}],
                "meta":{"ip":"Malaka.local/127.0.0.1"}}
            """;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeopleService peopleService;

    @MockBean
    private Config config;

    @MockBean
    RestTemplate restTemplate;

    @Test
    void getIssPeopleFromService() throws Exception {
        when(peopleService.getIssPeople()).thenReturn(issPeopleJSON);
        this.mockMvc.perform(get("/people")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Konstantin Borisov")));
    }
}
