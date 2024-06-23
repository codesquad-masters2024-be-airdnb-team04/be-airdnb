package com.airdnb.clone.dummy.batch.steps;

import com.airdnb.clone.domain.booking.entity.Booking;
import com.airdnb.clone.domain.booking.entity.Booking.Status;
import com.airdnb.clone.domain.member.entity.Member;
import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.dummy.RandomNumberGenerator;
import io.jsonwebtoken.lang.Assert;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class BookingProcessor implements ItemProcessor<Stay, List<Booking>> {

    private List<Member> members;
    private StepExecution stepExecution;

    @BeforeStep
    public void saveMembers() {
        Assert.state(stepExecution != null, "stepContext is null");
        members = (List<Member>) stepExecution.getExecutionContext().get("members");
    }

    @Override
    public List<Booking> process(Stay stay) throws Exception {
        log.info("members {}", members);
        log.info("stay {}", stay);

        // TODO: 작업 중
        Integer randomMonth = RandomNumberGenerator.generateInt(6, 8); // 성수기: 6~8 월
        Integer randomCheckInDay = RandomNumberGenerator.generateIntByInterval(1, 30, 3);
        Integer randomCheckOutDay = RandomNumberGenerator.generateIntByInterval(randomCheckInDay, 30, 3);
        LocalDateTime randomCheckIn = LocalDate.of(2024, randomMonth, randomCheckInDay).atTime((stay.getCheckInTime()));
        LocalDateTime randomCheckOut = LocalDate.of(2024, randomMonth, randomCheckOutDay).atTime((stay.getCheckOutTime()));

        Booking booking = Booking.builder()
                .member(members.get(RandomNumberGenerator.generateInt(0, members.size() - 1)))
                .stay(stay)
                .status(Status.RESERVED)
                .guestCount(RandomNumberGenerator.generateInt(1, 3))
                .totalRate(RandomNumberGenerator.generateLongByInterval(1_000_000L, 10_000_000L, 10_000))
                .checkIn(randomCheckIn)
                .checkOut(randomCheckOut)
                .build();
    }
}
