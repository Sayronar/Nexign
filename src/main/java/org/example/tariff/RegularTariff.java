package org.example.tariff;

import org.example.cdr.CallType;
import org.example.cdr.Cdr;

import java.time.Duration;
import java.time.LocalDateTime;

public class RegularTariff extends Tariff {
    private static final double INCOMING_CALL_COST = 0.0;
    private static final double OUTGOING_CALL_COST_FIRST_100_MINUTES = 0.5;
    private static final double OUTGOING_CALL_COST_AFTER_100_MINUTES = 1.5;
    private static final int FREE_MINUTES = 100;

    public RegularTariff() {
        super("11");
    }

    @Override
    public double calculateCost(LocalDateTime startTime, LocalDateTime endTime, long summaryDuration, Cdr CDR) {
        // вычисляем продолжительность звонка в минутах
        long durationInSeconds = Duration.between(startTime, endTime).getSeconds();
        long durationInMinutes = (long) Math.ceil(durationInSeconds / 60.0);
        long summaryDurationInMinutes = (long) Math.ceil(summaryDuration / 60.0);
        // если звонок был входящим, то стоимость равна 0
        if (CDR.getType().equals(CallType.INCOMING.getValue())) {
            return INCOMING_CALL_COST;
        } else {
            // если звонок был исходящим и длился не более 100 минут, то стоимость равна продолжительности звонка умноженной на стоимость минуты
            if (summaryDurationInMinutes <= FREE_MINUTES) {
                return durationInMinutes * OUTGOING_CALL_COST_FIRST_100_MINUTES;
            } else {
                // если звонок был исходящим и длился более 100 минут, то стоимость равна стоимости первых 100 минут плюс стоимость оставшейся продолжительности звонка
                return OUTGOING_CALL_COST_AFTER_100_MINUTES * durationInMinutes;
            }
        }
    }
}