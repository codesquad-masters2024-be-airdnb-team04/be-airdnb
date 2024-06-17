package com.airdnb.clone.domain.filter.repository;

import static com.airdnb.clone.domain.reservation.entity.QReservation.reservation;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.TimeTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationQuerydslImpl implements ReservationQuerydsl{

    private final JPAQueryFactory jpaQueryFactory;

    public List<Long> findUnavailableStayIdsByCheckInOut(LocalDate checkInDate, LocalDate checkOutDate) {
        return jpaQueryFactory
                .select(reservation.stay.id)
                .distinct()
                .from(reservation)
                .where(matchesCheckInAndCheckOut(checkInDate, checkOutDate))
                .fetch();
    }

    private BooleanExpression matchesCheckInAndCheckOut(LocalDate checkInDate, LocalDate checkOutDate) {

        if (checkInDate == null || checkOutDate == null) {
            return null;
        }
        if (checkInDate.isAfter(checkOutDate)) {
            throw new IllegalArgumentException();
        }

        DateTemplate<LocalDate> reservationCheckInDate = Expressions.dateTemplate(LocalDate.class,
                "CAST({0} AS DATE)", reservation.checkIn);

        TimeTemplate<LocalTime> reservationCheckInTime = Expressions.timeTemplate(LocalTime.class,
                "CAST({0} AS TIME)", reservation.checkIn);

        DateTemplate<LocalDate> reservationCheckOutDate = Expressions.dateTemplate(LocalDate.class,
                "CAST({0} AS DATE)", reservation.checkOut);

        TimeTemplate<LocalTime> reservationCheckOutTime = Expressions.timeTemplate(LocalTime.class,
                "CAST({0} AS TIME)", reservation.checkOut);

      return  reservationCheckInDate.lt(checkOutDate)
                .or(reservationCheckInDate.eq(checkOutDate).and(reservationCheckInTime.lt(reservation.stay.checkOutTime)))
                .and(reservationCheckOutDate.gt(checkInDate)
                        .or(reservationCheckOutDate.eq(checkInDate).and(reservationCheckOutTime.gt(reservation.stay.checkInTime))));
    }
}
