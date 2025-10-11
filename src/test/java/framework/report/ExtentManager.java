package framework.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance(String reportPath) {
        if (extent == null) {
            File reportDir = new File(reportPath);
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            String reportFile = reportPath + "/index.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
            spark.config().setReportName("Test execution report");
            spark.config().setDocumentTitle("Execution details");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Framework", "Playwright + TestNG");
            extent.setSystemInfo("Report Path", reportPath);
        }
        return extent;
    }
}
