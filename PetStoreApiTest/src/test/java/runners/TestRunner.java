package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/StorePetTest.feature",
        glue = {"steps"},
        monochrome = true,
        stepNotifications = true
        )

public class TestRunner {


}
