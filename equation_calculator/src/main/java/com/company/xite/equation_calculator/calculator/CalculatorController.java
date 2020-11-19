package com.company.xite.equation_calculator.calculator;

import com.company.xite.equation_calculator.equation.Equation;
import com.company.xite.equation_calculator.equation.EquationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class CalculatorController {
    @Autowired
    private EquationService equationService;

    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping(method = RequestMethod.POST, value = "/calculate/{userId}")
    public ResponseEntity<Object> calculate(@PathVariable long userId, @RequestBody Map<String, Object> equation) {
        try {
            Equation formattedEquation = equationService.getEquation((String) equation.get("equation"));
            return ResponseEntity.ok(calculatorService.performEquation(formattedEquation, userId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect equation format");
        }
    }
}
