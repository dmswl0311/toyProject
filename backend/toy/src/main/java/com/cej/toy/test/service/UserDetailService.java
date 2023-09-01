package com.cej.toy.test.service;

import com.cej.toy.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final TestRepository testRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserDetails userDetails = testRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return userDetails;
    }
}
