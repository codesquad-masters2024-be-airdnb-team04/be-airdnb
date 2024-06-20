package com.airdnb.clone.data;

import com.airdnb.clone.data.DummyAmenityGenerator.DummyAmenity;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DummyTypeGenerator {

    public static List<DummyType> generate() {
        List<DummyType> list = Arrays.stream(DummyType.values())
                .collect(Collectors.toList());
        int first = new Random().nextInt(0, 15);
        int second = new Random().nextInt(0, 15);

        return list.subList(first, second);
    }

    public enum DummyType {
        원룸, 투룸, 쓰리룸, 한옥, 기상천외한숙소, 풍차, 시골, 수영장, 해변, 최고의전망, 캐슬, 호수근처, 통나무, 농장, 키즈, 인기급상승
    }
}
