package me.imatveev.query;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecondQueryPartBuilder {
    private final FinalQueryPartBuilder builder;

    private SecondQueryPartBuilder join(@NonNull Join join,
                                        @NonNull String table,
                                        @NonNull String predicate) {
        builder.getTablePredicateJoinMap()
                .put(
                        new JoinPart(table, join),
                        QueryPredicate.of(predicate)
                );

        return new SecondQueryPartBuilder(builder);
    }

    private SecondQueryPartBuilder join(@NonNull Join join,
                                        @NonNull String table,
                                        @NonNull QueryPredicate predicate) {
        builder.getTablePredicateJoinMap()
                .put(
                        new JoinPart(table, join),
                        predicate
                );

        return new SecondQueryPartBuilder(builder);
    }

    public SecondQueryPartBuilder join(@NonNull String table,
                                       @NonNull QueryPredicate predicate) {
        return this.join(Join.INNER, table, predicate);
    }

    public SecondQueryPartBuilder leftJoin(@NonNull String table,
                                           @NonNull QueryPredicate predicate) {
        return this.join(Join.LEFT, table, predicate);
    }

    public SecondQueryPartBuilder rightJoin(@NonNull String table,
                                            @NonNull QueryPredicate predicate) {
        return this.join(Join.RIGHT, table, predicate);
    }

    public SecondQueryPartBuilder join(@NonNull String table,
                                       @NonNull String predicate) {
        return this.join(Join.INNER, table, predicate);
    }

    public SecondQueryPartBuilder leftJoin(@NonNull String table,
                                           @NonNull String predicate) {
        return this.join(Join.LEFT, table, predicate);
    }

    public SecondQueryPartBuilder rightJoin(@NonNull String table,
                                            @NonNull String predicate) {
        return this.join(Join.RIGHT, table, predicate);
    }

    public FinalQueryPartBuilder where(QueryPredicate predicate) {
        builder.setWherePredicate(predicate);

        return builder;
    }

    public String build() {
        return builder.build();
    }
}
