package com.view;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.config.Config;
import com.junit.JUnitAssert;
import com.utils.AES_Cipher;
import com.utils.WebDriverUtils;

public class EmailUI {

	private EmailUI() {
		throw new AssertionError();
	}

	public static void Send(final String subject, final String content) {
		// Recipient's email ID needs to be mentioned.
		final String[] recipients = Config.getInstance()
				.getProperty("mail.recipient").split(",");

		// Sender's email ID needs to be mentioned
		final String from = Config.getInstance().getProperty("mail.sender");

		// Assuming you are sending email from localhost
		final String host = Config.getInstance().getProperty("mail.smtp.host");
		final String username = Config.getInstance().getProperty(
				"smtp.AuthUserName");
		final String passw = AES_Cipher.decrypt(Config.getInstance()
				.getProperty("smtp.AuthPassword"));
		final Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		final Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, passw);
					}
				});
		try {
			// Create a default MimeMessage object.
			final MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			for (final String recipient : recipients) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(recipient));
			}

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(content);

			// Send message
			Transport.send(message);
			System.out.println("Sent email successfully....");
		} catch (final MessagingException mex) {
			mex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param driver
	 * @param title
	 *            : Email Title e.g. Enrollment Confirmation
	 * @param ExpectedContent
	 *            : Expected email content
	 */

	public static void CheckInternalEmail(final WebDriver driver, final String title,
			final String ExpectedContent) {
		JUnitAssert.assertTrue(checkIfEmailReceived(driver, title),
				"Fail to receive email:" + title);
		By by = By
				.xpath("//a[contains(text(),'"
						+ title
						+ "') and descendant::img[contains(@src,'envelop_unread.png')]]");
		WebDriverUtils.clickLink(driver, by);
		by = By.xpath("//tr[@class='internal-mail-content']");
		final String ActualContent = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertTrue(ActualContent.contains(ExpectedContent),
				"Email Content is wrong. Expected Result:" + ExpectedContent
						+ ";Actual Result:" + ActualContent);
	}

	/**
	 * check if email is received
	 * 
	 * @param driver
	 * @param EmailTitle
	 * @return
	 */
	public static Boolean checkIfEmailReceived(final WebDriver driver,
			final String EmailTitle) {

		Navigator.navigate(driver, Navigator.xmlWebElmtMgr
				.getNavigationPathList("LearningCenter", "MyEmails"));

		final By by = By
				.xpath("//a[contains(text(),'"
						+ EmailTitle
						+ "') and descendant::img[contains(@src,'envelop_unread.png')]]");
		if (WebDriverUtils.getHowManyByPresntInPage(driver, by, false) >= 1) {
			return true;
		} else {
			return false;
		}

	}

}