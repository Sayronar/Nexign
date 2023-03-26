package org.example.cdr;

import org.example.tariff.TariffCalculator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportWriter {
    public static void writeReport(String phoneNumber, List<Cdr> Cdrs, double totalCost, String output) throws IOException {
        String fileName = output + phoneNumber + ".txt";
        String tariffIndex = Cdrs.get(0).getTariffCode();

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write("Tariff index: " + tariffIndex + "\n");
            fileWriter.write("----------------------------------------------------------------------------\n");
            fileWriter.write("Report for phone number " + phoneNumber + ":\n");
            fileWriter.write("----------------------------------------------------------------------------\n");
            fileWriter.write("| Call Type |   Start Time        |     End Time        | Duration | Cost  |\n");
            fileWriter.write("----------------------------------------------------------------------------\n");
            for (Cdr CDR : Cdrs) {
                fileWriter.write(formatCall(CDR));
            }
            fileWriter.write("----------------------------------------------------------------------------\n");
            fileWriter.write(String.format("|%46s |%10.2f rubles |\n", "Total Cost:", totalCost));
            fileWriter.write("----------------------------------------------------------------------------\n");
        }
    }

    private static String formatCall(Cdr CDR) {
        return String.format("|     %02d    | %s | %s | %s | %6.2f |\n",
                CDR.getType().equals(Cdr.TYPE_INCOMING) ? 1 : 2,
                CDR.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                CDR.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                String.format("%02d:%02d:%02d", CDR.getDuration().toHours(), CDR.getDuration().toMinutesPart(), CDR.getDuration().toSecondsPart()),
                TariffCalculator.calculateCostByTariffCod(CDR, CDR.getDuration().getSeconds()));
    }
}
