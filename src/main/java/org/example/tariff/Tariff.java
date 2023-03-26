package org.example.tariff;

import org.example.cdr.Cdr;

import java.time.LocalDateTime;

public abstract class Tariff {
    private final String code;

    public Tariff(String code) {
        this.code = code;
    }

    // вычисление стоимости звонка по тарифу
    public abstract double calculateCost(LocalDateTime startTime, LocalDateTime endTime, long summaryDuration, Cdr CDR);

    public String getCode() {
        return code;
    }
}
