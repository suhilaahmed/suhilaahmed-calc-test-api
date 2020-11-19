package com.company.xite.equation_calculator.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    public static HashMap<Long, List<UserEquation>> userHistory = new HashMap<>();

    public void addEquation(Long userId, UserEquation equation) {
        List<UserEquation> history = userHistory.get(userId);
        if (history == null) {
            userHistory.put(userId, Arrays.asList(equation));
        } else {
            ArrayList<UserEquation> newHistory = new ArrayList<>(history);
            newHistory.add(equation);
            userHistory.put(userId, newHistory);
        }
    }

    public List<UserEquation> getLatestUserEquations(long userId) {
        List<UserEquation> history = userHistory.get(userId);
        if (history == null || history.size() <= 1)
            return new ArrayList<>();
        return history.subList((history.size() > 5) ? Math.abs(history.size() -6):0,  history.size() -1);
    }

    public List<UserEquation> getAllUserEquations(long userId) {
        return userHistory.get(userId);
    }
}
