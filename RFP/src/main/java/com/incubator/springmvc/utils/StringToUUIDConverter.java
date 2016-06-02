package com.incubator.springmvc.utils;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;

public class StringToUUIDConverter implements Converter<String, UUID> {
    public UUID convert(String source) {
        return UUID.fromString(source);
    }
}