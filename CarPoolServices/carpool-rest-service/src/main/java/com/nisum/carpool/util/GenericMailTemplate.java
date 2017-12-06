package com.nisum.carpool.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.nisum.carpool.data.util.Pool_Status;
import com.nisum.carpool.data.util.Ride_Status;
import com.nisum.carpool.service.dto.EmailAccount;
import com.nisum.carpool.service.dto.GenericEmailDto;
import com.nisum.carpool.service.dto.MessageContextDto;

/**
 * 
 * @author Rajendra Prasad Dava CPL-036 : Building reusable mail component for
 *         carpool
 * 
 */
@Component
public class GenericMailTemplate {
	Logger logger = LoggerFactory.getLogger(GenericMailTemplate.class);
	@Autowired
	private EmailAccount emailAccount;
	@Autowired
	private MessageContextDto messageContextDto;

	public void sendGenericMail(String ccMail, Map<String, GenericEmailDto> userMap) {
		logger.info("GenericMailTemplate::sendGenericMail::Entered into sendGenericMail functionality");
		Properties props = new Properties();
		props.put(Constants.GMAIL_SMTP, Constants.TRUE_FLAG);
		props.put(Constants.GMAIL_START_TLS, Constants.TRUE_FLAG);
		props.put(Constants.GMAIL_HOST, Constants.GMAIL_HOST_NAME);
		props.put(Constants.GMAIL_PORT, Constants.GMAIL_PORT_NUM);

		String fromMail = emailAccount.getAdminemail();
		String password = emailAccount.getAdminpassword();

		Authenticator authenticator = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromMail, password);
			}
		};

		Session session = Session.getInstance(props, authenticator);

		try {
			logger.info("GenericMailTemplate :: Building the mail structure");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));
			buildMailReceipientAndBody(message, ccMail, userMap);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void buildMailReceipientAndBody(Message message, String ccMail, Map<String, GenericEmailDto> userMap)
			throws AddressException, MessagingException {
		logger.info("GenricMailTemplate :: buildMailReceipientAndBody :: Entered into buildMailReceipientAndBody");
		Set<String> toMail = userMap.keySet();
		String bodyMsg;
		for (String key : toMail) {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(key));

			if (ccMail != null && !ccMail.equals("")) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccMail));
			}
			GenericEmailDto dto = userMap.get(key);

			if (dto.getStatus() == Ride_Status.APPROVED.getValue()) {
				logger.info("GenericMailTemplate :: Method to build a message when a pool is approved by driver "
						+ messageContextDto.getApproveSub());
				message.setSubject(messageContextDto.getApproveSub());
				bodyMsg = messageBodyTemplate(dto, messageContextDto.getMsgPrefix(),
						messageContextDto.getMsgApproveSuffix(), messageContextDto.getMsgWishApprove());
				message.setText(bodyMsg);
				message.setContent(bodyMsg, "text/html");
			} else if (dto.getStatus() == Ride_Status.REJECTED.getValue()) {
				logger.info("GenericMailTemplate :: Method to build a message when a poolis rejected by driver "
						+ messageContextDto.getRejectSub());

				message.setSubject(messageContextDto.getRejectSub());
				bodyMsg = messageBodyTemplate(dto, messageContextDto.getMsgPrefix(),
						messageContextDto.getMsgRejectSuffix(), messageContextDto.getMsgWish());
				message.setText(bodyMsg);
				message.setContent(bodyMsg, "text/html");

			} else if (dto.getStatus() == Ride_Status.CANCELLED.getValue() && dto.getIsRider() == 1) {
				logger.info("GenericMailTemplate :: Method to build a message when rider cancels the ride"
						+ messageContextDto.getRiderCancelSub());
				message.setSubject(messageContextDto.getRiderCancelSub());
				bodyMsg = riderCancelMessageBodyTemplate(dto);
				message.setText(bodyMsg);
				message.setContent(bodyMsg, "text/html");
			} else if (dto.getStatus() == Pool_Status.CANCELLED.getValue()) {
				logger.info("GenericMailTemplate :: Method to build a message when driver cancels the ride"
						+ messageContextDto.getCancelSub());
				message.setSubject(messageContextDto.getCancelSub());
				bodyMsg = messageBodyTemplate(dto, messageContextDto.getMsgPrefix(),
						messageContextDto.getMsgCancelSuffix(), messageContextDto.getMsgWish());
				message.setText(bodyMsg);
				message.setContent(bodyMsg, "text/html");

			} else if (dto.isNotifyMe() == true) {
				logger.info(
						"GenericMailTemplate :: Method to build a message when a pool is reopened: Pool has been reopened");
				message.setSubject("Pool has been reopened");
				bodyMsg = notifyMeMessageBodyTemplate(dto);
				message.setText(bodyMsg);
				message.setContent(bodyMsg, "text/html");
			}
		}
		Transport.send(message);

	}

	/**
	 * method that builds message template for approve, reject, driver cancel by
	 * taking following parameters
	 * 
	 * @param genericEmailDto
	 * @param msgPrefix
	 * @param msgSuffix
	 * @param wishMsg
	 * @return message that to be set for body of email
	 */
	public String messageBodyTemplate(GenericEmailDto genericEmailDto, String msgPrefix, String msgSuffix,
			String wishMsg) {

		StringBuilder sb = new StringBuilder();

		String headerContent = "";
		String footerContent = "";

		try {
			headerContent = Files.toString(new File(messageContextDto.getHeaderPath()), Charsets.UTF_8);
			footerContent = Files.toString(new File(messageContextDto.getFooterpath()), Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sb.append(headerContent);
		sb.append("<br/>");
		sb.append("<html><head></head><title></title>");
		sb.append("<body style='font-size:12px;font-family:Trebuchet MS;padding:10px'>");
		sb.append("<i>Hi" + "  " + genericEmailDto.getUserName() + "," + "</i>");
		sb.append("<br><br>");

		sb.append("<b style='font-size:14px;font-family:Trebuchet MS;padding:10px'>" + msgPrefix + " "
				+ genericEmailDto.getDate() + " from " + genericEmailDto.getLocation() + " to Nisum at "
				+ genericEmailDto.getStartTime() + " and from Nisum to " + genericEmailDto.getLocation() + " at  "
				+ genericEmailDto.getReturnTime() + " " + msgSuffix + "</b>");
		sb.append("<br><br><br>");
		sb.append("<i style='font-size:18px;font-family:Trebuchet MS;padding:10px;color:orange'>"
				+ wishMsg.toUpperCase() + "</i>");
		if (genericEmailDto.getRemarks() != null) {
			sb.append("<br><br>");
			sb.append("<i style='font-size:16px;font-family:Trebuchet MS;padding:10px;colorRed'>Remarks: "
					+ genericEmailDto.getRemarks() + "</b>");
		}
		sb.append("<br><br/>");
		sb.append("<br><br>");
		sb.append("<i>Thanks & Regards,</i>");
		sb.append("<br>");
		sb.append("<font color=red><i>Carpool Application.</i></font><hr/></html>");
		sb.append(footerContent);
		sb.append(
				"<i style='font-size:10px;font-family:Trebuchet MS;padding:10px'>Note: This is an auto generated e-mail. Please check this in <a href='#'>Carpool</a>.</i>");
		return sb.toString();
	}

	/**
	 * method that builds mail template when rider cancels the ride
	 * 
	 * @param genericEmailDto
	 * @return message that to be set for body of email
	 */
	public String riderCancelMessageBodyTemplate(GenericEmailDto genericEmailDto) {

		StringBuilder sb = new StringBuilder();

		String headerContent = "";
		String footerContent = "";

		try {
			headerContent = Files.toString(new File(messageContextDto.getHeaderPath()), Charsets.UTF_8);
			footerContent = Files.toString(new File(messageContextDto.getFooterpath()), Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(
				"GenericMailTemplate :: riderCancelMessageBodyTemplate::Building body of mail when rider cancels the ride");
		sb.append(headerContent);
		sb.append("<br/>");
		sb.append("<html><head></head><title></title>");
		sb.append("<body style='font-size:12px;font-family:Trebuchet MS;padding:10px'>");
		sb.append("<i>Hi" + "  " + genericEmailDto.getUserName() + "," + "</i>");
		sb.append("<br><br>");

		sb.append("<b style='font-size:14px;font-family:Trebuchet MS;padding:10px'>"
				+ genericEmailDto.getRiderUserName() + " has cancelled his ride on " + genericEmailDto.getDate()
				+ " from " + genericEmailDto.getLocation() + " to Nisum at " + genericEmailDto.getStartTime()
				+ " and from Nisum to " + genericEmailDto.getLocation() + " at  " + genericEmailDto.getReturnTime()
				+ ".</b>");
		sb.append("<br><br><br>");
		sb.append("<i>Thanks & Regards,</i>");
		sb.append("<br>");
		sb.append("<font color=red><i>Carpool Application.</i></font><hr/></html>");
		sb.append(footerContent);
		sb.append(
				"<i style='font-size:10px;font-family:Trebuchet MS;padding:10px'>Note: This is an auto generated e-mail. Please check this in <a href='#'>Carpool</a>.</i>");
		return sb.toString();
	}

	public String notifyMeMessageBodyTemplate(GenericEmailDto genericEmailDto) {

		StringBuilder sb = new StringBuilder();

		String headerContent = "";
		String footerContent = "";

		try {
			headerContent = Files.toString(new File(messageContextDto.getHeaderPath()), Charsets.UTF_8);
			footerContent = Files.toString(new File(messageContextDto.getFooterpath()), Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("GenericMailTemplate :: notifyMeMessageBodyTemplate::Building body of mail for pool reopened");
		sb.append(headerContent);
		sb.append("<br/>");
		sb.append("<html><head></head><title></title>");
		sb.append("<body style='font-size:12px;font-family:Trebuchet MS;padding:10px'>");
		sb.append("<i>Hi" + "  " + genericEmailDto.getUserName() + "," + "</i>");
		sb.append("<br><br>");
		sb.append("<b style='font-size:14px;font-family:Trebuchet MS;padding:10px'> A ride  on "
				+ genericEmailDto.getDate() + " from " + genericEmailDto.getLocation() + " to Nisum at "
				+ genericEmailDto.getStartTime() + " and from Nisum to " + genericEmailDto.getLocation() + " at  "
				+ genericEmailDto.getReturnTime() + " has been reopened</b>");
		sb.append("<br><br><br>");
		sb.append("<i>Thanks & Regards,</i>");
		sb.append("<br>");
		sb.append("<font color=red><i>Carpool Application.</i></font><hr/></html>");
		sb.append(footerContent);
		sb.append(
				"<i style='font-size:10px;font-family:Trebuchet MS;padding:10px'>Note: This is an auto generated e-mail. Please check this in <a href='#'>Carpool</a>.</i>");

		return sb.toString();
	}
}