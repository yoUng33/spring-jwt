package com.chegg.interview.springjwt.controller;

import com.chegg.interview.springjwt.model.User;
import com.chegg.interview.springjwt.payload.MessageResponse;
import com.chegg.interview.springjwt.payload.UserDto;
import com.chegg.interview.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/info")
public class UserInfoController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestHeader(value = "id") Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseEntity.ok().body(new MessageResponse("failed to find user"));
        }
        UserDto userDto = UserDto.fromUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResponseEntity<?> getByEmail(@RequestHeader(value = "email") String email){
        User user = userRepository.findByEmail(email).orElse(null);
        if(user == null){
            return ResponseEntity.ok().body(new MessageResponse("failed to find user"));
        }
        UserDto userDto = UserDto.fromUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
