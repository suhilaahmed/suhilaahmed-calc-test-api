package com.company.xite.equation_calculator.UnitTests.user;

import com.company.xite.equation_calculator.IntegrationTests.Utils.Helpers;
import com.company.xite.equation_calculator.user.User;
import com.company.xite.equation_calculator.user.UserEquation;
import com.company.xite.equation_calculator.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @MockBean
    UserService userService;

    @MockBean
    UserEquation userEquation;

    long userId;

    @Before
    public void setUp() {
        userService = new UserService();
        userId = Helpers.generateTestTicket();
        userEquation = mock(UserEquation.class);
    }

    @Test
    public void testAddEquationToEmptyUserHistory() {
        userService.addEquation(userId, userEquation);
        assertEquals(1, userService.getAllUserEquations(userId).size());
    }

    @Test
    public void testAddEquationToNonEmptyUserHistory() {
        User user = mock(User.class);
        userService.addEquation(user.getId(), userEquation);
        userService.addEquation(user.getId(), userEquation);
        assertEquals(2, userService.getAllUserEquations(user.getId()).size());
    }

    @Test
    public void testGetLatestUserEquationsWhenHistoryHas5Equations() {
        UserEquation userEquation = mock(UserEquation.class);
        for(int i = 0; i<=5 ;i++){
            userService.addEquation(userId, userEquation);
        }
        assertEquals(userService.getLatestUserEquations(userId).size(), 5);
    }

    @Test
    public void testGetLatestUserEquationsWhenHistoryHasMoreThanEquations() {
        UserEquation userEquation = mock(UserEquation.class);
        for(int i = 0; i<= 6 ;i++){
            userService.addEquation(userId, userEquation);
        }
        assertEquals(userService.getAllUserEquations(userId).size(), 7);
        assertEquals(userService.getLatestUserEquations(userId).size(), 5);
    }


    @Test
    public void testGetLatestUserEquationsWhenThereIsNoHistory() {
        userService.addEquation(userId, userEquation);
        assertEquals(0, userService.getLatestUserEquations(userId).size());
    }

    @Test
    public void testGetLatestUserEquationsWhenThereIsHistory() {
        userService.addEquation(userId, userEquation);
        userService.addEquation(userId, userEquation);
        assertEquals(1, userService.getLatestUserEquations(userId).size());
    }
}