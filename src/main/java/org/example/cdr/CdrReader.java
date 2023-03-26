package org.example.cdr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CdrReader {
    public List<Cdr> readCdr(String path) throws IOException {
        try (var stream = Files.lines(Paths.get(path))) {
            return stream.map(this::parseCall)
                    .collect(Collectors.toList());
        }
    }
    private Cdr parseCall(String line) {
        String[] fields = line.split(",");
        String callType = fields[0].trim();
        String phoneNumber = fields[1].trim();
        String startTime = fields[2].trim();
        String endTime = fields[3].trim();
        String tariffCode = fields[4].trim();
        return new Cdr(callType, phoneNumber, startTime, endTime, tariffCode);
    }
}





