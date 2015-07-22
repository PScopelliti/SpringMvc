package com.springapp.controller.exercise;

import com.springapp.jpa.repository.ExerciseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This class contains methods for verify that the Exercise controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExerciseResourceBeanTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseResourceBean sut;

    private MockMvc mockMvc;

    /**
     * This test verify that the controller return the expected
     * registration form page.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnRegistrationForm() throws Exception {

        mockMvc = standaloneSetup(sut).build();

        mockMvc.perform(get("/exercise/register"))
                .andExpect(view().name("exerciseRegisterForm"));
    }


}
