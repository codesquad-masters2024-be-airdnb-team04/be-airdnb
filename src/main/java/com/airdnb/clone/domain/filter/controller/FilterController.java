package com.airdnb.clone.domain.filter.controller;

import com.airdnb.clone.domain.filter.service.FilterService;
import com.airdnb.clone.domain.stay.controller.dto.response.StayDetailResponse;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FilterController {

    private static final long DEFAULT_INTERVAL_DAYS = 7L;
    private final FilterService filterService;

    @GetMapping("/stays")
    public Slice<StayDetailResponse> getAvailableStaysByCursor(
            @RequestParam(required = false) LocalDate checkInDate,
            @RequestParam(required = false) LocalDate checkOutDate,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer guestCount,
            @RequestParam(required = false, defaultValue = "37.490776") Double latitude,
            @RequestParam(required = false, defaultValue = "127.033480") Double longitude,
            @RequestParam(required = false, defaultValue = "50000") Integer radius,
            @RequestParam(required = false, defaultValue = "0") Long cursorId,
            @PageableDefault(size = 15) Pageable pageable) {
        if (checkInDate == null) {
            checkInDate = LocalDate.now();
        }
        if (checkOutDate == null) {
            checkOutDate = checkInDate.plusDays(DEFAULT_INTERVAL_DAYS);
        }

        return filterService.getAvailableStaysByCursor(checkInDate, checkOutDate, minPrice, maxPrice, guestCount,
                latitude, longitude, radius, cursorId, pageable);
    }

    @GetMapping("/stays/offset")
    public Page<StayDetailResponse> getAvailableStaysByOffset(
            @RequestParam(required = false) LocalDate checkInDate,
            @RequestParam(required = false) LocalDate checkOutDate,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer guestCount,
            @RequestParam(required = false, defaultValue = "37.490776") Double latitude,
            @RequestParam(required = false, defaultValue = "127.033480") Double longitude,
            @RequestParam(required = false, defaultValue = "50000") Integer radius,
            @PageableDefault(size = 15) Pageable pageable) {
        if (checkInDate == null) {
            checkInDate = LocalDate.now();
        }
        if (checkOutDate == null) {
            checkOutDate = checkInDate.plusDays(DEFAULT_INTERVAL_DAYS);
        }

        return filterService.getAvailableStaysByOffset(checkInDate, checkOutDate, minPrice, maxPrice, guestCount,
                latitude, longitude, radius, pageable);
    }
}
