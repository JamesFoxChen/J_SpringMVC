package com.james.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportUtil {

	public static <T> void ExportExcel(String[] titles, ArrayList<T> list, ServletOutputStream outputStream) {
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = new XSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		// Sheet名称，可以自定义中文名称
		XSSFSheet sheet = workBook.createSheet("Sheet1");
		ExportInternalUtil exportUtil = new ExportInternalUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		XSSFCell cell = null;

		// 输出标题
		for (int i = 0; i < titles.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titles[i]);
		}
		// 构建表体数据
		for (int i = 0; i < list.size(); i++) {
			// 创建行
			XSSFRow bodyRow = sheet.createRow(i + 1);
			// Object item = list.get(j);

			Class bean = list.get(i).getClass();
			Field[] fs = bean.getDeclaredFields();
			for (int j = 0; j < fs.length; j++) {
				Field f = fs[j];
				f.setAccessible(true); // 设置些属性是可以访问的
				String val = null;
				try {
					val = (String) f.get(bean);
				} catch (Exception e) {
					e.printStackTrace();

					// 创建列
					cell = bodyRow.createCell(j);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(val);
				}

				/*
				 * cell = bodyRow.createCell(0); cell.setCellStyle(bodyStyle);
				 * cell.setCellValue(user.getLastIp());
				 * 
				 * cell = bodyRow.createCell(1); cell.setCellStyle(bodyStyle);
				 * cell.setCellValue(user.getLastVisit());
				 * 
				 * cell = bodyRow.createCell(2); cell.setCellStyle(bodyStyle);
				 * cell.setCellValue(user.getPassword());
				 * 
				 * cell = bodyRow.createCell(3); cell.setCellStyle(bodyStyle);
				 * cell.setCellValue(user.getUserName());
				 * 
				 * cell = bodyRow.createCell(4); cell.setCellStyle(bodyStyle);
				 * cell.setCellValue(user.getUserId());
				 */
			}
		}

		try {
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
