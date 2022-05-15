package com.example.fitnessclub.controller;

public class Router {
    private String page = "index.jsp";
    private Type type = Type.FORWARD;

    public enum Type {
        FORWARD, REDIRECT;
    }

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public Type getType() {
        return type;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }

    public void setForward() {
        this.type = Type.FORWARD;
    }
}