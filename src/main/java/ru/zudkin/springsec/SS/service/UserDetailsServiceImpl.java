package ru.zudkin.springsec.SS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.zudkin.springsec.SS.model.User;
import ru.zudkin.springsec.SS.repository.UserRepository;



import java.util.Optional;
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
                user.get().getPassword(), user.get().getAuthorities());
    }
}
