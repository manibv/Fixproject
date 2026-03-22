package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/features",
    glue = {"stepDefinitions"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/jsonReports/cucumber-report.json"
    },
    monochrome = true
)
public class TestRunner {
}