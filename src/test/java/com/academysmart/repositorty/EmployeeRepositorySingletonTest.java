package com.academysmart.repositorty;

import com.academysmart.controller.EmployeesServlet;
import com.academysmart.repository.EmployeeRepositorySingleton;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeRepositorySingletonTest {

    @Mock
    private EmployeeRepositorySingleton e;

    @InjectMocks
    private EmployeesServlet controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testControllerGet() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
}

