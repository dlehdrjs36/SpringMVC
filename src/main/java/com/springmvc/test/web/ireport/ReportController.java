package com.springmvc.test.web.ireport;
import java.io.OutputStream;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springmvc.test.fileupload.FileUploadService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Controller
public class ReportController {
	@Autowired
	FileUploadService fileUploadService;
	
	@RequestMapping("report.do")
	public void report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String reportPath = request.getSession().getServletContext().getRealPath("/resources/report3.jrxml");
			System.out.println(reportPath);
			String reportLogo =  request.getSession().getServletContext().getRealPath("/resources/invoice_logo.png");
			
			// 로컬 경로에서 report 파일 읽어들임
			/*String reportLogo = "C:\\Users\\User\\git\\Spring\\SpringMVC\\src\\main\\webapp\\resources\\invoice_logo.png";
			JasperReport report = JasperCompileManager
					.compileReport("C:\\Users\\User\\git\\Spring\\SpringMVC\\src\\main\\webapp\\resources\\report3.jrxml");
			*/
			
			
			/* 서버의 경로에서 파일을 찾음.*/
			JasperReport report = JasperCompileManager
					.compileReport(request.getSession().getServletContext().getRealPath("/resources/report3.jrxml"));
			
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(fileUploadService.getReportBoard2());
			map.put("reportLogo", reportLogo);
			
			JasperPrint print = JasperFillManager.fillReport(report, map, JRdataSource);
			JRExporter exporter = new JRPdfExporter();
			OutputStream out;
			response.reset();
			out = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report3.pdf");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("reportChart.do")
	public void reportChart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			//String reportLogo = "C:\\Users\\User\\git\\Spring\\SpringMVC\\src\\main\\webapp\\resources\\flower1.png";
			String reportLogo =  request.getSession().getServletContext().getRealPath("/resources/flower1.png");
			// 로컬 경로에서 report 파일 읽어들임
			/*JasperReport report = JasperCompileManager
					.compileReport("C:\\Users\\User\\git\\Spring\\SpringMVC\\src\\main\\webapp\\resources\\chart_report.jrxml");
			 */
			
			/* 서버의 경로에서 파일을 찾음.*/
			JasperReport report = JasperCompileManager
					.compileReport(request.getSession().getServletContext().getRealPath("/resources/chart_report.jrxml"));
			
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(fileUploadService.getReportBoard3());
			map.put("reportLogo", reportLogo);
			
			JasperPrint print = JasperFillManager.fillReport(report, map, JRdataSource);
			JRExporter exporter = new JRPdfExporter();
			
			OutputStream out;
			response.reset();
			out = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "chart_report.pdf");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}