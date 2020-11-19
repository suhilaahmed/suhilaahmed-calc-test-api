package com.company.xite.equation_calculator.user;

import com.company.xite.equation_calculator.equation.EquationResult;
import com.company.xite.equation_calculator.equation.Equation;

import java.util.Objects;

public class UserEquation {

    private Equation equation;
    private EquationResult equationResult;

    public UserEquation(Equation equation, EquationResult equationResult) {
        this.equation = equation;
        this.equationResult = equationResult;
    }

    public Equation getEquation() {
        return equation;
    }

    public void setEquation(Equation equation) {
        this.equation = equation;
    }

    public EquationResult getEquationResult() {
        return equationResult;
    }

    public void setEquationResult(EquationResult equationResult) {
        this.equationResult = equationResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEquation userEquation = (UserEquation) o;
        return Objects.equals(equation, userEquation.equation) &&
                Objects.equals(equationResult, userEquation.equationResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equation, equationResult);
    }

    @Override
    public String toString() {
        return "History{" +
                "equation=" + equation +
                ", result=" + equationResult +
                '}';
    }
}
