package me.imatveev.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SelectQueryFactory {
    public static FirstQueryPartBuilder select(boolean distinct, @NonNull String... fields) {
        final FinalQueryPartBuilder finalBuilder = new FinalQueryPartBuilder();
        Objects.requireNonNull(fields);
        finalBuilder.setFields(fields);
        finalBuilder.setDistinct(distinct);

        return new FirstQueryPartBuilder(finalBuilder);
    }

    public static FirstQueryPartBuilder select(@NonNull String... fields) {
        return select(false, fields);
    }

    public static FirstQueryPartBuilder selectAll() {
        final FinalQueryPartBuilder finalBuilder = new FinalQueryPartBuilder();
        finalBuilder.setFields(new String[]{"*"});

        return new FirstQueryPartBuilder(finalBuilder);
    }
}
