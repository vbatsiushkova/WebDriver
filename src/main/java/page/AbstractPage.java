package page;

import groovy.util.logging.Slf4j;
import helpers.BrowserType;
import helpers.WebDriverUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helpers.ConfigurationConstants.BROWSER_FIREFOX;
import static helpers.ConfigurationConstants.SELENIUM_BROWSER;

@Slf4j
public abstract class AbstractPage {
	private static final Logger log = Logger.getLogger(Log.class.getName());

	protected String url;
	protected String urlFormat;
	private static WebDriver driver = WebDriverUtils.getInstance();


	public AbstractPage() {
		initializePage();
		PageFactory.initElements(driver, this);
	}

	protected abstract void initializePage();

	protected String getUrl() {
		return url;
	}

	protected String getUrlFormat() {
		return urlFormat;
	}

	public void goToUrl(String url) {
		try {
			driver.get(url);
		} catch (TimeoutException ignore) {
			log.info("Page " + url + " didn't load within 1 minute!");
			driver.navigate().refresh();
			log.info("Page is opened successfully!");
		} finally {
//				driver.manage().timeouts().pageLoadTimeout(WebDriver.Timeouts.FIVE_MINUTES.getValue(), TimeUnit.MILLISECONDS);
		}
	}

	public void check() {
		checkCurrentByFormat();
		if (!driver.getCurrentUrl().contains(url)) {
			throw new IllegalStateException("Current url doesn't match to expected! Current = " + driver.getCurrentUrl()
					+ " Expected url = " + url);
		}
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void clearCookies() {
		driver.manage().deleteAllCookies();
	}


	public void switchToTab(int index) {
		String window = new ArrayList<>(driver.getWindowHandles()).get(index - 1);
		driver.switchTo().window(window);
	}

	private boolean isCurrentByFormat() {
		Pattern pattern = Pattern.compile(getUrlFormat());
		Matcher matcher = pattern.matcher(getCurrentUrl());
		return matcher.find();
	}

	protected void checkCurrentByFormat() {
		if (StringUtils.isNotBlank(urlFormat) && !isCurrentByFormat()) {
			throw new IllegalStateException(
					String.format("Current page is wrong by url format.\nExpected url format:%s\nCurrent url:%s",
							getUrlFormat(), getCurrentUrl()));
		}

	}
}
