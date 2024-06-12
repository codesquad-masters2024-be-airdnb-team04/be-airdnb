package com.airdnb.clone.domain.reservation;

import com.airdnb.clone.domain.reservation.request.ReservationDeleteRequest;
import com.airdnb.clone.domain.reservation.request.ReservationSaveRequest;
import com.airdnb.clone.domain.reservation.request.ReservationUpdateRequest;
import com.airdnb.clone.domain.reservation.response.ReservationResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ReservationResponse save(@RequestBody ReservationSaveRequest request) {
        return reservationService.save(request);
    }

    @GetMapping("/{memberId}")
    public List<ReservationResponse> findReservationsByMemberId(@PathVariable Long memberId) {
        return reservationService.findReservationsByMemberId(memberId);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ReservationUpdateRequest request) {
        reservationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestBody ReservationDeleteRequest request) {
        reservationService.delete(request);
    }
}
