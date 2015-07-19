package com.springapp.controller;

import com.springapp.requestmap.RequestMappingBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseResourceTest {

    private static final String EXERCISE_PAGE_RESULT = "exercise";

    @InjectMocks
    private RequestMappingBean requestMappingBean;

    @Mock
    private ExerciseResource exerciseResource;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(requestMappingBean).build();

        when(exerciseResource.printWelcome(any(ModelMap.class))).thenReturn(EXERCISE_PAGE_RESULT);
    }

    @Test
    public void testExercisesPage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(view().name(EXERCISE_PAGE_RESULT));

        verify(exerciseResource, times(1)).printWelcome(any(ModelMap.class));

    }
}
