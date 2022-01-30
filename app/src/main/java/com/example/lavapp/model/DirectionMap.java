package com.example.lavapp.model;

import com.squareup.okhttp.Route;

import java.util.List;

public class DirectionMap {
    private String status, error_message;
    private List<Route> routes;

    public DirectionMap() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
