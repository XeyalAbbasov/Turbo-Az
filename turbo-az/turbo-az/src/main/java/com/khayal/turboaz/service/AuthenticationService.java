package com.khayal.turboaz.service;

import com.khayal.turboaz.dto.UserDto;
import com.khayal.turboaz.request.UserRequest;
import com.khayal.turboaz.request.UserResponse;
import com.khayal.turboaz.enums.Role;
import com.khayal.turboaz.exception.UserNotFoundException;
import com.khayal.turboaz.model.User;
import com.khayal.turboaz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserResponse save(UserDto userDto) {

        User user=User.builder().username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(Role.USER).build();
        userRepository.save(user);
        var token=jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();

    }

    public UserResponse auth(UserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }

    public void deleteUser(Integer id) {
        if (userExists(id)) {
            userRepository.deleteById(id);
        }else{
                throw new UserNotFoundException("User not found by following id :"+id);
            }
    }

    public void deactivateUser(Integer id) {

        User user =findUserById(id);

        user.setActive(false);

        userRepository.save(user);
    }
    public void activeUser(Integer id) {

        User user =findUserById(id);

        user.setActive(true);

        userRepository.save(user);
    }


    protected User findUserById(Integer id){

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found by following id :"+id));
    }

    protected boolean userExists(Integer id){

        return userRepository.existsById(id);

    }


}
