package me.imatveev.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.Arrays;
import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryPredicate {
    String predicate;
    //or
    QueryPredicateMode mode;
    List<QueryPredicate> predicates;

    public static QueryPredicate of(@NonNull String predicate) {
        return new QueryPredicate(predicate, null, null);
    }

    public static QueryPredicate or(@NonNull QueryPredicate... predicates) {
        return new QueryPredicate(
                null,
                QueryPredicateMode.OR,
                Arrays.stream(predicates).toList()
        );
    }

    public static QueryPredicate and(@NonNull QueryPredicate... predicates) {
        return new QueryPredicate(
                null,
                QueryPredicateMode.AND,
                Arrays.stream(predicates).toList()
        );
    }

    @Override
    public String toString() {
        if (predicate != null) {
            return predicate;
        }

        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < predicates.size(); ++i) {
            builder.append("(")
                    .append(predicates.get(i))
                    .append(")");

            if (i != predicates.size() - 1) {
                builder.append(" ")
                        .append(mode)
                        .append(" ");
            }
        }

        return builder.toString();
    }
}
