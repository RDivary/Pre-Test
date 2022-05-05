package com.divary.pretesthomecredit.service.user.impl;

import com.divary.pretesthomecredit.dto.request.AddBalanceRequest;
import com.divary.pretesthomecredit.dto.request.CreateUSerRequest;
import com.divary.pretesthomecredit.enums.LogTransferEnum;
import com.divary.pretesthomecredit.exception.NotFoundException;
import com.divary.pretesthomecredit.model.LogTransfer;
import com.divary.pretesthomecredit.model.User;
import com.divary.pretesthomecredit.repository.LogTransferRepository;
import com.divary.pretesthomecredit.repository.UserRepository;
import com.divary.pretesthomecredit.service.LogTransferService;
import com.divary.pretesthomecredit.service.implementation.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserImplTest {

//    @InjectMocks
    UserImpl userImpl;

//    @Mock
    UserRepository userRepository;

//    @Mock
    LogTransferService logTransferService;

//    @Mock
    Random random;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        userRepository = mock(UserRepository.class);
        logTransferService = mock(LogTransferService.class);
        random = mock(Random.class);
    }

    @Test
    void TestCreateOrUpdate_CreateUser_OK(){

        User userDb = getUserDb();

        User userSave = getUserSave();

        CreateUSerRequest form = new CreateUSerRequest();
        form.setBalance(1000);
        form.setUsername("tester");

        when(random.nextInt(10)).thenReturn(1);
        when(userRepository.save(userSave)).thenReturn(userDb);

        UserImpl userImpl = new UserImpl(userRepository, logTransferService, random);

        User userActual = userImpl.createOrUpdate(form, null);

        System.err.println(userSave);
        System.err.println(userDb);

        assertEquals(userDb.getId(), userActual.getId());
        assertEquals(userDb.getUsername(), userActual.getUsername());
        assertEquals(userDb.getBalance(), userActual.getBalance());
        assertNotNull(userActual.getAccountNumber());

        verify(userRepository, Mockito.times(0)).findById(anyString());
    }

    @Test
    void TestCreateOrUpdate_UpdateUser_OK(){

        User userDb = getUserDb();
        System.err.println(userDb);

        User userSave = getUserSave();
        userSave.setUsername("hi");

        CreateUSerRequest form = new CreateUSerRequest();
        form.setBalance(1000);
        form.setUsername("test");

        when(userRepository.findById("112233")).thenReturn(Optional.of(userDb));
        when(userRepository.save(userDb)).thenReturn(userDb);

        User userActual = userImpl.createOrUpdate(form, "112233");

        System.err.println(userActual);
        System.err.println(userDb);
        assertEquals(userDb, userActual);
        verify(userRepository).findById(anyString());
    }

    @Test
    void TestCreateOrUpdate_UpdateUser_ThrowNotFoundExceptionWhenIdNotFound(){

        User userDb = new User();
        userDb.setId("uuid");
        userDb.setUsername("tester");
        userDb.setBalance(1000);
        userDb.setAccountNumber("12345678");

        CreateUSerRequest form = new CreateUSerRequest();
        form.setBalance(1000);
        form.setUsername("tester");

        assertThrows(NotFoundException.class, () -> userImpl.createOrUpdate(form, "112233"));

        verify(userRepository).findById(anyString());
        verify(userRepository, Mockito.times(0)).save(any());
    }

//    @Test
//    void testAddBalance_OK(){
//
//        User userDb = getUserDb();
//
//        User userSave = getUserSave();
//        userSave.setId("uuid");
//
//        AddBalanceRequest form = new AddBalanceRequest();
//        form.setAddBalanceAmount(1000L);
//
//        when(userRepository.findById("uuid")).thenReturn(Optional.of(userDb));
//
//        userDb.setBalance(2000);
//        userDb.setLogTransfers(new ArrayList<>());
//        when(userRepository.save(userSave)).thenReturn(userDb);
//        when(logTransferService.create(LogTransferEnum.ADD_BALANCE, userSave, form.getAddBalanceAmount(), any())).thenReturn(new LogTransfer());
//
//        User userActual = userImpl.addBalance("uuid", form);
//
//        assertEquals(userDb.getId(), userActual.getId());
//        assertEquals(userDb.getUsername(), userActual.getUsername());
//        assertEquals(userDb.getBalance(), userActual.getBalance());
//        assertNotNull(userActual.getAccountNumber());
//        assertEquals(userDb, userActual);
//
//    }

    private User getUserDb(){

        User userDb = new User();
        userDb.setId("uuid");
        userDb.setUsername("tester");
        userDb.setBalance(1000);
        userDb.setAccountNumber("1111111");

        return userDb;
    }

    private User getUserSave(){

        User userSave = new User();
        userSave.setUsername("tester");
        userSave.setBalance(1000);
        userSave.setAccountNumber("1111111");

        return userSave;
    }
}
