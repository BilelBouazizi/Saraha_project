package com.bouaziz.saraha.services.auth;

import com.bouaziz.saraha.models.User;
import com.bouaziz.saraha.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

//on peut le mettre aussi dans la config
@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
   private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user=repository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
