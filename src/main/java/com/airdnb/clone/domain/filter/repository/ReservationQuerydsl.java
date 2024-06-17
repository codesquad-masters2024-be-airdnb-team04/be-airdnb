package com.airdnb.clone.domain.filter.repository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationQuerydsl {

    List<Long> findUnavailableStayIdsByCheckInOut(LocalDate checkInDate, LocalDate checkOutDate);
}
