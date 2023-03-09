package me.imatveev.query;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class FirstQueryPartBuilder {
    private final FinalQueryPartBuilder builder;

    public SecondQueryPartBuilder from(String table, String schema) {
        Objects.requireNonNull(schema);
        builder.setSchema(schema);

        Objects.requireNonNull(table);
        builder.setTable(table);

        return new SecondQueryPartBuilder(builder);
    }
}
