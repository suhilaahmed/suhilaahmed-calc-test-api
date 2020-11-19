package com.company.xite.equation_calculator.equation;

import com.company.xite.equation_calculator.user.UserEquation;

import java.util.List;
import java.util.Objects;

public class EquationResponse {
    private EquationResult equationResult;
    private List<UserEquation>  userEquations;

    public EquationResponse(EquationResult equationResult, List<UserEquation> userEquations) {
        this.equationResult = equationResult;
        this.userEquations = userEquations;
    }

    public EquationResult getEquationResult() {
        return equationResult;
    }

    public void setEquationResult(EquationResult equationResult) {
        this.equationResult = equationResult;
    }

    public List<UserEquation> getUserEquations() {
        return userEquations;
    }

    public void setUserEquations(List<UserEquation> userEquations) {
        this.userEquations = userEquations;
    }

    @Override
    public String toString() {
        return "EquationResponse{" +
                "equationResult=" + equationResult +
                ", userEquations=" + userEquations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquationResponse that = (EquationResponse) o;
        return Objects.equals(equationResult, that.equationResult) &&
                Objects.equals(userEquations, that.userEquations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equationResult, userEquations);
    }
}
