package com.example.AdvanceUser;

import com.example.AdvanceUser.Controller.UserController;
import com.example.AdvanceUser.Model.AdvanceUser;
import com.example.AdvanceUser.Repository.AdvanceUserRepository;
import com.example.AdvanceUser.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdvanceUserApplicationTests {

    @MockBean
    private AdvanceUserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @Autowired
    private AdvanceUserRepository advanceUserRepository;

	@Test
	void contextLoads() {
	}

    public void getAllUsers(){
        when(userRepository.findAll()).thenReturn(
                Stream.of(
                        new AdvanceUser("Billy silly", "billyisnotsilly@gmail.com", "male", "08023456789"),
                        new AdvanceUser("Willy filly", "willyfilly@gmail.com", "female", "08098765432"),
                        new AdvanceUser("Lilly bill", "lillybill@gmail.com", "female", "080223334444")
                ).toList());

            assertEquals(3, userService.findAllUsers().size());
            assertEquals("Willy filly", userService.findAllUsers().get(1).getFullName());
            assertEquals("willyfilly@gmail.com", userService.findAllUsers().get(1).getEmail());

    }

    public void findUserById(){
        AdvanceUser user = new AdvanceUser("Akin Shola", "akinShola@gmail.com", "male", "08034455566");
        when(userRepository.findById(1L)).thenReturn(
                Optional.of(user)
        );

        assertEquals(user, userService.getUserById(1L));
        assertEquals("Akin Shola", userService.getUserById(1L).getFullName());
        assertNotNull(userService.getUserById(1L));
    }

    //Integration Testing
    public void getAllUser(){
        ResponseEntity<List<AdvanceUser>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    public void saveUser(){

        //Create an object of the class
        AdvanceUser user = new AdvanceUser("Mark Lange", "MLange2gmail.com", "male", "08011122233");
        // use the Repository to save it to dataBase
        advanceUserRepository.save(user);
        //verify if user was saved successfully
        //Save instance of the object id into an object
        Long userId = user.getId();
        //store method into another object
        AdvanceUser userTest = userRepository.findById(userId).orElse(null);

        assertEquals(user, userTest);
        assertNotNull(userTest);
        assertEquals("Mark Lange", userTest.getFullName());

    }
}
