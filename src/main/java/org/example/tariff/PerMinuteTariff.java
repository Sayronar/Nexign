package org.example.tariff;

import org.example.cdr.Cdr;

import java.time.Duration;
import java.time.LocalDateTime;

public class PerMinuteTariff extends Tariff {
    private static final double MINUTE_COST = 1.5;

    public PerMinuteTariff() {
        super("03");
    }

    @Override
    public double calculateCost(LocalDateTime startTime, LocalDateTime endTime, long summaryDuration, Cdr CDR) {
        Duration duration = Duration.between(startTime, endTime);
        long min = duration.toMinutesPart() + (duration.toSecondsPart() > 0 ? 1 : 0);
        // стоимость вычисляется как продолжительность звонка умноженная на стоимость минуты
        return min * MINUTE_COST;
    }
}
