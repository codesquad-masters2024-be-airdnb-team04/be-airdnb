package com.airdnb.clone.domain.reservation;

import com.airdnb.clone.domain.reservation.dto.ReservationResponse;
import com.airdnb.clone.domain.reservation.dto.ReservationSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private ReservationService reservationService;

    @GetMapping("/reservations")
    public ReservationResponse saveReservation(ReservationSaveRequest request) {
        return reservationService.save(request);
    }
}
