package com.minibank.demo.rest;

import com.minibank.demo.model.User;
import com.minibank.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@Component
@RequestMapping("/bank/v1/users/")
public class UserRestControllerV1 {

    @Autowired
    private UserService userService;

 /*   @Value("${featire_flags.getuser}")
    private String featire_flags_getuser;

    @Value("${ffeatire_flags.seveuser}")
    private String featire_flags_seveuser;*/

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long userId) {
       /* if (featire_flags_getuser.equals("1")) {*/
            if (userId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            User user = this.userService.findById(userId);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        }
      /*  else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
      /*  if (featire_flags_seveuser.equals("1")) {*/
            HttpHeaders headers = new HttpHeaders();

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.userService.saveUser(user);
            return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
        }
     /*   else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }*/

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.saveUser(user);

        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = this.userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.findAll();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}