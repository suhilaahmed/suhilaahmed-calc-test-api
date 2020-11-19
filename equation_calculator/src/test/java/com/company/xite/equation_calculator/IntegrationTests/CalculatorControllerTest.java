package com.company.xite.equation_calculator.IntegrationTests;

import com.company.xite.equation_calculator.IntegrationTests.Utils.Helpers;
import com.company.xite.equation_calculator.IntegrationTests.Utils.Requests;
import com.company.xite.equation_calculator.equation.EquationResult;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private String url;

    private long userId;

    private String expectedErrorMessage;

    private void incorrectEquationFormatTest(String body, String expectedErrorMessage) throws Exception {
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isBadRequest());
        String responseErrorMessage = mvcResult.getResponse().getErrorMessage();
        Assert.assertEquals(expectedErrorMessage, responseErrorMessage);
    }

    @Before
    public void setUp() throws Exception {
        url = Helpers.loadApplicationProperties().getProperty("url");
        userId = Helpers.generateTestTicket();
        expectedErrorMessage = "Incorrect equation format";
    }


    @Test
    public void calculateBadRequestWithEmptyBody() throws Exception {
        Requests.httpRequest(mockMvc, url, "{}", userId, MediaType.APPLICATION_JSON, status().isBadRequest());
    }

    @Test
    public void calculateBadRequestWithEmptyEquation() throws Exception {
        String body = Helpers.buildRequestBody("");
        incorrectEquationFormatTest(body, expectedErrorMessage);
    }

    @Test
    public void userCannotCalculateEquationWithInvalidOperator() throws Exception {
        String body = Helpers.buildRequestBody("1#1");
        incorrectEquationFormatTest(body, expectedErrorMessage);
    }

    @Test
    public void userCannotCalculateEquationWithMoreThanOneOperator() throws Exception {
        String body = Helpers.buildRequestBody("1++1");
        incorrectEquationFormatTest(body, expectedErrorMessage);
    }

    @Test
    public void userCannotCalculateEquationWithMoreThanTwoOperands() throws Exception {
        String body = Helpers.buildRequestBody("1+1-1");
        incorrectEquationFormatTest(body, expectedErrorMessage);
    }

    @Test
    public void userCannotCalculateEquationWithIncorrectFormat() throws Exception {
        String body = Helpers.buildRequestBody("--11");
        incorrectEquationFormatTest(body, expectedErrorMessage);
    }

    @Test
    public void userCannotCalculateEquationWithNegativeZero() throws Exception {
        String body = Helpers.buildRequestBody("-0+1");
        incorrectEquationFormatTest(body, expectedErrorMessage);
    }

    @Test
    public void userCannotCalculateEquationWithDivisionOfZeroByZero() throws Exception {
        String body = Helpers.buildRequestBody("0/0");
        incorrectEquationFormatTest(body, expectedErrorMessage);
    }

    @Test
    public void userCannotCalculateEquationWithDivisionOfNumberByZero() throws Exception {
        String body = Helpers.buildRequestBody("1/0");
        incorrectEquationFormatTest(body, expectedErrorMessage);
    }

    @Test
    public void userCanCalculateCorrectEquationFormat() throws Exception {
        String body = Helpers.buildRequestBody("1+2");
        Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
    }

    @Test
    public void userCanCalculateCorrectEquationFormatWithLeadingZeros() throws Exception {
        String body = Helpers.buildRequestBody("0001+0002");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.resultNumber", 3d);
    }

    @Test
    public void userCanCalculateCorrectEquationFormatWithValidMultiplicationOperator() throws Exception {
        String body = Helpers.buildRequestBody("2*2");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.resultNumber", 4d);
    }

    @Test
    public void userCanCalculateCorrectEquationFormatWithValid_x_MultiplicationOperator() throws Exception {
        String body = Helpers.buildRequestBody("2x2");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.resultNumber", 4d);
    }

    @Test
    public void userCanCalculateCorrectEquationFormatWithValid_X_MultiplicationOperator() throws Exception {
        String body = Helpers.buildRequestBody("2X2");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.resultNumber", 4d);
    }

    @Test
    public void userCanCalculateCorrectEquationFormatWithWhiteSpaces() throws Exception {
        String body = Helpers.buildRequestBody("    1+   2");
        Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
    }

    @Test
    public void userCanCalculateEquationThatResultInPrimeNumber() throws Exception {
        String body = Helpers.buildRequestBody("5+6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.primeNumber", true);
    }

    @Test
    public void checkWhenEquationResultIsPrimeNumberThenResultIsNaturalAndPositiveAndWhole() throws Exception {
        String body = Helpers.buildRequestBody("5+6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.primeNumber", true);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.wholeNumber", true);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.positiveNumber", true);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.naturalNumber", true);
    }

    @Test
    public void checkWhenEquationResultIsPositiveNumberThenResultIsNatural() throws Exception {
        String body = Helpers.buildRequestBody("5+6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.positiveNumber", true);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.naturalNumber", true);
    }

    @Test
    public void userCanCalculateEquationThatResultInPositiveNumber() throws Exception {
        String body = Helpers.buildRequestBody("5+6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.positiveNumber", true);
    }

    @Test
    public void userCanCalculateEquationThatResultInNegativeNumber() throws Exception {
        String body = Helpers.buildRequestBody("5-6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.negativeNumber", true);
    }

    @Test
    public void checkThatWhenEquationResultIsNegativeNumberThenResultIsNotPrime() throws Exception {
        String body = Helpers.buildRequestBody("5-6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.negativeNumber", true);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.primeNumber", false);
    }

    @Test
    public void checkWhenEquationResultInNegativeNumberThenNumberIsNotNatural() throws Exception {
        String body = Helpers.buildRequestBody("5-6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.negativeNumber", true);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.naturalNumber", false);
    }

    @Test
    public void checkWhenEquationResultInNegativeNumberThenNumberIsNotPositive() throws Exception {
        String body = Helpers.buildRequestBody("5-6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.negativeNumber", true);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.positiveNumber", false);
    }

    @Test
    public void userCanCalculateEquationThatResultInWholeNumber() throws Exception {
        String body = Helpers.buildRequestBody("5+6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.wholeNumber", true);
    }

    @Test
    public void userCanCalculateEquationThatResultInFractionalNumber() throws Exception {
        String body = Helpers.buildRequestBody("5/6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.wholeNumber", false);
    }

    @Test
    public void checkWhenEquationResultInFractionalNumberThenNumberIsNotNatural() throws Exception {
        String body = Helpers.buildRequestBody("5/6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.wholeNumber", false);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.naturalNumber", false);
    }

    @Test
    public void checkWhenEquationResultInFractionalNumberThenNumberIsNotPrime() throws Exception {
        String body = Helpers.buildRequestBody("5/6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.wholeNumber", false);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.primeNumber", false);
    }

    @Test
    public void checkThatEquationCanResultInFractionalNegativeNumber() throws Exception {
        String body = Helpers.buildRequestBody("-5/6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.wholeNumber", false);
        Helpers.validatePathWithValue(mvcResult, "$.equationResult.numberClassifier.negativeNumber", true);
    }

    @Test
    public void checkThatFirstEquationForUserIsNotAddedToHistoryList() throws Exception {
        String body = Helpers.buildRequestBody("-5/6");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        List<EquationResult> equationResults = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("$.userEquations");
        Assert.assertEquals(0, equationResults.size());
    }

    @Test
    public void checkThatUserHasHistoryListThatNotEmpty() throws Exception {
        String body = Helpers.buildRequestBody("-5/6");
        Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        List<EquationResult> equationResults = (List<EquationResult>) Helpers.getValueFromPath(mvcResult, "$.userEquations");
        Assert.assertEquals(1, equationResults.size());
    }

    @Test
    public void checkThatOnlyTheMostRecentFiveEquationsAreAddedToUserHistory() throws Exception {
        String body = Helpers.buildRequestBody("1*2");
        MvcResult mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        for (int i = 0; i <= 5; i++) {
            body = Helpers.buildRequestBody(""+i+"+4");
            mvcResult = Requests.httpRequest(mockMvc, url, body, userId, MediaType.APPLICATION_JSON, status().isOk());
        }
        List<EquationResult> equationResults = (List<EquationResult>) Helpers.getValueFromPath(mvcResult, "$.userEquations");
        Assert.assertEquals(5, equationResults.size());
        Helpers.validatePathWithValue(mvcResult, "$.userEquations[0].equation.firstOperand", 0d);
        Helpers.validatePathWithValue(mvcResult, "$.userEquations[0].equation.secondOperand", 4d);
        Helpers.validatePathWithValue(mvcResult, "$.userEquations[0].equation.operator", "+");
    }


}