package Services.ServicesImpl;

import DAO.StatisticalDAO;
import Model.Statistical;
import Services.StatisticalService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticalServiceImpl implements StatisticalService {
    private StatisticalDAO dao;
    public StatisticalServiceImpl() {
        dao = new StatisticalDAO();
    }
    @Override
    public List<Statistical> getSalary() {
        return dao.getSalary();
    }

    @Override
    public Boolean exportXls(String path) {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Lương");

        Row rowTitle = sheet.createRow(0);
        Cell cellTite = rowTitle.createCell(0);
        cellTite.setCellValue("DANH SÁCH LƯƠNG");

        Row rowHeader = sheet.createRow(5);

        Cell cellHeader = rowHeader.createCell(0);
        cellHeader.setCellValue("Mã nhân viên");

        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Họ tên");

        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue("Tháng");

        cellHeader = rowHeader.createCell(3);
        cellHeader.setCellValue("Quý");

        cellHeader = rowHeader.createCell(4);
        cellHeader.setCellValue("Năm");

        cellHeader = rowHeader.createCell(5);
        cellHeader.setCellValue("Lương");

        cellHeader = rowHeader.createCell(6);
        cellHeader.setCellValue("Ghi chú");

        int i = 5;

        List<Statistical> list = dao.getSalary();
        for (Statistical s : list) {
            Row row = sheet.createRow(i + 1);

            Cell cell = row.createCell(0);
            cell.setCellValue(s.getStaffId());

            cell = row.createCell(1);
            cell.setCellValue(s.getStaffName());

            cell = row.createCell(2);
            cell.setCellValue(s.getMonth());

            cell = row.createCell(3);
            cell.setCellValue(s.getQuarter());

            cell = row.createCell(4);
            cell.setCellValue(s.getYear());

            cell = row.createCell(5);
            cell.setCellValue(s.getSalary());

            cell = row.createCell(6);
            cell.setCellValue(s.getNote());

            i++;
        }

        try {
            FileOutputStream fos = new FileOutputStream(path + ".xlsx");

            workbook.write(fos);
            workbook.close();

            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
