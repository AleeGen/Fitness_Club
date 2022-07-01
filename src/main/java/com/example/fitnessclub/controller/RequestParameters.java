package com.example.fitnessclub.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RequestParameters {

    private final Map<String, String> parameters = new HashMap<>();
    private final Map<String, String[]> masParameters = new HashMap<>();

    public String put(String parameterName, String value) {
        return parameters.put(parameterName, value);
    }

    public String[] put(String parameterName, String[] values) {
        return masParameters.put(parameterName, values);
    }

    public String get(String parameterName) {
        return parameters.get(parameterName);
    }

    public String[] getMas(String parameterName) {
        return masParameters.get(parameterName);
    }

    public Collection<String> values() {
        return parameters.values();
    }

    public void clear() {
        parameters.clear();
    }
}
