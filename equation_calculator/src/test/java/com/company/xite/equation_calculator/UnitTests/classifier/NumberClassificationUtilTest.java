package com.company.xite.equation_calculator.UnitTests.classifier;

import com.company.xite.equation_calculator.classifier.NumberClassificationUtil;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberClassificationUtilTest {

    @Test
    public void testIsNaturalNumberWithNegativeNumber(){
        assertFalse(NumberClassificationUtil.isNaturalNumber(-1d));
    }

    @Test
    public void testIsNaturalNumberWithPositiveNumber(){
        assertTrue(NumberClassificationUtil.isNaturalNumber(1d));
    }

    @Test
    public void testIsNaturalNumberWithNotANumber(){
        assertFalse(NumberClassificationUtil.isNaturalNumber(Double.NaN));
    }

    @Test
    public void testIsPositiveNumberWithPositiveNumber(){
        assertTrue(NumberClassificationUtil.isPositiveNumber(1d));
    }

    @Test
    public void testIsPositiveNumberWithNegativeNumber(){
        assertFalse(NumberClassificationUtil.isPositiveNumber(-1d));
    }

    @Test
    public void testIsPositiveNumberWithNotANumber(){
        assertFalse(NumberClassificationUtil.isPositiveNumber(Double.NaN));
    }

    @Test
    public void testIsPrimeNumberWithPrimeNumber(){
        assertTrue(NumberClassificationUtil.isPrimeNumber(3d));
    }

    @Test
    public void testIsPrimeNumberWithNumberGreaterThan3(){
        assertFalse(NumberClassificationUtil.isPrimeNumber(6d));
    }

    @Test
    public void testIsPrimeNumberWithNotPrimeNumber(){
        assertFalse(NumberClassificationUtil.isPrimeNumber(1d));
    }

    @Test(expected = NumberFormatException.class)
    public void testIsPrimeNumberWithNotANumberNumber(){
        NumberClassificationUtil.isPrimeNumber(Double.NaN);
    }

    @Test
    public void testIsWholeNumberWithNonFractionalNumber(){
        assertTrue(NumberClassificationUtil.isWholeNumber(3d));
    }

    @Test
    public void testIsWholeNumberWithFractionalNumber(){
        assertFalse(NumberClassificationUtil.isWholeNumber(1.5d));
    }

    @Test
    public void testIsWholeNumberWithNotANumber(){
        assertFalse(NumberClassificationUtil.isWholeNumber(Double.NaN));
    }

    @Test
    public void testIsNegativeNumberWithNegativeNumber(){
        assertTrue(NumberClassificationUtil.isNegativeNumber(-1d));
    }

    @Test
    public void testIsNegativeNumberWithPositiveNumber(){
        assertFalse(NumberClassificationUtil.isNegativeNumber(1d));
    }

    @Test
    public void testIsNegativeNumberWithNotANumber(){
        assertFalse(NumberClassificationUtil.isNegativeNumber(Double.NaN));
    }



}