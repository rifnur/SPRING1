package ru.geekbrains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.UserRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
        Authority - разрешение на некое действие
        User (VIEW_ALL, VIEW_SINGLE, EDIT, DELETE)

        ROLE_SUPERADMIN -> (VIEW_ALL, VIEW_SINGLE, EDIT, DELETE)
        ROLE_ADMIN -> (VIEW_ALL, VIEW_SINGLE)

        ROLE -> new SimpleGrantedAuthority("ROLE_ADMIN"); -> hasRole('ADMIN') -> hasAuthority('ROLE_ADMIN')

        new SimpleGrantedAuthority("ROLE_ADMIN"); -> hasRole('ADMIN')
        new SimpleGrantedAuthority("VIEW_ALL"); -> hasAuthority('VIEW_ALL')
        new SimpleGrantedAuthority("VIEW_SINGLE"); -> hasAuthority('VIEW_SINGLE')
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
                        ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
