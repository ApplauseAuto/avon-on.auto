package com.applause.auto.pageframework.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NotFoundException;

import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.testdata.TestConstants.Excel;

public class ExcelReader {
  private static final LogController LOGGER =
      new LogController(MethodHandles.lookup().lookupClass());

  /**
   * Get the data from the spreadsheet for the mobile journey tests
   *
   * @return List<ExcelRow>
   */
  public static HashMap<String, String> getLanguageStringsForMarket(String market) {
    HashMap<String, String> map = new HashMap<String, String>();
    LOGGER.info("Parsing markets and languages Excel document");
    String excelFilePath = Excel.FILE_LOCATION;
    Row row;

    try {
      FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
      Workbook workbook = getRelevantWorkbook(inputStream, excelFilePath);
      Sheet masterSheet = workbook.getSheetAt(Excel.DEFAULT_LANGUAGE_SHEET);

      // now populate the actual test data
      int rowCounter = 0;
      row = masterSheet.getRow(0);

      while (!cellIsEmpty(row, Excel.MARKET_COLUMN)) {
        row = masterSheet.getRow(rowCounter);
        if (row.getCell(Excel.MARKET_COLUMN).getStringCellValue().equals(market)) {
          Market.setlanguage(row.getCell(Excel.DEFAULT_LANGUAGE_COLUMN).getStringCellValue());
          Cell accountNumberCell = row.getCell(Excel.ACCOUNT_COLUMN);
          accountNumberCell.setCellType(CellType.STRING);
          Market.setAccountnumber(accountNumberCell.getStringCellValue());
          Market.setPassword(row.getCell(Excel.PASSWORD_COLUMN).getStringCellValue());
          return getLanguageStrings(
              workbook, row.getCell(Excel.DEFAULT_LANGUAGE_COLUMN).getStringCellValue());
        }
        rowCounter++;
        row = masterSheet.getRow(rowCounter);
      }

      throw new NotFoundException("Could not find market " + market + " in the excel file");

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to find the spreadsheet.");
    }
  }

  /**
   * Sets language, account number, and password for specified market
   *
   * @param market
   */
  public static void setDetailsForMarket(String market) {
    LOGGER.info("Parsing markets and languages Excel document");
    String excelFilePath = Excel.FILE_LOCATION;

    try {
      FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
      Workbook workbook = getRelevantWorkbook(inputStream, excelFilePath);
      Sheet masterSheet = workbook.getSheetAt(Excel.DEFAULT_LANGUAGE_SHEET);

      // now populate the actual test data
      int rowCounter = 0;
      Row row = masterSheet.getRow(0);

      while (!cellIsEmpty(row, Excel.MARKET_COLUMN)) {
        row = masterSheet.getRow(rowCounter);

        if (row.getCell(Excel.MARKET_COLUMN).getStringCellValue().equals(market)) {
          Market.setlanguage(row.getCell(Excel.DEFAULT_LANGUAGE_COLUMN).getStringCellValue());
          Cell accountNumberCell = row.getCell(Excel.ACCOUNT_COLUMN);
          accountNumberCell.setCellType(CellType.STRING);
          Market.setAccountnumber(accountNumberCell.getStringCellValue());
          Market.setPassword(row.getCell(Excel.PASSWORD_COLUMN).getStringCellValue());
          return;
        }

        rowCounter++;
        row = masterSheet.getRow(rowCounter);
      }

      throw new NotFoundException("Could not find market " + market + " in the excel file");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to find the spreadsheet.");
    }
  }

  private static HashMap<String, String> getLanguageStrings(Workbook workbook, String language) {
    HashMap<String, String> languageStrings = new HashMap<String, String>();
    Sheet masterSheet = workbook.getSheetAt(Excel.ELEMENT_STRINGS_SHEET);
    Row row;

    // get correct column index for the language
    // now populate the actual test data
    int columnCounter = 1;
    row = masterSheet.getRow(0);
    while (!cellIsEmpty(row, columnCounter)) {
      if (row.getCell(columnCounter).getStringCellValue().equals(language)) {
        // now populate the map with the key value pairs
        int rowCounter = 1;
        row = masterSheet.getRow(rowCounter);

        while (!cellIsEmpty(row, columnCounter)) {
          row = masterSheet.getRow(rowCounter);
          languageStrings.put(
              row.getCell(0).getStringCellValue(), row.getCell(columnCounter).getStringCellValue());
          rowCounter++;
          row = masterSheet.getRow(rowCounter);
        }

        return languageStrings;
      }
      columnCounter++;
    }

    throw new NotFoundException("Language " + language + " not found in the spreadsheet");
  }

  private static boolean cellIsEmpty(Row row, int columnNumber) {
    try {
      return row.getCell(columnNumber).getStringCellValue().isEmpty();
    } catch (Exception e) {
      return true;
    }
  }

  private static Workbook getRelevantWorkbook(FileInputStream inputStream, String excelFilePath) {
    Workbook workbook = null;
    try {
      if (excelFilePath.endsWith("xls")) {
        workbook = new HSSFWorkbook(inputStream);
      } else if (excelFilePath.endsWith("xlsx")) {
        workbook = new XSSFWorkbook(inputStream);
      } else {
        throw new IllegalArgumentException("Incorrect file format");
      }
    } catch (IOException e) {
    }

    return workbook;
  }

  private static String getCellValue(Cell cell) {
    try {
      cell.setCellType(CellType.STRING);
      int rowNum = cell.getRow().getRowNum();
      int cellNum = cell.getColumnIndex();

      /*
       * This is code to account for data that needs to be pulled from merged cells - without this, the
       * cell value returned is empty.
       */
      for (CellRangeAddress cellRangeAddress : cell.getSheet().getMergedRegions()) {
        int firstMergedRow = cellRangeAddress.getFirstRow();
        int lastMergedRow = cellRangeAddress.getLastRow();
        int firstMergedColumn = cellRangeAddress.getFirstColumn();
        int lastMergedColumn = cellRangeAddress.getLastColumn();

        /**
         * Checks to see if cell is within a merged region. If so, it will return the first row and
         * column of the merged region, which will contain the desired cell value.
         */
        if ((rowNum >= firstMergedRow && rowNum <= lastMergedRow)
            && (cellNum >= firstMergedColumn && cellNum <= lastMergedColumn)) {
          return cell.getSheet()
              .getRow(cellRangeAddress.getFirstRow())
              .getCell(cellRangeAddress.getFirstColumn())
              .getStringCellValue()
              .trim();
        }
      }

      return cell.getStringCellValue().trim();
    } catch (NullPointerException e) {
      LOGGER.debug("Cell returned null. Returning empty string.");
      return "";
    } catch (IllegalStateException illegal) {
      return String.valueOf(cell.getNumericCellValue()).replace(".0", "");
    }
  }
}
