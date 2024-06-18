package com.airdnb.clone.domain.booking;

import com.airdnb.clone.domain.common.Guest;
import com.airdnb.clone.domain.member.entity.Member;
import com.airdnb.clone.domain.member.repository.MemberRepository;
import com.airdnb.clone.domain.booking.entity.Reservation;
import com.airdnb.clone.domain.booking.repository.BookingRepository;
import com.airdnb.clone.domain.booking.response.BookingResponse;
import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.domain.stay.repository.StayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookingService {

    private final BookingRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final StayRepository stayRepository;

    @Transactional
    public BookingResponse create(Reservation.ReservationBuilder builder, Long stayId, Long hostId) {
        Member member = memberRepository.findById(hostId)
                .orElseThrow();
        Stay stay = stayRepository.findById(stayId)
                .orElseThrow();
        Reservation entity = builder.member(member).stay(stay).build();
        Reservation saved = reservationRepository.save(entity);
        return BookingResponse.of(saved);
    }

    @Transactional
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    @Transactional
    public void edit(Long id, Guest guest) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow();

        reservation.changeGuest(guest);
    }

    public BookingResponse findById(Long id) {
        return reservationRepository.findById(id)
                .map(BookingResponse::of)
                .orElseThrow();
    }
}
