package com.divary.pretesthomecredit.service.user;

import com.divary.pretesthomecredit.dto.request.AddBalanceRequest;
import com.divary.pretesthomecredit.exception.NotFoundException;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddBalanceTest {

    @Mock
    private UserImpl userService;

    @Mock
    private UserRepository userRepository;

    private LocalDateTime time = LocalDateTime.now();

    private User getActualUser() {

        User user = new User();

        user.setId("1");
        user.setUsername("username");
        user.setAccountNumber("12345678");
        user.setBalance(10000);
        user.setCreateDate(time);
        user.setUpdateDate(time);

        return user;
    }

    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userRepository.save(getActualUser())).thenReturn(getActualUser());
        Mockito.when(userRepository.getById("1")).thenReturn(getActualUser());

    }

    @Test
    public void successAddBalance() {

        AddBalanceRequest addBalanceRequest = new AddBalanceRequest();

        addBalanceRequest.setAddBalanceAmount(10000l);

        User user = getActualUser();
        user.setBalance(user.getBalance() + 10000);

        Mockito.when(userService.addBalance("1", addBalanceRequest)).thenReturn(user);

        userService.addBalance("1", addBalanceRequest);

        Assertions.assertEquals(getActualUser().getBalance() + 10000, userService.addBalance("1", addBalanceRequest).getBalance());

    }
}
