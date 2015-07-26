package com.springapp.controller.user;

import com.springapp.jpa.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This class contains methods for verify that the User controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserResourceBeanTest {

    private static final Long USER_ID = 123L;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserResourceBean sut;

    private MockMvc mockMvc;

    @Before
    public void init() {
        // Setup Spring test in standalone mode
        mockMvc = standaloneSetup(sut).build();
    }

    /**
     * This test verify that user is deleted correctly.
     */
    @Test
    public void shouldRemoveOneUser() {

        sut.deleteUserById(USER_ID);

        verify(userRepository, times(1)).delete(USER_ID);
    }

    /**
     * This test verify that controller return expected response to
     * a delete request.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnCorrectResponse() throws Exception {

        mockMvc.perform(delete("/exercise/{id}", USER_ID))
                .andExpect(status().isOk());

        verify(userRepository, times(1)).delete(USER_ID);
    }

}
