package com.example.fitnessclub.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestParameters {
    private final Map<String, String> parameters = new HashMap<>();

    public String put(String parameterName, String value) {
        return parameters.put(parameterName, value);
    }

    public String get(String parameterName) {
        return parameters.get(parameterName);
    }
}
