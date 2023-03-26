package org.example.cdr;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cdr {
    private String type;
    private String phoneNumber;
    private LocalDateTime startTime;
    public static final String TYPE_INCOMING = "01";
    public static final String TYPE_OUTGOING = "02";
    private LocalDateTime endTime;
    private Duration duration;
    /*private String startTime;
    private String endTime;*/
    private String tariffCode;

    public Cdr(String type, String phoneNumber, String startTime, String endTime, String tariffCode) {
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.startTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        this.endTime = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        /*this.startTime = startTime;
        this.endTime = endTime;*/
        this.duration = Duration.between(this.startTime, this.endTime);
        this.tariffCode = tariffCode;
    }

    public String getType() {
        return type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getTariffCode() {
        return tariffCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cdr CDR = (Cdr) o;
        return Objects.equals(type, CDR.type) && Objects.equals(phoneNumber, CDR.phoneNumber) && Objects.equals(startTime, CDR.startTime) && Objects.equals(endTime, CDR.endTime) && Objects.equals(tariffCode, CDR.tariffCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, phoneNumber, startTime, endTime, tariffCode);
    }

    @Override
    public String toString() {
        return type + " , " +
                phoneNumber + " , " +
                startTime + " , " +
                endTime + " , " +
                tariffCode;
    }
}
