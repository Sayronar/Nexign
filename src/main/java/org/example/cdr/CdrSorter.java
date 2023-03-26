package org.example.cdr;

import org.example.tariff.PerMinuteTariff;
import org.example.tariff.RegularTariff;
import org.example.tariff.UnlimitedTariff;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CdrSorter {

    public void generatePhoneNumberReports(CdrReader cdrReader, String input, String output) throws IOException {
        List<Cdr> Cdrs = cdrReader.readCdr(input);
        HashSet<String> uniquePhoneNumbers = new HashSet<>(Cdrs.stream().map(Cdr::getPhoneNumber).collect(Collectors.toList()));

        for (String phoneNumber : uniquePhoneNumbers) {
            List<Cdr> filteredCdrs = getUniquePhoneNumbers(Cdrs, phoneNumber);
            double totalCost = calculateTotalCostForCdrs(filteredCdrs);
            ReportWriter.writeReport(phoneNumber, filteredCdrs, totalCost, output);
        }
    }

    private List<Cdr> getUniquePhoneNumbers(List<Cdr> Cdrs, String phoneNumber) {
        return Cdrs.stream()
                .filter(call -> call.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    private double calculateTotalCostForCdrs(List<Cdr> Cdrs) {
        double totalCost = 0;
        UnlimitedTariff unlimitedTariff = new UnlimitedTariff();
        PerMinuteTariff perMinuteTariff = new PerMinuteTariff();
        RegularTariff regularTariff = new RegularTariff();

        for (Cdr CDR : Cdrs) {
            long summaryDuration = CDR.getDuration().getSeconds();
            switch (CDR.getTariffCode()) {
                case "06":
                    totalCost += unlimitedTariff.calculateCost(CDR.getStartTime(), CDR.getEndTime(), summaryDuration, CDR);
                    if (totalCost == 0) {
                        totalCost = 100;
                    }
                    break;
                case "03":
                    totalCost += perMinuteTariff.calculateCost(CDR.getStartTime(), CDR.getEndTime(), summaryDuration, CDR);
                    break;
                case "11":
                    totalCost += regularTariff.calculateCost(CDR.getStartTime(), CDR.getEndTime(), summaryDuration, CDR);
                    break;
            }
        }
        return totalCost;
    }
}