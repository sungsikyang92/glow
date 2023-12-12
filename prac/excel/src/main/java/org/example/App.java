package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static String filePath = "/Users/ssiky/Projects/glow/prac";

    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    static String TIME = now.format(formatter);
    public static String fileNm = "Sea_South_Statistics_" + TIME + ".xlsx";
    public static void main( String[] args )
    {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet eventSituation = workbook.createSheet("이벤트상황");
        XSSFSheet eventStatus = workbook.createSheet("이벤트상태");

        Map<String, Object[]> statusData = new TreeMap<>();
        statusData.put("1", new Object[]{"Date", "비상벨", "화재 센서", "가스 센서", "셋톱박스1", "셋톱박스2", "CCTV1", "CCTV2"});
        for (int i = 2; i < 20; i++) {
            statusData.put(String.valueOf(i), new Object[]{TIME, "비상벨TEST"+i, "화재 센서TEST"+i , "가스 센서TEST"+i, "셋톱박스1_TEST"+i, "셋톱박스_TEST"+i, "CCTV_TEST"+i, "CCTV2_TEST"+i});
        }

        Set<String> keySet = statusData.keySet();
        int rowNum = 0;

        for (String key : keySet) {
            Row row = eventStatus.createRow(rowNum++);
            Object[] objArr = statusData.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(new File(filePath, fileNm));
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
