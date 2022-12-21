package com.elbatech.hr.helper;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.elbatech.hr.model.Department;
import com.elbatech.hr.model.Employee;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	//static String[] HEADERs = { "emp_id", "first_name", "last_name", "email_id" };
	static String SHEET = "employees";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List [] excelToEmployees(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			List [] result=new List[2];
			List<Employee> employees = new ArrayList<>();
			List<Department> departments = new ArrayList<>();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

			int rowNumber = 0;
			for (Row currentRow : sheet) {
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Employee em = new Employee();
				Department d = new Department();

				int cellIdx = 0;
				for (Cell currentCell : currentRow) {
					currentCell.setCellType(CellType.STRING);

					if (rowNumber == 8 && cellIdx == 1) {
						cellIdx++;
						em.setManager("");
					}

					switch (cellIdx) {
					case 0:
						em.setName(currentCell.getStringCellValue());
						break;
					case 1:
						em.setManager(currentCell.getStringCellValue() + "");
						break;
					case 2:
						em.setUsername(currentCell.getStringCellValue());
						break;
					case 3:
						em.setEmail(currentCell.getStringCellValue());
						break;
					case 4:
						em.setDepartment(currentCell.getStringCellValue());
						break;
					case 5:
						em.setPhoneNumber(currentCell.getStringCellValue());
						break;
					case 6:
						em.setAddress(currentCell.getStringCellValue());
						break;
					case 7:
						em.setStartDate(convertStringToDate(currentCell.getStringCellValue()));
						break;
					case 8:
						em.setEndDate(convertStringToDate(currentCell.getStringCellValue()));
						em.setStatusEmployee((new Date()).compareTo(em.getEndDate()) < 0 ? "active" : "inactive");
						break;
					case 9:
						d.setDepartmentName(currentCell.getStringCellValue());
						break;
					case 10:
						d.setDepartmentLeader(currentCell.getStringCellValue());
						break;
					case 11:
						d.setDepartmentPhone(currentCell.getStringCellValue());
						break;
					default:
						break;
					}

					cellIdx++;
				}

				employees.add(em);
				if (d.getDepartmentName() != null)
					departments.add(d);
				rowNumber++;
			}


			workbook.close();
			result[0]=employees;
			result[1]=departments;
			return result;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

	public static Date convertStringToDate(String longString) {

		String year = longString.substring(0, 4);
		String month = longString.charAt(4) + "" + longString.charAt(5);
		String day = longString.charAt(6) + "" + longString.charAt(7);
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + day);
		} catch (Exception e) {
			date = new Date();
		}
		return date;
	}
}
