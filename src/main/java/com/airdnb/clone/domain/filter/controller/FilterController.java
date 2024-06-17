package com.airdnb.clone.domain.filter.controller;

import com.airdnb.clone.domain.filter.controller.request.AvailableStayRequest;
import com.airdnb.clone.domain.filter.service.FilterService;
import com.airdnb.clone.domain.stay.controller.dto.response.StayDetailResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FilterController {

    private final FilterService filterService;

    @GetMapping("/filter")
    public List<StayDetailResponse> getAvailableStays(@RequestBody AvailableStayRequest request) {
        return filterService.getAvailableStays(request);
    }
}
