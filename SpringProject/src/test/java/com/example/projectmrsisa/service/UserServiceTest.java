package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.model.LoyaltyStatus;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private User userMock;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindUserById(){
        when(userRepositoryMock.findUserById(2)).thenReturn(userMock);
        User user = userService.findUserById(2);
        assertEquals(userMock, user);
        verify(userRepositoryMock, times(1)).findUserById(2);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testFindAll() {
        when(userRepositoryMock.findAll()).thenReturn(Arrays.asList(new User(1, "matijazaric9@gmail.com", "$2a$10$6zhtvn7zujmExlDo2pDwUODVq09LpFBuBL62g6Bsd2lOJjnuctrKC", "Matija", "Zaric", new Address("Srbija", "Loznica", "Drinska 84"), "+381657842312", LoyaltyStatus.Regular, 0)));
        List<User> users = userService.findAll();
        assertEquals(users.get(0).getEmail(), "matijazaric9@gmail.com");
        verify(userRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testAddUser() {
        when(userRepositoryMock.save(userMock)).thenReturn(userMock);
        User savedUser = userService.addUser(userMock);
        org.junit.Assert.assertThat(savedUser, is(equalTo(userMock)));
    }
}
