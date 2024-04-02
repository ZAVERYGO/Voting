package com.kozich.voting.service.api;

public interface Converter<FROM, TO> {
    TO convert(FROM item);
}