package com.company.xite.equation_calculator.classifier;

import java.util.Objects;

public class NumberClassifier {
    private boolean isNaturalNumber;
    private boolean isPositiveNumber;
    private boolean isNegativeNumber;
    private boolean isPrimeNumber;
    private boolean isWholeNumber;

    public NumberClassifier(boolean isNaturalNumber, boolean isPositiveNumber, boolean isNegativeNumber, boolean isPrimeNumber, boolean isWholeNumber) {
        this.isNaturalNumber = isNaturalNumber;
        this.isPositiveNumber = isPositiveNumber;
        this.isNegativeNumber = isNegativeNumber;
        this.isPrimeNumber = isPrimeNumber;
        this.isWholeNumber = isWholeNumber;
    }

    public boolean isNaturalNumber() {
        return isNaturalNumber;
    }

    public void setNaturalNumber(boolean naturalNumber) {
        isNaturalNumber = naturalNumber;
    }

    public boolean isPositiveNumber() {
        return isPositiveNumber;
    }

    public void setPositiveNumber(boolean positiveNumber) {
        isPositiveNumber = positiveNumber;
    }

    public boolean isNegativeNumber() {
        return isNegativeNumber;
    }

    public void setNegativeNumber(boolean negativeNumber) {
        isNegativeNumber = negativeNumber;
    }

    public boolean isPrimeNumber() {
        return isPrimeNumber;
    }

    public void setPrimeNumber(boolean primeNumber) {
        isPrimeNumber = primeNumber;
    }

    public boolean isWholeNumber() {
        return isWholeNumber;
    }

    public void setWholeNumber(boolean wholeNumber) {
        isWholeNumber = wholeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberClassifier that = (NumberClassifier) o;
        return isNaturalNumber == that.isNaturalNumber &&
                isPositiveNumber == that.isPositiveNumber &&
                isNegativeNumber == that.isNegativeNumber &&
                isPrimeNumber == that.isPrimeNumber &&
                isWholeNumber == that.isWholeNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isNaturalNumber, isPositiveNumber, isNegativeNumber, isPrimeNumber, isWholeNumber);
    }

    @Override
    public String toString() {
        return "NumberClassifier{" +
                "isNaturalNumber=" + isNaturalNumber +
                ", isPositiveNumber=" + isPositiveNumber +
                ", isNegativeNumber=" + isNegativeNumber +
                ", isPrimeNumber=" + isPrimeNumber +
                ", isWholeNumber=" + isWholeNumber +
                '}';
    }
}
