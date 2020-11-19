package com.company.xite.equation_calculator.UnitTests.equation;

import com.company.xite.equation_calculator.equation.Equation;
import com.company.xite.equation_calculator.equation.EquationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EquationServiceTest {
    @MockBean
    EquationService equationService;

    @MockBean
    Equation equation;


    @Before
    public void setUp() throws Exception {
        equationService = new EquationService();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEquationWithInvalidEquationString() {
        equation = equationService.getEquation("1#2");

    }

    @Test
    public void testGetEquationWithValidEquationString() {
        equation = equationService.getEquation(" -0002 / 0");
        assertEquals(equation.getFirstOperand(), -2, 0);
        assertEquals(equation.getSecondOperand(), 0, 0);
        assertEquals(equation.getOperator(), "/");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEquationWithInvalidEquationOperands() {
        equation = equationService.getEquation(" -0 / 0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEquationWithEmptyEquationString() {
        equation = equationService.getEquation("");
    }


}