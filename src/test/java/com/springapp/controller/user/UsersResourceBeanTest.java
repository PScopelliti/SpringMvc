package com.springapp.controller.user;

import com.springapp.jpa.model.User;
import com.springapp.jpa.repository.UserRepository;
import com.springapp.utils.user.UserStubFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * This class contains methods for verify that the Users controller manage requests/responses correctly.
 */
@RunWith(MockitoJUnitRunner.class)
public class UsersResourceBeanTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsersResourceBean sut;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = standaloneSetup(sut).build();
    }

    /**
     * This test verify that Users performs correct interaction
     * when return a list of users.
     */
    @Test
    public void shouldReturnListOfUsers() {

        final List<User> userList = UserStubFactory.createStubbedUserList(4);

        when(userRepository.findAll()).thenReturn(userList);

        final List<User> result = sut.getUsers();

        Assert.assertNotNull("Result is null", result);
        Assert.assertEquals("Result contents is different than expected.", userList, result);

        verify(userRepository, times(1)).findAll();
    }

    /**
     * This test verify that the controller return the expected
     * response for the specified request.
     *
     * @throws Exception
     */
    @Test
    public void shouldProcessGetCorrectly() throws Exception {

        final List<User> userList = UserStubFactory.createStubbedUserList(1);

        when(userRepository.findAll()).thenReturn(userList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(userList.get(0).getId()))
                .andExpect(jsonPath("$[0].username").value(userList.get(0).getUsername()));

        verify(userRepository, times(1)).findAll();
    }
}
