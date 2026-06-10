package com.naveensys.services;

import com.naveensys.services.entity.User;
import com.naveensys.services.repository.UserRepository;
import com.naveensys.services.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(UserService.class)
public class UserServiceTests {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    private User testUser;
    
    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        testUser = new User();
        testUser.setName("John Doe");
        testUser.setEmail("john@example.com");
        testUser.setPhone("1234567890");
        testUser.setAddress("123 Main St");
        testUser.setCity("Springfield");
        testUser.setState("IL");
        testUser.setZipCode("62701");
    }
    
    @Test
    public void testCreateUser() {
        User createdUser = userService.createUser(testUser);
        
        assertNotNull(createdUser.getId());
        assertEquals("John Doe", createdUser.getName());
        assertEquals("john@example.com", createdUser.getEmail());
    }
    
    @Test
    public void testGetUserById() {
        User createdUser = userService.createUser(testUser);
        User foundUser = userService.getUserById(createdUser.getId());
        
        assertEquals(createdUser.getId(), foundUser.getId());
        assertEquals("John Doe", foundUser.getName());
    }
    
    @Test
    public void testGetUserByEmail() {
        userService.createUser(testUser);
        User foundUser = userService.getUserByEmail("john@example.com");
        
        assertEquals("John Doe", foundUser.getName());
        assertEquals("john@example.com", foundUser.getEmail());
    }
    
    @Test
    public void testGetAllUsers() {
        userService.createUser(testUser);
        
        User user2 = new User();
        user2.setName("Jane Doe");
        user2.setEmail("jane@example.com");
        user2.setPhone("0987654321");
        user2.setAddress("456 Oak Ave");
        user2.setCity("Shelbyville");
        user2.setState("IL");
        user2.setZipCode("62702");
        userService.createUser(user2);
        
        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }
    
    @Test
    public void testUpdateUser() {
        User createdUser = userService.createUser(testUser);
        
        User updateDetails = new User();
        updateDetails.setName("John Updated");
        updateDetails.setPhone("9999999999");
        
        User updatedUser = userService.updateUser(createdUser.getId(), updateDetails);
        
        assertEquals("John Updated", updatedUser.getName());
        assertEquals("9999999999", updatedUser.getPhone());
    }
    
    @Test
    public void testDeleteUser() {
        User createdUser = userService.createUser(testUser);
        userService.deleteUser(createdUser.getId());
        
        assertThrows(IllegalArgumentException.class, 
            () -> userService.getUserById(createdUser.getId()));
    }
    
    @Test
    public void testCreateUserWithDuplicateEmail() {
        userService.createUser(testUser);
        
        User duplicateUser = new User();
        duplicateUser.setName("Another User");
        duplicateUser.setEmail("john@example.com");
        duplicateUser.setPhone("1234567890");
        duplicateUser.setAddress("789 Pine St");
        duplicateUser.setCity("Capital City");
        duplicateUser.setState("IL");
        duplicateUser.setZipCode("62703");
        
        assertThrows(IllegalArgumentException.class, 
            () -> userService.createUser(duplicateUser));
    }
}
