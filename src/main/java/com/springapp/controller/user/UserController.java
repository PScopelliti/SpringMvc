package com.springapp.controller.user;

import com.springapp.jpa.model.Exercise;
import com.springapp.jpa.model.User;
import com.springapp.service.user.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

/**
 * This class define a controller for users.
 */
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserResource userResource;

    @Autowired
    public UserController(final UserResource userResource) {
        this.userResource = userResource;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable
                               final Integer id) {

        userResource.deleteUserById(id);
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> processRegistration(@Valid
                                                    @RequestBody
                                                    final User user) {
        final User savedUser = userResource.save(user);

        final HttpHeaders headers = new HttpHeaders();
        final URI locationUri = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/api/v1/users/")
                .path(String.valueOf(savedUser.getId()))
                .build()
                .toUri();

        headers.setLocation(locationUri);

        final ResponseEntity<User> responseEntity =
                new ResponseEntity<>(savedUser, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User showUserDetails(@PathVariable
                                final Integer id) {

        return userResource.showUserDetails(id);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@Valid
                           @RequestBody
                           final User user,
                           @PathVariable
                           final Integer id) {

        return userResource.updateUser(user, id);
    }

    @RequestMapping(value = "/{id}/exercises",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Exercise> getExercisesPerUser(@PathVariable
                                                    final Integer id) {

        return userResource.getExercisesPerUser(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getUsers() {

        return userResource.getUsers();
    }
}
