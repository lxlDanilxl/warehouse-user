package es.lxldanilxl.warehouse.user.service;

import java.util.Optional;

import es.lxldanilxl.warehouse.user.model.User;
import es.lxldanilxl.warehouse.user.util.exception.UserAlreadyExistsException;
import es.lxldanilxl.warehouse.user.util.exception.UserNotFoundException;

public interface UserService {

    /**
     * Registers a new user. The user's password is automatically hashed before
     * insertion.
     *
     * @param user The user to register
     * @return The registered user
     */
    User registerUser(User user) throws UserAlreadyExistsException;

    /**
     * Finds a user by their username.
     *
     * @param username The username to search for
     * @return The user with the given username, or an empty optional if not found
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by their email.
     *
     * @param email The email to search for
     * @return The user with the given email, or an empty optional if not found
     */
    Optional<User> findByEmail(String email);

    /**
     * Changes the password of a user with the given username.
     * 
     * @param username    The username of the user to change the password of
     * @param newPassword The new password for the user
     * @throws UserNotFoundException If a user with the given username is not found
     */
    void changePassword(String username, String newPassword) throws UserNotFoundException;

    /**
     * Changes the email of a user with the given username.
     * 
     * @param username The username of the user to change the email of
     * @param newEmail The new email for the user
     * @throws UserNotFoundException If a user with the given username is not found
     */
    void changeEmail(String username, String newEmail) throws UserNotFoundException;

}