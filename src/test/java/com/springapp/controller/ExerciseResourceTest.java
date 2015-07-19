package com.springapp.controller;

import com.springapp.configuration.web.WebMvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class}, loader = AnnotationConfigContextLoader.class)
public class ExerciseResourceTest {

    //@InjectMocks
    private ExerciseResource exerciseResource;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        // Process mock annotations
//        MockitoAnnotations.initMocks(this);

        exerciseResource = new ExerciseResourceBean();
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(exerciseResource).build();
    }

    @Test
    public void testHomePage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(view().name("exercise"));

    }
}
