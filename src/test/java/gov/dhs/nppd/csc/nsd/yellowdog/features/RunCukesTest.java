package gov.dhs.nppd.csc.nsd.yellowdog.features;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber-html-report"},
		features = "src/test/resources/features/yellowdog",
//		name = "incorrect"                     // Scenario names that contain "incorrect"
//		tags = {"@ShouldPass", "@ShouldFail"}  // AND: run scenarios tagged with BOTH @ShouldPass AND @ShouldFail
//		tags = {"@ShouldPass, @ShouldFail"}    //  OR: run scenarios tagged with EITHER @ShouldPass OR @ShouldFail
		tags = {"@Login"}
)
public class RunCukesTest {
}