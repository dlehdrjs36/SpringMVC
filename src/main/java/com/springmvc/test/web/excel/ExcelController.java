package com.springmvc.test.web.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.test.fileupload.FileUploadDTO;
import com.springmvc.test.fileupload.FileUploadService;

@Controller
public class ExcelController {
	@Autowired
	FileUploadService fileUploadService;

	// 1. ViewResolver를 이용하지 않는 엑셀 방법.
	// xls 엑셀 파일 생성 , 출력
	@RequestMapping("/excelCreate.do")
	public void excelCreate(FileUploadDTO vo, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("EUC-KR");
		PrintWriter out = response.getWriter();

		// 엑셀 workbook 생성
		Workbook wb = new HSSFWorkbook();

		// xls 버전
		CellStyle cs = wb.createCellStyle();
		Font f2 = wb.createFont();
		f2.setFontName("궁서체");
		f2.setItalic(true);
		cs.setFont(f2);

		// 시트 추가, Excel 아래부분의 Sheet 이름 확인해보면 이해가능.
		wb.createSheet("first sheet");
		wb.createSheet();

		// 파일업로드 목록 출력( 날짜 별로 업로드가 발생한 count )
		List<Map<String, Object>> list = fileUploadService.getReportBoard3();
		Row row;
		Cell cell;
		Map<String, Object> map;
		Sheet sheet = wb.getSheetAt(0); // 0번 sheet는 first sheet를 의미한다.
		String[] headers = { "count", "date" };

		// 첫 번째 행을 생성, row는 Excel의 행을 의미한다.
		row = sheet.createRow(0);
		// header를 Excel에 먼저 추가.
		cell = row.createCell(0);
		cell.setCellValue(headers[0]);

		cell = row.createCell(1);
		cell.setCellValue(headers[1]);

		for (int i = 0; i < list.size(); i++) {
			// row는 Excel의 행을 의미한다.
			row = sheet.createRow(i + 1); // sheet의 0번째 행을 만든다.
			map = list.get(i);
			Iterator<String> iter = map.keySet().iterator(); // map 자료형에서 key 값만 가지고 있는 반복자.

			int j = 0; // 열 번호 변수.
			// Key가 있다면,
			while (iter.hasNext()) {
				// Key 값이 있는 만큼 Excel의 열이 추가된다. while문으로 반복동작.
				cell = row.createCell(j++); // cell은 열을 의미한다. Ex) 1행 일때 n열...

				Object field = map.get(iter.next());
				System.out.println(field.getClass());
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof BigDecimal) {
					cell.setCellValue(((BigDecimal) field).doubleValue());
				} else if (field instanceof Date) {
					cell.setCellValue((Date) field);
				} else {
					cell.setCellValue(field.toString());
				}
			}
		}
		// 엑셀 파일 저장
		String filename = "d:/uploadTest/excel_" + System.currentTimeMillis() + ".xls";
		FileOutputStream fos = new FileOutputStream(filename);
		wb.write(fos);
		fos.close();
		out.print("엑셀 저장 완료");
		wb.close();
	}

	@RequestMapping("/excelDownload.do")
	public void excelDownload(FileUploadDTO vo, HttpServletResponse response) throws IOException {
		// 다운로드
		String downFileName = "excel.xls";
		File uFile = new File(vo.getBoardFile());
		int fSize = (int) uFile.length(); // 파일크기

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(uFile));
		String mimetype = "text/html";
		response.setBufferSize(fSize);
		response.setContentType(mimetype);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + downFileName + "\"");
		response.setContentLength(fSize);
		FileCopyUtils.copy(in, response.getOutputStream());
		in.close();

		uFile.delete(); // 파일삭제

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	// 2. ViewResolver를 이용하는 엑셀 방법.
	// xlsx 엑셀출력
	@RequestMapping("/xlsxExcelView.do")
	public ModelAndView excelView(FileUploadDTO vo, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = fileUploadService.getReportBoard3();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String[] header = { "count", "boardDate" };
		map.put("headers", header);
		map.put("filename", "excel_dept");
		map.put("datas", list);
		return new ModelAndView("excelxlsxView", map);
	}
}