package ru.elmanov.jwt.security.demo.service.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.elmanov.jwt.security.demo.model.User;
import ru.elmanov.jwt.security.demo.model.security.UserDetailsImpl;
import ru.elmanov.jwt.security.demo.service.UserService;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found: " + username);
        }
        return UserDetailsImpl.convert(user);
    }
}
