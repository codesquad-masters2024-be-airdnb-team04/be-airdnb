package com.airdnb.clone.domain.reservation;

import com.airdnb.clone.domain.reservation.request.ReservationDeleteRequest;
import com.airdnb.clone.domain.reservation.request.ReservationUpdateRequest;
import com.airdnb.clone.domain.reservation.response.ReservationResponse;
import com.airdnb.clone.domain.reservation.request.ReservationSaveRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private ReservationService reservationService;

    @GetMapping("/reservations")
    public ReservationResponse save(ReservationSaveRequest request) {
        return reservationService.save(request);
    }

    @GetMapping("/reservations/{memberId}")
    public List<ReservationResponse> findReservationsByMemberId(@RequestParam Long memberId) {
        return reservationService.findReservationsByMemberId(memberId);
    }

    @PatchMapping("/reservations")
    public void update(ReservationUpdateRequest request) {
        reservationService.update(request);
    }

    @DeleteMapping("/reservations")
    public void delete(ReservationDeleteRequest request) {
        reservationService.delete(request);
    }
}
