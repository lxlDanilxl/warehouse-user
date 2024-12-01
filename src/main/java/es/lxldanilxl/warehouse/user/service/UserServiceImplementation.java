package es.lxldanilxl.warehouse.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import es.lxldanilxl.warehouse.user.model.User;
import es.lxldanilxl.warehouse.user.dao.UserRepository;
import es.lxldanilxl.warehouse.user.util.exception.UserAlreadyExistsException;
import es.lxldanilxl.warehouse.user.util.exception.UserNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user. The user's password is automatically hashed before
     * insertion.
     *
     * @param user The user to register
     * @return The registered user
     */
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if (!userRepository.findByUsername(user.getUsername()).isEmpty()) {
            throw new UserAlreadyExistsException("User already exists with username: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Finds a user by their username.
     *
     * @param username The username to search for
     * @return The user with the given username, or an empty optional if not found
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Finds a user by their email.
     *
     * @param email The email to search for
     * @return The user with the given email, or an empty optional if not found
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Changes the password of a user with the given username.
     * 
     * @param username    The username of the user to change the password of
     * @param newPassword The new password for the user
     * @throws UserNotFoundException If a user with the given username is not found
     */
    @Override
    public void changePassword(String username, String newPassword) throws UserNotFoundException {
        Optional<User> userOptional = findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    /**
     * Changes the email of a user with the given username.
     * 
     * @param username The username of the user to change the email of
     * @param newEmail The new email for the user
     * @throws UserNotFoundException If a user with the given username is not found
     */
    @Override
    public void changeEmail(String username, String newEmail) throws UserNotFoundException {
        Optional<User> userOptional = findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        User user = userOptional.get();
        user.setEmail(newEmail);
        userRepository.save(user);
    }

}
