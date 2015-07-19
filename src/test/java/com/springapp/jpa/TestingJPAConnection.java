package com.springapp.jpa;

import com.springapp.configuration.persistence.PersistenceConfiguration;
import com.springapp.configuration.root.ApplicationConfig;
import com.springapp.jpa.repository.ExerciseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by giuseppe on 18/07/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfiguration.class , ApplicationConfig.class}, loader = AnnotationConfigContextLoader.class)
public class TestingJPAConnection {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Before
    public final void before() {

    }

    @Test
    public final void test(){

        exerciseRepository.findAll();
    }


}
