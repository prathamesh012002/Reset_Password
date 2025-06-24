package run;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features= "Feature File Path",
				 glue ="steodefination",
				 plugin = {"pretty", "html:target/cucumber-reports.html"
				 		}
			)
public class Runner {

}
