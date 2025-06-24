package run;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features= "C:\\Users\\AC\\eclipse-workspace\\orange_hrm_02\\feature\\reset_password.feature",
				 glue ="steodefination",
				 plugin = {"pretty", "html:target/cucumber-reports.html"
				 		}
			)
public class Runner {

}
