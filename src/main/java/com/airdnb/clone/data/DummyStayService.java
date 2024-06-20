package com.airdnb.clone.data;

import com.airdnb.clone.domain.stay.entity.Stay;
import com.airdnb.clone.domain.stay.repository.StayRepository;
import jakarta.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyStayService {

    @Autowired
    private StayRepository stayRepository;

    @PostConstruct
    public void init() {
        List<Stay> stays = IntStream.range(0, 100000)
                .mapToObj(i -> {
                    if (0 <= i && i <= 30000) {
                        return DummyStayGenerator.generateJeju();
                    }
                    else if (30001 <= i && i <= 50000) {
                        return DummyStayGenerator.generateBusan();
                    }
                    else if (50001 <= i && i <= 100000) {
                        return DummyStayGenerator.generateGangwon();
                    }
                    else return DummyStayGenerator.generate();
                })
                .collect(Collectors.toList());

        stayRepository.saveAll(stays);
    }


    public static void main(String[] args) {
        List<Stay> stays = IntStream.range(0, 200)
                .mapToObj(i -> {
                    if (0 <= i && i <= 60) {
                        return DummyStayGenerator.generateJeju();
                    }
                    if (61 <= i && i <= 80) {
                        return DummyStayGenerator.generateBusan();
                    }
                    if (81 <= i && i <= 100) {
                        return DummyStayGenerator.generateGangwon();
                    }
                    return DummyStayGenerator.generate();
                })
                .collect(Collectors.toList());

        try {
            writeStaysToCSV(stays, "stays.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void writeStaysToCSV(List<Stay> stays, String fileName) throws IOException {
        try (FileWriter out = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                     .withHeader("ID", "Location", "Name", "Description"))) {

            for (Stay stay : stays) {
                printer.printRecord(stay.getPoint().getY(), stay.getPoint().getX(), stay.getDescription());
            }
        }
    }
}
