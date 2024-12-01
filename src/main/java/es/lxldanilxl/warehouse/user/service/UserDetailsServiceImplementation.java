package es.lxldanilxl.warehouse.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.lxldanilxl.warehouse.user.dao.UserRepository;
import es.lxldanilxl.warehouse.user.model.User;
import es.lxldanilxl.warehouse.user.model.UserDetailsImplementation;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user by their username, throwing a UsernameNotFoundException if not
     * found.
     * 
     * @param username The username to search for
     * @return The user with the given username
     * @throws UsernameNotFoundException If no user with the given username is found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new UserDetailsImplementation(user.get());
    }

}
