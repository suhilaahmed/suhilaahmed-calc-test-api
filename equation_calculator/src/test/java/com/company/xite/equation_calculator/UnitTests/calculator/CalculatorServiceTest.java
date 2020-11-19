package com.company.xite.equation_calculator.UnitTests.calculator;

import com.company.xite.equation_calculator.IntegrationTests.Utils.Helpers;
import com.company.xite.equation_calculator.calculator.CalculatorService;
import com.company.xite.equation_calculator.equation.Equation;
import com.company.xite.equation_calculator.equation.EquationResponse;
import com.company.xite.equation_calculator.equation.EquationResult;
import com.company.xite.equation_calculator.user.UserService;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {
    @MockBean
    Equation equation;

    @MockBean
    CalculatorService calculatorService;

    @MockBean
    UserService userService;

    long userId;

    @Before
    public void setUp() throws Exception {
        userService = new UserService();
        calculatorService = new CalculatorService(userService);
        equation = new Equation(1d, 2d, "+");
        userId = Helpers.generateTestTicket();
    }

    @Test
    public void testGetEquationResultWithValidPlusOperator(){
        equation.setOperator("+");
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
        assertEquals(3d, equationResult.getResultNumber(), 0);
    }

    @Test
    public void testGetEquationResultWithValidMinusOperator(){
        equation.setOperator("-");
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
        assertEquals(-1d, equationResult.getResultNumber(), 0);
    }

    @Test
    public void testGetEquationResultWithValidMultiplyOperator(){
        equation.setOperator("*");
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
        assertEquals(2d, equationResult.getResultNumber(), 0);
    }

    @Test
    public void testGetEquationResultWithValidXMultiplyOperator(){
        equation.setOperator("X");
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
        assertEquals(2d, equationResult.getResultNumber(), 0);
    }

    @Test
    public void testGetEquationResultWithValid_x_MultiplyOperator(){
        equation.setOperator("x");
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
        assertEquals(2d, equationResult.getResultNumber(), 0);
    }

    @Test
    public void testGetEquationResultWithValidDivisionOperator(){
        equation.setOperator("/");
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
        assertEquals(0.5d, equationResult.getResultNumber(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEquationResultWithInValidOperator(){
        equation.setOperator("#");
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEquationResultWithEmptyOperator(){
        equation.setOperator("");
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
    }

    @Test(expected = NullPointerException.class)
    public void testGetEquationResultWithNullOperator(){
        equation.setOperator(null);
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPerformEquationWithInvalidEquation(){
        equation.setOperator("#");
        calculatorService.performEquation(equation, userId);
    }

    @Test
    public void testPerformEquationWithValidEquation(){
        equation.setOperator("+");
        EquationResponse equationResponse = calculatorService.performEquation(equation,  userId);
        assertEquals(3d, equationResponse.getEquationResult().getResultNumber(), 0);
        assertEquals(1, userService.getAllUserEquations(userId).size());
    }

    @Test(expected = NullPointerException.class)
    public void testPerformEquationWithEmptyEquation(){
        EquationResponse equationResponse = calculatorService.performEquation(new Equation(Double.NaN, Double.NaN, null), userId);
    }

    @Test
    public void testPerformEquationWithEquationProducePrimeNumber(){
        equation.setOperator("+");
        EquationResponse equationResponse = calculatorService.performEquation(equation, userId);
        assertEquals(3d, equationResponse.getEquationResult().getResultNumber(), 0);
        assertEquals(1, userService.getAllUserEquations(userId).size());
        assertTrue(equationResponse.getEquationResult().getNumberClassifier().isPrimeNumber());
    }

    @Test
    public void testPerformEquationWithEquationProduceNegativeNumber(){
        equation.setOperator("-");
        EquationResponse equationResponse = calculatorService.performEquation(equation, userId);
        assertEquals(-1d, equationResponse.getEquationResult().getResultNumber(), 0);
        assertEquals(1, userService.getAllUserEquations(userId).size());
        assertTrue(equationResponse.getEquationResult().getNumberClassifier().isNegativeNumber());
    }

    @Test
    public void testPerformEquationWithEquationProduceNaturalNumber(){
        equation.setOperator("+");
        EquationResponse equationResponse = calculatorService.performEquation(equation, userId);
        assertEquals(3d, equationResponse.getEquationResult().getResultNumber(), 0);
        assertEquals(1, userService.getAllUserEquations( userId).size());
        assertTrue(equationResponse.getEquationResult().getNumberClassifier().isNaturalNumber());
    }

    @Test
    public void testPerformEquationWithEquationProducePositiveNumber(){
        equation.setOperator("+");
        EquationResponse equationResponse = calculatorService.performEquation(equation,  userId);
        assertEquals(3d, equationResponse.getEquationResult().getResultNumber(), 0);
        assertEquals(1, userService.getAllUserEquations( userId).size());
        assertTrue(equationResponse.getEquationResult().getNumberClassifier().isPositiveNumber());
    }

    @Test
    public void testPerformEquationWithEquationProduceWholeNumber(){
        equation.setOperator("+");
        EquationResponse equationResponse = calculatorService.performEquation(equation,  userId);
        assertEquals(3d, equationResponse.getEquationResult().getResultNumber(), 0);
        assertEquals(1, userService.getAllUserEquations(userId).size());
        assertTrue(equationResponse.getEquationResult().getNumberClassifier().isWholeNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateEquationForDivisionWithZero(){
        equation.setOperator("/");
        equation.setSecondOperand(0);
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(calculatorService.getEquationResult(equation));
    }
}