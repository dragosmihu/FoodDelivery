package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;

public final class AuditService {
    public static void audit(String feature) throws IOException {
        BufferedWriter auditCSV = new BufferedWriter(new FileWriter("csv/Audit.csv", true));
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        auditCSV.append(feature).append(",").append(String.valueOf(timeStamp)).append("\n");
        auditCSV.flush();
        auditCSV.close();

    }
}
