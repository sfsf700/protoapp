package com.prototype.protoapp.stock.service;

import com.prototype.protoapp.stock.entity.LoginUserDetails;
import com.prototype.protoapp.stock.repository.LoginUser;
import com.prototype.protoapp.stock.repository.LoginUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final LoginUserRepository loginUserRepository;

    public LoginUserDetailsService(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<LoginUser> loginUserOptional = loginUserRepository.findByEmail(name);
        return loginUserOptional.map(loginUser -> new LoginUserDetails(loginUser))
                .orElseThrow(()-> new UsernameNotFoundException("not found"));
    }
}
