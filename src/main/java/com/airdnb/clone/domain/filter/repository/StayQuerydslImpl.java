package com.airdnb.clone.domain.filter.repository;

import static com.airdnb.clone.domain.stay.entity.QStay.stay;

import com.airdnb.clone.domain.stay.entity.Stay;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StayQuerydslImpl implements StayQuerydsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Stay> findAvailableStays(List<Long> unavailableStayIds, Integer minPrice, Integer maxPrice, Integer guestCount) {
        return jpaQueryFactory
                .select(stay)
                .from(stay)
                .where(matchesUnavailableStayIds(unavailableStayIds),
                        matchesTotalPrice(minPrice, maxPrice),
                        matchesGuestCount(guestCount))
                .fetch();
    }

    private BooleanExpression matchesUnavailableStayIds(List<Long> unavailableStayIds) {
        if (unavailableStayIds.isEmpty()) {
            return null;
        }
        return stay.id.notIn(unavailableStayIds);
    }

    private BooleanExpression matchesTotalPrice(Integer minPrice, Integer maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return null;
        }
        if (minPrice == null) {
            return stay.fee.perNight.loe(maxPrice);
        }
        if (maxPrice == null) {
            return stay.fee.perNight.goe(minPrice);
        }
        return stay.fee.perNight.between(minPrice, maxPrice);
    }

    private BooleanExpression matchesGuestCount(Integer guestCount) {
        if (guestCount == null) {
            return null;
        }
        return stay.roomInfo.guest.guestCount.goe(guestCount);
    }
}
