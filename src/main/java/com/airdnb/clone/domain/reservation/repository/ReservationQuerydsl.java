package com.airdnb.clone.domain.reservation.repository;

import com.airdnb.clone.domain.stay.entity.Stay;
import java.time.LocalDate;
import java.util.List;

public interface ReservationQuerydsl {

    List<Stay> findAvailableStays(LocalDate checkInDate, LocalDate checkOutDate, Integer minPrice, Integer maxPrice, Integer guestCount);
}
