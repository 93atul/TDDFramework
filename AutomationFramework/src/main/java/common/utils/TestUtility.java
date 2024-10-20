package common.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class TestUtility {
    public static final Logger s_logger = LoggerFactory.getLogger(TestUtility.class);
    public static Predicate<String> validateNotBlank = data-> !data.trim().isEmpty();
    public static BiPredicate<String,String> validateValueEquals = String::equals;
    public static Predicate<Double> validateNotBlankDouble = data-> !data.toString().isEmpty();
    public static Predicate<WebElement> validateElementPresence=element->{
        boolean result = true;
        try {
            new FluentWait<WebElement>(element).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(2))
                    .until(new Function<WebElement, Boolean>() {
                        @Override
                        public Boolean apply(WebElement webElement) {
                            return webElement.isDisplayed();
                        }
                    });
        }catch(Exception ex){
            s_logger.info("Unable to find Element Got Exception: "+ex.getMessage());
            result=false;
        }
        return result;
    };

}
