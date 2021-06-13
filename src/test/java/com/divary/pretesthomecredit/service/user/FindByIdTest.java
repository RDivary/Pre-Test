package com.divary.pretesthomecredit.service.user;

import com.divary.pretesthomecredit.exception.NotFoundException;
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
public class FindByIdTest {

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
        Mockito.when(userRepository.getById("1")).thenReturn(getActualUser());
        Mockito.when(userRepository.getById("2")).thenThrow(new NotFoundException("User"));

    }

    @Test
    public void successFind() {

        String id = "1";
        Mockito.when(userService.findById(id, null)).thenReturn(getActualUser());
        Assertions.assertEquals(id, userService.findById(id, null).getId());
    }

}
