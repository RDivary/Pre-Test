package com.divary.pretesthomecredit.service.user;

import com.divary.pretesthomecredit.model.User;
import com.divary.pretesthomecredit.repository.UserRepository;
import com.divary.pretesthomecredit.service.implementation.UserImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SaveTest {

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
        user.setBalance(0);
        user.setCreateDate(time);
        user.setUpdateDate(time);

        return user;
    }

    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(userRepository.save(getActualUser())).thenReturn(getActualUser());
    }

    @Test
    public void successSave() {

        User expectedUser = new User();

        expectedUser.setId("1");
        expectedUser.setUsername("username");
        expectedUser.setAccountNumber("12345678");
        expectedUser.setBalance(0);
        expectedUser.setCreateDate(time);
        expectedUser.setUpdateDate(time);

        Mockito.when(userService.save(expectedUser)).thenReturn(getActualUser());
        Assertions.assertEquals(expectedUser, userService.save(expectedUser));
    }
}
