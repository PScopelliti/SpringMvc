package com.springapp.controller.exercise;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.repository.ExerciseRepository;
import com.springapp.utils.exercise.ExerciseStubFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * This class contains methods for verify that the Exercises controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExercisesResourceBeanTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExercisesResourceBean sut;

    private MockMvc mockMvc;

    @Test
    public void testExercisesPage() throws Exception {

        final List<Exercise> expectedExercises = ExerciseStubFactory.createStubExercisesList(5);

        when(exerciseRepository.findAll()).thenReturn(expectedExercises);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(sut)
                .setSingleView(new InternalResourceView("/WEB-INF/views/exercises.jsp"))
                .build();

        mockMvc.perform(get("/exercises"))
                .andExpect(view().name("exercises"))
                .andExpect(model().attributeExists("exerciseList"))
                .andExpect(model().attribute("exerciseList", hasItems(expectedExercises.toArray())));

        verify(exerciseRepository, times(1)).findAll();
    }
}