package com.kimhong.thymeleaf.model;

import javax.validation.constraints.NotBlank;

public class Testing {
    @NotBlank
    String firstName;

    public Testing() {
    }

    public Testing(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Testing{" +
                "firstName='" + firstName + '\'' +
                '}';
    }
}
