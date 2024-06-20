package com.airdnb.clone.data;

import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.domain.stay.repository.StayRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyStayService {

    @Autowired
    private StayRepository stayRepository;

    @PostConstruct
    public void init() {
        List<Stay> stays = IntStream.range(0, 10000)
                .mapToObj(i -> DummyStayGenerator.generate())
                .collect(Collectors.toList());

        stayRepository.saveAll(stays);
    }
}
