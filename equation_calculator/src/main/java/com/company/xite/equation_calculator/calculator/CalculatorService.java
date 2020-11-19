package com.company.xite.equation_calculator.calculator;

import com.company.xite.equation_calculator.equation.EquationResponse;
import com.company.xite.equation_calculator.equation.EquationResult;
import com.company.xite.equation_calculator.classifier.NumberClassificationUtil;
import com.company.xite.equation_calculator.classifier.NumberClassifier;
import com.company.xite.equation_calculator.equation.Equation;
import com.company.xite.equation_calculator.user.UserEquation;
import com.company.xite.equation_calculator.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    @Autowired
    private UserService userService;

    public CalculatorService(UserService userService) {
        this.userService = userService;
    }

    public EquationResponse performEquation(Equation equation, long userId) {
        EquationResult equationResult = new EquationResult();
        equationResult.setResultNumber(getEquationResult(equation));
        equationResult.setNumberClassifier(
                new NumberClassifier(NumberClassificationUtil.isNaturalNumber(equationResult.getResultNumber()),
                        NumberClassificationUtil.isPositiveNumber(equationResult.getResultNumber()),
                        NumberClassificationUtil.isNegativeNumber(equationResult.getResultNumber()),
                        NumberClassificationUtil.isPrimeNumber(equationResult.getResultNumber()),
                        NumberClassificationUtil.isWholeNumber(equationResult.getResultNumber()))
        );
        userService.addEquation(userId, new UserEquation(equation, equationResult));

        return new EquationResponse(equationResult,userService.getLatestUserEquations(userId));
    }

    public double getEquationResult(Equation equation) {
        switch (equation.getOperator()) {
            case "+":
                return equation.getFirstOperand() + equation.getSecondOperand();
            case "-":
                return equation.getFirstOperand() - equation.getSecondOperand();
            case "*":
            case "x":
            case "X":
                return equation.getFirstOperand() * equation.getSecondOperand();
            case "/":
                validateEquationForDivision(equation);
                return equation.getFirstOperand() / equation.getSecondOperand();
        }
        throw new IllegalArgumentException();
    }

    public boolean validateEquationForDivision(Equation equation){
        if(equation.getOperator().equals("/") && equation.getSecondOperand() == 0.0){
                throw new IllegalArgumentException();
        }
        return true;
    }


}
