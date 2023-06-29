package com.khayal.turboaz.controller;

import com.khayal.turboaz.dto.UserDto;
import com.khayal.turboaz.request.UserRequest;
import com.khayal.turboaz.request.UserResponse;
import com.khayal.turboaz.model.User;
import com.khayal.turboaz.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        return ResponseEntity.ok(authenticationService.getAllUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto){
        return  ResponseEntity.ok(authenticationService.save(userDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserRequest request){

        return ResponseEntity.ok(authenticationService.auth(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        authenticationService.deleteUser(id);
        return ResponseEntity.ok().build();

    }
    @PatchMapping("/{id}")
        public ResponseEntity<Void> deactivate(@PathVariable Integer id){
        authenticationService.deactivateUser(id);
        return ResponseEntity.ok().build();

    }
    @PatchMapping("/active/{id}")
    public ResponseEntity<Void> activeUser(@PathVariable Integer id){
        authenticationService.activeUser(id);
        return ResponseEntity.ok().build();

    }

}
