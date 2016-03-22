package com.test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.config.Config;
import com.utils.WebDriverUtils;

public class WebWalker {
	public void listAllLists(WebDriver driver) {
		String url = "http://localhost:8280/ekp101/";
		this.login(driver, url);

		long startTime = System.currentTimeMillis();
		ArrayList<String> toVisitLinks = new ArrayList<String>();
		ArrayList<String> visitedLinks = new ArrayList<String>();
		toVisitLinks.add(driver.getCurrentUrl());
		int maxVisit = 10000;
		int counter = 0;
		this.listLinks(driver, toVisitLinks, visitedLinks, counter, maxVisit);
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime) / (1000 * 60);
		System.out.println("\nDuration=" + duration + " mins");
	}

	private void login(WebDriver driver, String url) {
		WebDriverUtils.openURL(driver, url);
		String UID = "ndadmin";
		String PWD = "123456";

		/*
		 * String UID = "uma_qa20"; String PWD = "11111111";
		 */

		By by = By.id("UID");
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(UID);
		by = By.id("PWD");
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(PWD);
		by = By.name("login");
		driver.findElement(by).click();
	}

	public void listLinks(WebDriver driver, ArrayList<String> toVisitLinks,
			ArrayList<String> visitedLinks, int counter, int maxVisit) {
		try {
			if (toVisitLinks.size() > 0) {
				// 1. visit(then delete) the first link if not visited before
				String toVisitLink = toVisitLinks.get(0);
				if (!visitedLinks.contains(toVisitLink)
						&& toVisitLink.contains("ekp101")) {
					driver.get(toVisitLink);
					Thread.sleep(1000);
					toVisitLinks.remove(0);
					visitedLinks.add(toVisitLink);

					// Check EKP error or UNSAFE-data here
					String text = "Please contact the system administrator";
					if (WebDriverUtils.textPresentInPage(driver, text)) {
						System.out
								.println("UNSAFE data was found in test case");
						String userDirectory = Config.getInstance()
								.getProperty("screenShotDir");
						String destFile = userDirectory + "/" + counter
								+ "_EkpError.png";
						WebDriverUtils.takeScreenShot(driver, destFile);
					}

					text = "UNSAFE";
					if (WebDriverUtils.textPresentInPage(driver, text)) {
						System.out
								.println("UNSAFE data was found in test case");
						String userDirectory = Config.getInstance()
								.getProperty("screenShotDir");
						String destFile = userDirectory + "/" + counter
								+ "_UnsafeData.png";
						WebDriverUtils.takeScreenShot(driver, destFile);
					}
				}

				// re-login if necessary
				if (toVisitLink.contains("LOGOFF")
						|| driver
								.getCurrentUrl()
								.equals("http://localhost:8280/ekp101/servlet/ekp/login?getnews=Y")
						|| toVisitLink.contains("login")) {
					this.login(driver, "http://localhost:8280/ekp101/");
				}

				counter++;
				System.out.printf("Fetching %s   ...", driver.getCurrentUrl()
						+ "\ncounter:" + counter);

				By by = By.xpath("//a");
				List<WebElement> links = driver.findElements(by);

				// 2. keep all out-going links found in this link
				for (WebElement link : links) {
					String text = link.getText();
					if (counter < maxVisit) {
						if (!text.equals("")) {
							String url = link.getAttribute("href");
							// System.out.printf("\n*a:<%s> (%s)",url ,
							// link.getText());
							// filter 1: no invalid url
							if (url != null) {
								// filter 2: not-visited url
								if (!toVisitLinks.contains(url)
										&& !visitedLinks.contains(url)) {
									toVisitLinks.add(url);
								}
							}
						}
					} else {
						return;
					}
				}
				System.out.printf(
						"\nFound Links:(%d);visited:(%d);toVisit:(%d)\n",
						links.size(), visitedLinks.size(), toVisitLinks.size());
				// 3. visit more links
				this.listLinks(driver, toVisitLinks, visitedLinks, counter,
						maxVisit);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		WebWalker ins = new WebWalker();

		WebDriver driver = new FirefoxDriver();
		ins.listAllLists(driver);
	}
}
