package com.divary.pretesthomecredit.service.user;

import com.divary.pretesthomecredit.dto.request.AddBalanceRequest;
import com.divary.pretesthomecredit.dto.request.TransferRequest;
import com.divary.pretesthomecredit.model.User;
import com.divary.pretesthomecredit.repository.UserRepository;
import com.divary.pretesthomecredit.service.implementation.UserImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransferTest {

    @Mock
    private UserImpl userService;

    @Mock
    private UserRepository userRepository;

    private LocalDateTime time = LocalDateTime.now();

    private User getSender() {

        User user = new User();

        user.setId("1");
        user.setUsername("sender");
        user.setAccountNumber("12345678");
        user.setBalance(10000);
        user.setCreateDate(time);
        user.setUpdateDate(time);

        return user;
    }

    private User getReceiver() {

        User user = new User();

        user.setId("2");
        user.setUsername("receiver");
        user.setAccountNumber("87654321");
        user.setBalance(10000);
        user.setCreateDate(time);
        user.setUpdateDate(time);

        return user;
    }

    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userRepository.save(getSender())).thenReturn(getSender());
        Mockito.when(userRepository.save(getReceiver())).thenReturn(getReceiver());

        Mockito.when(userRepository.findById("1")).thenReturn(Optional.of(getSender()));

    }

    @Test
    public void successTransfer() {

        TransferRequest transferRequest = new TransferRequest();

        long amount = 5000l;

        transferRequest.setAccountNumberReceive("87654321");
        transferRequest.setAmount(amount);

        User sender = getSender();
        sender.setBalance(sender.getBalance() - amount);

        User receiver = getReceiver();
        receiver.setBalance(receiver.getBalance() + amount);

        Mockito.when(userService.transfer(transferRequest, "1")).thenReturn(sender);
        Mockito.when(userRepository.findByAccountNumberEquals("87654321")).thenReturn(Optional.of(receiver));

        User user = userService.transfer(transferRequest, "1");

        Assertions.assertEquals(sender.getBalance(), user.getBalance());
        Assertions.assertEquals(receiver.getBalance(), userRepository.findByAccountNumberEquals(receiver.getAccountNumber()).get().getBalance());
    }
}
