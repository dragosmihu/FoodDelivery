package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.zip.Adler32;

public class AuditService {
    public static AuditService instance = null;
    private AuditService(){}

    public static AuditService getInstance(){
        if (instance == null) instance = new AuditService();
        return instance;
    }

    public static void audit(String feature) throws IOException {
        BufferedWriter auditCSV = new BufferedWriter(new FileWriter("csv/Audit.csv", true));
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        auditCSV.append(feature).append(",").append(String.valueOf(timeStamp)).append("\n");
        auditCSV.flush();
        auditCSV.close();

    }
}
