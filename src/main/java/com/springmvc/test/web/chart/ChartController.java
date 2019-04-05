package com.springmvc.test.web.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ChartController {

	@RequestMapping(value = "/googlechart.do", method = RequestMethod.GET)
	public String googlechart() {
		return "googleChart/googleChart";
	}

	@RequestMapping(value = "/googlechart2.do", method = RequestMethod.GET)
	public String googlechart2() {
		return "googleChart/googleChart2";
	}

	// 구글 차트
	@RequestMapping(value = "/googlechart3.do")
	public String chart(ModelMap model) throws Exception {
		Slice s1 = Slice.newSlice(15, Color.newColor("CACACA"), "mac");
		Slice s2 = Slice.newSlice(50, Color.newColor("DF7417"), "window");
		Slice s3 = Slice.newSlice(25, Color.newColor("951800"), "linux");
		Slice s4 = Slice.newSlice(10, Color.newColor("01A1DB"), "others");
		PieChart pieChart = GCharts.newPieChart(s1, s2, s3, s4);
		pieChart.setTitle("google chart", Color.BLACK, 15);
		pieChart.setSize(720, 360);
		pieChart.setThreeD(true);
		model.addAttribute("pieUrl", pieChart.toURLString());
		return "/googleChart/googleChart3";
	}
	// 구글 차트 - Ajax 요청 View 화면
	@RequestMapping(value = "/googlechart4.do", method = RequestMethod.GET)
	public String googlechart4() {
		return "googleChart/googleChart4";
	}
	// 구글 차트 - Ajax 요청 처리
	@RequestMapping("/getChartData.do")
	@ResponseBody
	public List<Map<String, String>> getChartData() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "인사");
		map.put("cnt", "5");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("name", "총무");
		map.put("cnt", "10");
		list.add(map);
		
		map = new HashMap<String, String>();
		map.put("name", "기획");
		map.put("cnt", "20");
		list.add(map);
		
		return list;
	}

}
