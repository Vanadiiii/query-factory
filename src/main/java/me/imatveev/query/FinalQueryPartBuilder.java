package me.imatveev.query;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Data
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
public class FinalQueryPartBuilder {
    private boolean distinct;
    private String[] fields;
    private String schema;
    private String table;
    private Map<JoinPart, QueryPredicate> tablePredicateJoinMap = new LinkedHashMap<>();
    private QueryPredicate wherePredicate;

    public String build() {
        return this.build(false);
    }

    public String build(boolean pretty, Object... params) {
        final StringBuilder builder = new StringBuilder("SELECT ");

        if (distinct) {
            builder.append("DISTINCT ");
        }

        for (int i = 0; i < fields.length - 1; ++i) {
            builder.append(fields[i].toUpperCase(Locale.ROOT))
                    .append(", ");
        }
        builder.append(fields[fields.length - 1].toUpperCase(Locale.ROOT))
                .append(' ');

        if (pretty) {
            builder.append('\n');
        }

        builder.append("FROM ")
                .append(schema.toUpperCase(Locale.ROOT))
                .append('.')
                .append(table.toUpperCase(Locale.ROOT))
                .append(" ");

        tablePredicateJoinMap
                .forEach((joinPart, predicate) ->
                        {
                            if (pretty) {
                                builder.append('\n');
                                builder.append("  ");
                            }
                            builder.append(joinPart.join())
                                    .append("JOIN ")
                                    .append(schema.toUpperCase(Locale.ROOT))
                                    .append('.')
                                    .append(joinPart.table().toUpperCase(Locale.ROOT))
                                    .append(" ON ")
                                    .append(predicate)
                                    .append(" ");
                        }
                );

        if (wherePredicate != null) {
            if (pretty) {
                builder.append('\n');
            }
            builder.append("WHERE ")
                    .append(wherePredicate);
        }

        if (params != null && params.length > 0) {
            for (Object param : params) {
                final int paramIdx = builder.indexOf("?");

                final String value;
                if (param == null) {
                    value = "null";
                } else {
                    value = param.toString();
                }

                builder.replace(paramIdx, paramIdx + 1, value);
            }

            if (builder.indexOf("?") > 0) {
                throw new IllegalArgumentException("Wrong param's count");
            }
        }

        return builder.append(';')
                .toString();
    }
}
