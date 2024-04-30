package com.ivo.mas.system.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelModelFormat {

	/*
	 * ��ȡδ���ܵ�excel����
	 *
	 * @param file excel�ļ�
	 */
	public void readExcel(File file, int sheetIndex, int startReadLine, int tailLine) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(file);
			readModelExcel(wb, sheetIndex, startReadLine, tailLine);
		} catch (InvalidFormatException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}

	/**
	 * ��ȡexcel�ļ�
	 * 
	 * @param wb
	 * @param sheetIndex    sheetҳ�±꣺��0��ʼ
	 * @param startReadLine ��ʼ��ȡ����:��0��ʼ
	 * @param tailLine      ȥ������ȡ����
	 */
	public List<JSONObject> readModelExcel(Workbook wb, int sheetIndex, int startReadLine, int tailLine) {

		Sheet sheet = wb.getSheetAt(sheetIndex);
		String sheetName = sheet.getSheetName();
		int a = sheet.getNumMergedRegions();
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		Row row = null;
		String cellValue = "";
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			row = sheet.getRow(i);
			JSONObject object = new JSONObject();
			Cell cell = row.getCell(1);
			cellValue = getCellValueWithType(cell);
			object.put("data",cellValue);
			jsonObjects.add(object);
		}
		return jsonObjects;
	}

	public String getSubUtilSimple(String soap, String rgex) {
		Pattern pattern = Pattern.compile(rgex);// ƥ���ģʽ
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			return m.group(1);
		}
		return null;
	}

	public String getCellValueWithType(Cell cell) {
		String cellValue = "";
		if (null != cell) {
			// �������ж����ݵ�����
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC: // ����

				if (0 == cell.getCellType()) {// �жϵ�Ԫ��������Ƿ���NUMERIC����'
					if (HSSFDateUtil.isCellDateFormatted(cell)) {// �ж��Ƿ�Ϊ��������
						Date date = cell.getDateCellValue();
						DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						cellValue = formater.format(date);
					} else {
						DecimalFormat df = new DecimalFormat("0");
		                cellValue = df.format(cell.getNumericCellValue());
					}
				}
				break;

			case HSSFCell.CELL_TYPE_STRING: // �ַ���
				cellValue = cell.getStringCellValue();
				break;

			case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
				cellValue = cell.getBooleanCellValue() + "";
				break;

			case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ
				cellValue = cell.getCellFormula() + "";
				break;

			case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
				cellValue = "";
				break;

			case HSSFCell.CELL_TYPE_ERROR: // ����
				// cellValue = "�Ƿ��ַ�";
				cellValue = "";
				break;

			default:
				// cellValue = "δ֪����";
				cellValue = "";
				break;
			}
		}
		return cellValue;
	}

	/**
	 * ��ȡ�ϲ���Ԫ���ֵ
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public String getMergedRegionValue(Sheet sheet, int row, int column) {

		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}

	/**
	 * �жϺϲ�����
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isMergedRow(Sheet sheet, int row, int column) {

		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row == firstRow && row == lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * �ж�ָ���ĵ�Ԫ���Ƿ��Ǻϲ���Ԫ��
	 * 
	 * @param sheet
	 * @param row    ���±�
	 * @param column ���±�
	 * @return
	 */
	public List<Integer> isMergedRegion(Sheet sheet, int row, int column) {

		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					List<Integer> spanInfo = new ArrayList<Integer>();
					spanInfo.add(firstRow);
					spanInfo.add(lastRow - firstRow + 1);
					return spanInfo;
				}
			}
		}
		return null;
	}

	/**
	 * �ж�sheetҳ���Ƿ��кϲ���Ԫ��
	 * 
	 * @param sheet
	 * @return
	 */
	public boolean hasMerged(Sheet sheet) {
		return sheet.getNumMergedRegions() > 0 ? true : false;
	}

	/**
	 * �ϲ���Ԫ��
	 * 
	 * @param sheet
	 * @param firstRow ��ʼ��
	 * @param lastRow  ������
	 * @param firstCol ��ʼ��
	 * @param lastCol  ������
	 */
	public void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}

	/**
	 * ��ȡ��Ԫ���ֵ
	 * 
	 * @param cell
	 * @return
	 */
	public String getCellValue(Cell cell) {

		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			return cell.getStringCellValue();

		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			return String.valueOf(cell.getNumericCellValue());

		}
		return "";
	}

}
