package org.example.tariff;

import org.example.cdr.Cdr;

import java.time.LocalDateTime;

public class UnlimitedTariff extends Tariff {
    private static final double MINUTE_COST = 1.0;
    private static final int FREE_MINUTES = 300;
    private static final double MONTHLY_COST = 100.0;

    public UnlimitedTariff() {
        super("06");
    }

    @Override
    public double calculateCost(LocalDateTime startTime, LocalDateTime endTime, long summaryDuration, Cdr CDR) {
        long summaryDurationInMinutes = summaryDuration / 60 + (summaryDuration % 60 > 0 ? 1 : 0);

        if (summaryDurationInMinutes <= FREE_MINUTES) {
            return 0.0;
        } else {
            double cost = MONTHLY_COST;
            cost += (summaryDurationInMinutes - FREE_MINUTES) * MINUTE_COST;
            return cost;
        }
    }
}