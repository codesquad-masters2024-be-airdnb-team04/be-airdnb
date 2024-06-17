package com.airdnb.clone.domain.filter.repository;

import com.airdnb.clone.domain.stay.entity.Stay;
import java.util.List;

public interface StayQuerydsl {

    List<Stay> findAvailableStays(List<Long> unavailableStayIds, Integer minPrice, Integer maxPrice, Integer guestCount);
}
