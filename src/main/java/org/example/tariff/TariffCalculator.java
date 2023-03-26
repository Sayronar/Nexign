package org.example.tariff;

import org.example.cdr.Cdr;

public class TariffCalculator {
    public static double calculateCostByTariffCod(Cdr CDR, long summaryDuration) {
        UnlimitedTariff unlimitedTariff = new UnlimitedTariff();
        PerMinuteTariff perMinuteTariff = new PerMinuteTariff();
        RegularTariff regularTariff = new RegularTariff();
        double cost = 0;
        switch (CDR.getTariffCode()) {
            case ("06"):
                cost = unlimitedTariff.calculateCost(CDR.getStartTime(), CDR.getEndTime(), summaryDuration, CDR);
                break;
            case ("03"):
                cost = perMinuteTariff.calculateCost(CDR.getStartTime(), CDR.getEndTime(), summaryDuration, CDR);
                break;
            case ("11"):
                cost = regularTariff.calculateCost(CDR.getStartTime(), CDR.getEndTime(), summaryDuration, CDR);
                break;
        }
        return cost;
    }
}
