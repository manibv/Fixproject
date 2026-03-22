package resources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class ReportGenerator {

    public static void generateReport() {
        File reportOutputDirectory = new File("target/cucumber-html-reports");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/jsonReports/cucumber-report.json");

        Configuration configuration = new Configuration(reportOutputDirectory, "Fixproject");
        configuration.setBuildNumber("1");
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Project", "Fixproject");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}