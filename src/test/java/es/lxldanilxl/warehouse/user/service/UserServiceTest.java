package es.lxldanilxl.warehouse.user.service;

import es.lxldanilxl.warehouse.user.model.User;
import es.lxldanilxl.warehouse.user.dao.UserRepository;
import es.lxldanilxl.warehouse.user.util.exception.UserAlreadyExistsException;
import es.lxldanilxl.warehouse.user.util.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        // Limpiar la base de datos antes de cada prueba
        userRepository.deleteAll();

        // Crear un usuario de prueba
        user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
    }

    @Test
    void testRegisterUser() throws UserAlreadyExistsException {
        // Preparar el usuario de prueba
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("plainpassword");

        // Registrar el usuario
        User registeredUser = userService.registerUser(user);

        // Verificar que el usuario fue registrado correctamente
        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
        assertTrue(passwordEncoder.matches("plainpassword", registeredUser.getPassword()));
    }

    @Test
    void testRegisterUser_throwsException_ifUserAlreadyExists() {
        // Registrar el usuario inicialmente
        userService.registerUser(user);

        // Intentar registrar el mismo usuario nuevamente y esperar la excepción
        assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(user));
    }

    @Test
    void testFindByUsername() throws UserAlreadyExistsException {
        // Registrar el usuario
        userService.registerUser(user);

        // Buscar el usuario por nombre de usuario
        Optional<User> foundUser = userService.findByUsername(user.getUsername());

        // Verificar que el usuario se ha encontrado correctamente
        assertTrue(foundUser.isPresent());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
    }

    @Test
    void testFindByUsername_returnsEmpty_ifUserNotFound() {
        // Buscar un usuario que no existe
        Optional<User> foundUser = userService.findByUsername("nonexistentuser");

        // Verificar que el resultado es vacío
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindByEmail() throws UserAlreadyExistsException {
        // Registrar el usuario
        userService.registerUser(user);

        // Buscar el usuario por email
        Optional<User> foundUser = userService.findByEmail(user.getEmail());

        // Verificar que el usuario se ha encontrado correctamente
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void testFindByEmail_returnsEmpty_ifUserNotFound() {
        // Buscar un usuario que no existe
        Optional<User> foundUser = userService.findByEmail("nonexistent@example.com");

        // Verificar que el resultado es vacío
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testChangePassword() throws UserNotFoundException {
        // Registrar el usuario
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("plainpassword");
        try {
            userService.registerUser(user);
        } catch (UserAlreadyExistsException e) {
            fail("Failed to register user: " + e.getMessage());
        }

        // Cambiar la contraseña
        userService.changePassword("testuser", "newpassword");

        // Verificar que la contraseña fue cambiada
        Optional<User> updatedUser = userService.findByUsername("testuser");
        assertTrue(updatedUser.isPresent());
        assertTrue(passwordEncoder.matches("newpassword", updatedUser.get().getPassword()));
    }

    @Test
    void testChangePassword_throwsException_ifUserNotFound() {
        // Intentar cambiar la contraseña de un usuario inexistente
        assertThrows(UserNotFoundException.class,
                () -> userService.changePassword("nonexistentuser", "newpassword123"));
    }

    @Test
    void testChangeEmail() throws UserAlreadyExistsException, UserNotFoundException {
        // Registrar el usuario
        userService.registerUser(user);

        // Cambiar el email del usuario
        userService.changeEmail(user.getUsername(), "newemail@example.com");

        // Verificar que el email ha sido cambiado
        Optional<User> updatedUser = userService.findByUsername(user.getUsername());
        assertTrue(updatedUser.isPresent());
        assertEquals("newemail@example.com", updatedUser.get().getEmail());
    }

    @Test
    void testChangeEmail_throwsException_ifUserNotFound() {
        // Intentar cambiar el email de un usuario inexistente
        assertThrows(UserNotFoundException.class,
                () -> userService.changeEmail("nonexistentuser", "newemail@example.com"));
    }
}
