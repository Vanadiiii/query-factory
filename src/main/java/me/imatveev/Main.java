package me.imatveev;


import static me.imatveev.query.QueryPredicate.and;
import static me.imatveev.query.QueryPredicate.of;
import static me.imatveev.query.QueryPredicate.or;
import static me.imatveev.query.SelectQueryFactory.select;

public class Main {
    public static void main(String[] args) {
        final String query =
                select(true, "hotel.id", "room.name", "rate.value")
                        .from("hotels", "hotel")
                        .join("room", "hotel.id = room.id")
                        .join("rate", "hotel.id = rate.id")
                        .where(
                                and(
                                        or(
                                                of("hotel.id is not null"),
                                                of("room.id is not null"),
                                                of("rate.value > ?")
                                        ),
                                        of("hotel.name like '%?%'")
                                )
                        )
                        .build(true, 123, "ROYAL");

        System.out.println(query);
    }
}