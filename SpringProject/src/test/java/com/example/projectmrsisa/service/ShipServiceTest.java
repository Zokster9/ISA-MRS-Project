package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.model.LoyaltyStatus;
import com.example.projectmrsisa.model.Ship;
import com.example.projectmrsisa.model.User;
import com.example.projectmrsisa.repository.ShipRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipServiceTest {

    @Mock
    private ShipRepository shipRepositoryMock;

    @Mock
    private Ship shipMock;

    @InjectMocks
    private ShipService shipService;

    @Test
    public void testFindShipById() {
        when(shipRepositoryMock.findShipById(2)).thenReturn(shipMock);
        Ship ship = shipService.findShipById(2);
        assertEquals(ship, shipMock);
        verify(shipRepositoryMock, times(1)).findShipById(2);
        verifyNoMoreInteractions(shipRepositoryMock);
    }

    @Test
    public void testAddShip() {
        when(shipRepositoryMock.save(shipMock)).thenReturn(shipMock);
        Ship savedShip = shipService.addShip(shipMock);
        org.junit.Assert.assertThat(savedShip, is(equalTo(shipMock)));
    }

    @Test
    @Rollback(true)
    public void testDeleteShipById() {
        when(shipRepositoryMock.findShipById(2)).thenReturn(shipMock);
        shipRepositoryMock.delete(shipMock);
        verify(shipRepositoryMock).delete(shipMock);
    }
}
