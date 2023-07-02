package com.imdadareeph.router;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ServiceApiHeaderType
{
    APPLICATION_CODE("ApplicationCode"),
    CORRELATION_ID("CorrelationId"),
    SERVICE_NAME("serviceName");


    @Getter
    private final String headerKey;

    ServiceApiHeaderType(final String headerKey) {
        this.headerKey = headerKey;
    }

    public static List<String> getHeaderKeys() {
        return Arrays.asList(ServiceApiHeaderType.values())
                .stream()
                .map(ServiceApiHeaderType::getHeaderKey)
                .collect(Collectors.toList());
    }
}
