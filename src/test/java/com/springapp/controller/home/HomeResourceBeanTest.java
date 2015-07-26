package com.springapp.controller.home;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * This class contains methods for verify that the Home controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeResourceBeanTest {

    private static final String HOME_PAGE_RESULT = "home";

    @InjectMocks
    private HomeResourceBean sut;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    /**
     * This test verify that request / response are correct for exercise page.
     *
     * @throws Exception
     */
    @Test
    public void testExercisesPage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(view().name(HOME_PAGE_RESULT));

    }
}
