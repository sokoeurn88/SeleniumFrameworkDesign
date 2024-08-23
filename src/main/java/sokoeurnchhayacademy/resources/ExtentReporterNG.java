package sokoeurnchhayacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		// ExtentReports, ExtentSparkReporter
		String path = System.getProperty("user.dir") + "\\reporters\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports(); // ExtentReporters is the main class. ExtentSparkReporter is attached to it.
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sokoeurn Chhay");
		
		return extent;
	}
}
