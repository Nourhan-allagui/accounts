package com.example.account.services;

import com.example.account.dtos.SignupRequest;
import com.example.account.dtos.SignUpResponse;
import com.example.account.entities.UserInformation;
import com.example.account.repositories.RoleRepository;
import com.example.account.repositories.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignUpService {

    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existsByUsername(String username) {
        return signUpRepository.findByUsername(username).isPresent();
    }

    public boolean existsByEmail(String email) {
        return signUpRepository.findByEmail(email).isPresent();
    }

    public void registerUser(SignupRequest request) {
        UserInformation user = new UserInformation();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setProfileImageUrl(request.getProfileImageUrl());
        user.setEnabled(true);
        user.setRoles(request.getRoles());
        signUpRepository.save(user);
    }

    public UserInformation updateUser(Long id, UserInformation updateUser){
        UserInformation existingUser = getUserById(id);
        existingUser.setUsername(updateUser.getUsername());
        existingUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setFirstName(updateUser.getFirstName());
        existingUser.setLastName(updateUser.getLastName());
        existingUser.setPhoneNumber(updateUser.getPhoneNumber());
        existingUser.setProfileImageUrl(updateUser.getProfileImageUrl());
        existingUser.setRoles(updateUser.getRoles());
        return signUpRepository.save(existingUser);
    }

    public List<SignUpResponse> getAllUsers() {
        List<UserInformation> users = signUpRepository.findAll();
        return users.stream()
                .map(SignUpResponse::new)
                .collect(Collectors.toList());
    }

    public UserInformation getUserById(Long id) {
        return signUpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " +id));
    }



    public void deleteUser(Long id){
        if(!signUpRepository.existsById(id)){
            throw new RuntimeException("User not found with ID: "+ id);
        }
        signUpRepository.deleteById(id);
    }
}
