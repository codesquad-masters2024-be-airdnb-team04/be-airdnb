package com.airdnb.clone.domain.reservation;

import com.airdnb.clone.domain.member.MemberRepository;
import com.airdnb.clone.domain.member.entity.Member;
import com.airdnb.clone.domain.reservation.entity.Reservation;
import com.airdnb.clone.domain.reservation.request.ReservationSaveRequest;
import com.airdnb.clone.domain.reservation.request.ReservationUpdateRequest;
import com.airdnb.clone.domain.reservation.response.ReservationResponse;
import com.airdnb.clone.domain.stay.StayRepository;
import com.airdnb.clone.domain.stay.entity.Stay;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final StayRepository stayRepository;

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
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, ReservationUpdateRequest request) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow();

        reservation.updateGuest(request.guestCount());
    }

    public List<ReservationResponse> findReservationsByMemberId(Long memberId) {
        return reservationRepository.findAllByMemberId(memberId).stream()
                .map(ReservationResponse::toDto)
                .toList();
    }
}
