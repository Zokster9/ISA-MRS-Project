package com.example.projectmrsisa.service;

import com.example.projectmrsisa.model.Address;
import com.example.projectmrsisa.repository.AddressRepository;
import com.example.projectmrsisa.service.AddressService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepositoryMock;

    @Mock
    private Address addressMock;

    @InjectMocks
    private AddressService addressService;

    @Test
    public void testFindAddressById(){
        when(addressRepositoryMock.findAddressById(4)).thenReturn(addressMock);
        Address address = addressService.findAddressById(4);
        assertEquals(addressMock, address);
        verify(addressRepositoryMock, times(1)).findAddressById(4);
        verifyNoMoreInteractions(addressRepositoryMock);
    }

    @Test
    public void testGetAddress(){
        Address address = new Address();
        address.setCountry("Srbija");
        address.setCity("Subotica");
        address.setStreet("Brace Radica 21");

        when(addressRepositoryMock.getAddressByCityCountryStreet("Srbija","Subotica","Brace Radica 21")).thenReturn(address);
        Address dbAddress = addressService.getAddress(address);

        assertThat(dbAddress).isNotNull();
        assertThat(dbAddress.getCity()).isEqualTo("Subotica");
        assertThat(dbAddress.getCountry()).isEqualTo("Srbija");
        assertThat(dbAddress.getStreet()).isEqualTo("Brace Radica 21");

        verify(addressRepositoryMock, times(1)).getAddressByCityCountryStreet("Srbija","Subotica","Brace Radica 21");
        verifyNoMoreInteractions(addressRepositoryMock);
    }

    @Test
    public void testSaveAddress(){
        when(addressRepositoryMock.save(addressMock)).thenReturn(addressMock);
        Address savedAddress = addressService.saveAddress(addressMock);
        org.junit.Assert.assertThat(savedAddress, is(equalTo(addressMock)));
    }
}
