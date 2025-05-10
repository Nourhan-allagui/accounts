package com.example.account.security;

import com.example.account.entities.UserInformation;
import com.example.account.repositories.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SignUpDetailsService implements UserDetailsService {

    @Autowired
    private SignUpRepository signUpRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return signUpRepository.findOneWithRolesByLoginIgnoreCase(email)
                .map(user -> {
                    return createSecurityUser(user);
                })
                .orElseThrow(() -> new UsernameNotFoundException("User with login " + email + " could not be found."));
    }

    private User createSecurityUser(UserInformation userEntity) {
        List<SimpleGrantedAuthority> grantedRoles = userEntity
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .toList();
        return new User(userEntity.getEmail(), userEntity.getPassword(), grantedRoles);
    }
}
