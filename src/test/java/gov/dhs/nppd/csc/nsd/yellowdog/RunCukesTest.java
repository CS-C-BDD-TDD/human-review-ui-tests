package gov.dhs.nppd.csc.nsd.yellowdog;

import org.aspectj.lang.annotation.Aspect;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@ComponentScan(basePackages = { "gov.dhs" })
@CucumberOptions(features = "src/test/resources/features")
@Aspect
public class RunCukesTest {
}