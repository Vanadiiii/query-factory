package me.imatveev.query;

import lombok.NonNull;

record JoinPart(@NonNull String table,
                @NonNull Join join) {
}
