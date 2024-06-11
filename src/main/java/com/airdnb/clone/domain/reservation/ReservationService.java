package com.airdnb.clone.domain.reservation;

import com.airdnb.clone.domain.member.MemberRepository;
import com.airdnb.clone.domain.member.entity.Member;
import com.airdnb.clone.domain.reservation.request.ReservationDeleteRequest;
import com.airdnb.clone.domain.reservation.request.ReservationUpdateRequest;
import com.airdnb.clone.domain.reservation.response.ReservationResponse;
import com.airdnb.clone.domain.reservation.request.ReservationSaveRequest;
import com.airdnb.clone.domain.reservation.entity.Reservation;
import com.airdnb.clone.domain.stay.StayRepository;
import com.airdnb.clone.domain.stay.entity.Stay;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private MemberRepository memberRepository;
    private StayRepository stayRepository;

    @Transactional
    public ReservationResponse save(ReservationSaveRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow();
        Stay stay = stayRepository.findById(request.stayId())
                .orElseThrow();
        Reservation entity = request.toEntity(member, stay);
        Reservation saved = reservationRepository.save(entity);
        return ReservationResponse.toDto(saved);
    }

    @Transactional
    public void delete(ReservationDeleteRequest request) {
        reservationRepository.deleteById(request.reservationId());
    }

    @Transactional
    public void update(ReservationUpdateRequest request) {
        Reservation entity = reservationRepository.findById(request.reservationId())
                .orElseThrow();
        entity.updateGuest(request.guestCount());
    }
}
