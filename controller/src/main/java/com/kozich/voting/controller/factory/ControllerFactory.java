package com.kozich.voting.controller.factory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ControllerFactory {
    private final static ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getMapper() {
        return mapper;
    }
}
