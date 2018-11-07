package gov.dhs.nppd.csc.nsd.yellowdog;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features")
public class RunCukesTest {

  @BeforeClass
  public static void initWebDriver() {
    System.out.println("**### - ChromeDriver setup");
  }
}