package com.airdnb.clone.domain.booking.repository;

import com.airdnb.clone.domain.booking.entity.Booking;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, BookingQuerydsl {

    @Query("select count(b.id) from Booking b where b.stay.id = :stayId and b.checkIn < :checkOut and b.checkOut > :checkIn")
    Long countBookedStay(Long stayId, LocalDateTime checkIn, LocalDateTime checkOut);
}
