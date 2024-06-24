package com.airdnb.clone.dummy.batch.steps;

import com.airdnb.clone.domain.booking.entity.Booking;
import com.airdnb.clone.domain.booking.entity.Booking.Status;
import com.airdnb.clone.domain.member.repository.MemberRepository;
import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.dummy.RandomNumberGenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@StepScope
public class BookingProcessor implements ItemProcessor<Stay, List<Booking>> {

    private static final AtomicInteger CURRENT_INDEX = new AtomicInteger(0);
    private static final int INCREMENT_STEP = 1;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Booking> process(Stay stay) throws Exception {
        if (72_000 < CURRENT_INDEX.getAndAdd(INCREMENT_STEP) && CURRENT_INDEX.get() < 90_000) {
            return null; // 제주도 패스
        }
        if (138_000 < CURRENT_INDEX.get() && CURRENT_INDEX.get() < 150_000) {
            return null; // 부산 패스
        }
        if (198_000 < CURRENT_INDEX.get() && CURRENT_INDEX.get() < 210_000) {
            return null; // 강원 패스
        }
        if (282_000 < CURRENT_INDEX.get() && CURRENT_INDEX.get() < 300_000) {
            return null; // 전국 패스
        }

        List<Booking> bookings = new ArrayList<>();

        int step = 1;
        for (int i = 6; i <= 8; i++) {
            int currentMonth = i;
            for (int j = 1; j <= 27; j += step + 1) {
                int nextCheckInDay = j;
                if (j % 7 == 1) {
                    step = 2;
                } else {
                    step = 1;
                }
                int nextCheckOutDat = j + step;
                LocalDateTime checkIn = LocalDate.of(2024, currentMonth, nextCheckInDay)
                        .atTime((stay.getCheckInTime()));
                LocalDateTime checkOut = LocalDate.of(2024, currentMonth, nextCheckOutDat)
                        .atTime((stay.getCheckOutTime()));

                Booking booking = Booking.builder()
                        .member(memberRepository.findById(RandomNumberGenerator.generateLong(1, 25000 - 1))
                                .orElseThrow())
                        .stay(stay)
                        .status(Status.RESERVED)
                        .guestCount(RandomNumberGenerator.generateInt(1, 3))
                        .totalRate(RandomNumberGenerator.generateLongByInterval(1_000_000L, 10_000_000L, 10_000))
                        .checkIn(checkIn)
                        .checkOut(checkOut)
                        .build();

                bookings.add(booking);
            }
        }

        return bookings;
    }
}
