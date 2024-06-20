package com.airdnb.clone.domain.booking;

import static org.junit.jupiter.api.Assertions.*;

import com.airdnb.clone.domain.booking.controller.request.BookingSaveRequest;
import com.airdnb.clone.domain.booking.entity.Booking;
import com.airdnb.clone.domain.booking.repository.BookingRepository;
import com.airdnb.clone.domain.member.entity.Member;
import com.airdnb.clone.domain.stay.entity.RoomInformation;
import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.domain.stay.entity.Stay.Status;
import com.airdnb.clone.domain.stay.entity.StayFee;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void 순차적_예약_요청시_중복_예약은_일어나지_않는다() {
        LocalDateTime checkIn = LocalDateTime.of(LocalDate.of(2024, 8, 1), LocalTime.of(15, 0, 0));
        LocalDateTime checkOut = LocalDateTime.of(LocalDate.of(2024, 8, 2), LocalTime.of(12, 0, 0));

        BookingSaveRequest request = new BookingSaveRequest(1L, 1L, checkIn, checkOut, 2);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();
        long originCount = bookingRepository.count();

        IntStream.range(0, 10)
                .forEach(i -> {
                    try {
                        bookingService.create(request);
                        successCount.incrementAndGet();
                    } catch (Exception e) {
                        failCount.incrementAndGet();
                    }
                });

        System.out.println("successCount = " + successCount);
        System.out.println("failCount = " + failCount);

        long changeCount = bookingRepository.count();
        Assertions.assertThat(originCount + 1).isEqualTo(changeCount);
    }

    @Test
    void 락이_없는_예약_동시_요청은_중복_예약을_막지_못한다() throws InterruptedException {
        LocalDateTime checkIn = LocalDateTime.of(LocalDate.of(2024, 8, 1), LocalTime.of(15, 0, 0));
        LocalDateTime checkOut = LocalDateTime.of(LocalDate.of(2024, 8, 2), LocalTime.of(12, 0, 0));
        BookingSaveRequest request = new BookingSaveRequest(1L, 1L, checkIn, checkOut, 2);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        long originCount = bookingRepository.count();
        int requestCount = 30;

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch latch = new CountDownLatch(requestCount);

        for (int i = 0; i < requestCount; i++) {
            executorService.submit(() -> {
                try {
                    bookingService.create(request);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        System.out.println("successCount = " + successCount);
        System.out.println("failCount = " + failCount);

        long changeCount = bookingRepository.count();
        Assertions.assertThat(originCount + 1).isEqualTo(changeCount);
    }


}