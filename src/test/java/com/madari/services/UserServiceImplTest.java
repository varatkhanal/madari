package com.madari.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.madari.dto.RoleDTO;
import com.madari.dto.UserDTO;
import com.madari.exceptions.EmailAlreadyExistsException;
import com.madari.exceptions.UsernameNotFoundException;
import com.madari.model.Role;
import com.madari.model.Users;
import com.madari.repository.RoleRepository;
import com.madari.repository.UserRepository;
import com.madari.services.UserServiceImpl;
import com.madari.shared.Utils;


//@ExtendWith(MockitoExtension.class) //The @ExtendWith(MockitoExtension.class) approach uses JUnit 5's extension mechanism to automatically initialize mocks.
class UserServiceImplTest {
	
	@Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private Utils utils;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser_Success() throws EmailAlreadyExistsException, UsernameNotFoundException {
        // Arrange
        UserDTO userDto = new UserDTO();
        userDto.setEmail("test@example.com");
        userDto.setUserName("testuser");
        userDto.setPassword("password");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("user");
        userDto.setRole(roleDTO);

        Users user = new Users();
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        Role role = new Role();
        role.setName("ROLE_USER");

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);
        when(userRepository.findByUserName(userDto.getUserName())).thenReturn(null);
        when(utils.generatedUserId(30)).thenReturn("publicUserId");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(role);
        when(bCryptPasswordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(Users.class))).thenReturn(user);
        when(userService.getUser(user.getUserName())).thenReturn(userDto);

        // Act
        UserDTO createdUser = userService.createUser(userDto);

        // Assert
        assertNotNull(createdUser);
        assertEquals(userDto.getEmail(), createdUser.getEmail());
        assertEquals(userDto.getUserName(), createdUser.getUserName());
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    public void testCreateUser_EmailAlreadyExists() {
        // Arrange
        UserDTO userDto = new UserDTO();
        userDto.setEmail("bharat@gmail.com");
        userDto.setUserName("bharat.khanal");

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(new Users());

        // Act & Assert
        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.createUser(userDto);
        });
    }

    @Test
    public void testCreateUser_UsernameAlreadyExists() {
        // Arrange
        UserDTO userDto = new UserDTO();
        userDto.setEmail("bharat@gmail.com");
        userDto.setUserName("bharat.khanal");

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);
        when(userRepository.findByUserName(userDto.getUserName())).thenReturn(new Users());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.createUser(userDto);
        });
    }
	
	
//	@Test
//    void testCreateUser_UsernameExists() {
//        UserDTO userDto = new UserDTO();
//        userDto.setEmail("test@example.com");
//        userDto.setUserName("existinguser");
//        userDto.setPassword("password");
//
//        when(userRepository.findByEmail(anyString())).thenReturn(null);
//        when(userRepository.findByUserName(anyString())).thenReturn(new Users());
//
//        assertThrows(RuntimeException.class, () -> userService.createUser(userDto));
//    }
}	