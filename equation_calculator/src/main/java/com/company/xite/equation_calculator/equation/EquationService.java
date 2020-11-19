package com.company.xite.equation_calculator.equation;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EquationService {
    static final String regex = "(?:(^-?\\d+)([xX*/+\\-])\\s*)+(-?\\d+\\s*)";
    static final Pattern pattern = Pattern.compile(regex);

    public Equation getEquation(String equation) {
        String equationWithoutSpaces = equation.replaceAll(" ", "");
        final Matcher matcher = pattern.matcher(equationWithoutSpaces);
        if (matcher.find()) {
            if (matcher.groupCount() == 3 && equationWithoutSpaces.length() == (matcher.group(1).length() + matcher.group(3).length() + 1)) {
                if (matcher.group(1).replaceAll("0+", "0").equals("-0")
                        || matcher.group(3).replaceAll("0+", "0").equals("-0")) {
                    throw new IllegalArgumentException();
                }
                return new Equation(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(3)),
                        matcher.group(2));
            }
        }

        throw new IllegalArgumentException();
    }

}
