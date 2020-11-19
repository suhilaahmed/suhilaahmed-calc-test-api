package com.company.xite.equation_calculator.equation;


import com.company.xite.equation_calculator.classifier.NumberClassifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EquationResult {


    private double resultNumber;
    private NumberClassifier numberClassifier;


    public double getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(double resultNumber) {
        this.resultNumber = resultNumber;
    }

    public NumberClassifier getNumberClassifier() {
        return numberClassifier;
    }

    public void setNumberClassifier(NumberClassifier numberClassifier) {
        this.numberClassifier = numberClassifier;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquationResult equationResult = (EquationResult) o;
        return
                Double.compare(equationResult.resultNumber, resultNumber) == 0 &&
                numberClassifier.equals(equationResult.numberClassifier) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash( resultNumber, numberClassifier);
    }

    @Override
    public String toString() {
        return "Result{" +
             //   "userId=" + userId +
                ", resultNumber=" + resultNumber +
                ", numberClassifier=" + numberClassifier +

                '}';
    }
}
