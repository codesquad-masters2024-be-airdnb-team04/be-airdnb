package com.airdnb.clone.data;

import com.airdnb.clone.domain.member.entity.Member;

public class DummyMemberGenerator {
    private static int count = 0;

    public static Member generate() {
        return Member.builder()
                .name("멤버" + ++count)
                .build();
    }
}
