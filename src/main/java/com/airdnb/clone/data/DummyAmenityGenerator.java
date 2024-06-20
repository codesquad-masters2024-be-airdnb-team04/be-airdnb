package com.airdnb.clone.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DummyAmenityGenerator {

    public static List<DummyAmenity> generate() {
        List<DummyAmenity> list = Arrays.stream(DummyAmenity.values())
                .collect(Collectors.toList());
        int first = new Random().nextInt(0, 16);
        int second = new Random().nextInt(0, 16);

        return list.subList(first, second);
    }

    public enum DummyAmenity {
        헤어드라이기, 클렌징폼, 에어컨, 선풍기, 샴푸, 비데, 온수, 옷걸이, 침구, 담요, 다리미, 중앙난방, 구급상자, 무선인터넷, 주방, 인덕션, 전자레인지
    }
}
