package com.company.xite.equation_calculator.user;

import java.util.List;

public class User {
    private long id;
    private List<UserEquation> performedEquations;

    public User(long id, List<UserEquation> performedEquations) {
        this.id = id;
        this.performedEquations = performedEquations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<UserEquation> getPerformedEquations() {
        return performedEquations;
    }

    public void setPerformedEquations(List<UserEquation> performedEquations) {
        this.performedEquations = performedEquations;
    }
}
