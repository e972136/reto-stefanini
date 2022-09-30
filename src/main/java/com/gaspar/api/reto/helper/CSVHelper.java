package com.gaspar.api.reto.helper;


import com.gaspar.api.reto.entity.Reparacion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE="text/csv";
    static String[] HEADERs = { "IDReparacion", "Descripcion", "Cantidad", "ValorUnitario","CreadoEn" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Reparacion> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Reparacion> tutorials = new ArrayList<Reparacion>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (CSVRecord csvRecord : csvRecords) {
                try{
                Reparacion tutorial = Reparacion.builder()
                        .IdReparacion(Integer.parseInt(csvRecord.get("IDReparacion")))
                        .descripcion(csvRecord.get("Descripcion"))
                        .cantidad(Integer.parseInt(csvRecord.get("Cantidad")))
                        .valorUnitario(new BigDecimal(csvRecord.get("ValorUnitario")))
                        .creadoEn(LocalDate.parse(csvRecord.get("CreadoEn"),formatter))
                        .build();
                tutorials.add(tutorial);
                }catch (Exception e){
                    System.out.println(e+""+csvRecord);
                }
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
