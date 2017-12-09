package com.nisum.carpool.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
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
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.nisum.carpool.data.util.Pool_Status;
import com.nisum.carpool.data.util.Ride_Status;
import com.nisum.carpool.service.dto.EmailAccount;
import com.nisum.carpool.service.dto.GenericEmailDto;
import com.nisum.carpool.service.dto.MessageContextDto;
import com.nisum.carpool.service.exception.MailServiceException;

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

	String headerContent = "";
	String footerContent = "";

	/**
	 * @author Rajendra Prasad Dava This method loads the header and footer file at
	 *         while loading the class
	 * 
	 * @throws MailServiceException
	 */
	@PostConstruct
	public void loadFilePathAtStartUp() throws MailServiceException {
		logger.info("GenericMailTenmplate :: loadFilePathAtStartUp() :: loading header and footer files");
		try {

			headerContent = Files.toString(
					new File(System.getProperty("user.dir") + "/src/main/resources/static/header.html"),
					Charsets.UTF_8);
			footerContent = Files.toString(
					new File(System.getProperty("user.dir") + "/src/main/resources/static/footer.html"),
					Charsets.UTF_8);
		} catch (IOException e) {
			throw new MailServiceException("Unable to load the header and footer files");
		}
	}

	/**
	 * @author Rajendra Prasad Dava
	 * 
	 *         This method sends an mail to particular receipient given in userMap
	 * 
	 * @param ccMail
	 * @param userMap
	 * @return status whether the mail is sent successfully or not
	 * @throws MailServiceException
	 */
	public boolean sendGenericMail(String ccMail, Map<String, GenericEmailDto> userMap) throws MailServiceException {
		boolean flag = false;
		logger.info("GenericMailTemplate::sendGenericMail()::Entered into sendGenericMail functionality");
		if (userMap == null) {
			throw new MailServiceException("Please Enter Valid Details");
		} else {
			logger.info(
					"GenericMailTemplate:: sendGenericMailTemplate :: calling performValidation() to validate the input data");
			boolean status = performValidations(userMap);
			Session session = null;
			if (status) {
				String fromMail = emailAccount.getAdminemail();
				String password = emailAccount.getAdminpassword();
				logger.info(
						"GenericMailTemplate :: sendGenericEmail :: calling setPropertiesAndAuthenticateAdminMail() method to authenticate the mail");
				session = setPropertiesAndAuthenticateAdminMail(fromMail, password);

				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(fromMail));
					logger.info(
							"GenericMailTemplate :: sendGenericMail:: calling buildAndSendMail() to build the mail");
					flag = buildAndSendMail(message, ccMail, userMap);
				} catch (Exception e) {
					logger.info("GenericMailTemplate :: sendGenericMail:: failed to send mail");
					throw new MailServiceException(Constants.MAILFAILEDEXCEPTION);
				} // end of catch block
			} // end of if
			logger.info("GenericMailTemplate :: sendGenericMail :: mail sent successfully");
			return flag;
		} // end of else
	}// end of method

	/**
	 * @author Rajendra Prasad Dava
	 * 
	 *         This method validates whether given input data is empty or not
	 * 
	 * @param userMap
	 * @return status as true if validations are done successfully
	 * @throws MailServiceException
	 */
	public boolean performValidations(Map<String, GenericEmailDto> userMap) throws MailServiceException {
		logger.info("GenericMialTemplate :: performValidations:: entered into performValidations() method");
		Set<String> toMail = null;
		if ((toMail = userMap.keySet()) != null) {
			for (String key : toMail) {
				if ("".equals(key)) {
					throw new MailServiceException("Please enter the valid receipient mail id");
				} else {
					GenericEmailDto genereicEmailDto = userMap.get(key);
					if (genereicEmailDto.getDate() == null || genereicEmailDto.getDate().equals("")) {
						throw new MailServiceException(Constants.INVALIDDATE);
					}
					if (genereicEmailDto.getLocation() == null || genereicEmailDto.getLocation().equals("")) {
						throw new MailServiceException(
								Constants.INVALIDLOCATIONVALUE);
					}
					if (genereicEmailDto.getUserName() == null || genereicEmailDto.getUserName().equals("")) {
						throw new MailServiceException(
								Constants.INVALIDUSERNAME);
					}
					if (genereicEmailDto.getStartTime() == null || genereicEmailDto.getStartTime().equals("")) {
						throw new MailServiceException(
								Constants.INVALIDSTARTTIME);
					}
					if (genereicEmailDto.getReturnTime() == null || genereicEmailDto.getReturnTime().equals("")) {
						throw new MailServiceException(
								Constants.INVALIDRETURNTIME);
					}
				} // end of else
			} // end of for loop
		}
		logger.info("GenericMailTemplate :: performValidations:: exiting from performValidations()");
		return true;
	}

	/**
	 * @author Rajendra Prasad Dava
	 * 
	 *         This method set the properties for smtp server and authenticates the
	 *         given mailid and password
	 * @return session for given mail and password
	 */
	public Session setPropertiesAndAuthenticateAdminMail(String fromMail, String password) {
		logger.info(
				"GenericMailTemplate :: propertiesSetForSmtp :: entered into setPropertiesAndAuthenticateAdminMail() method");
		Properties props = new Properties();
		props.put(Constants.GMAIL_SMTP, Constants.TRUE_FLAG);
		props.put(Constants.GMAIL_START_TLS, Constants.TRUE_FLAG);
		props.put(Constants.GMAIL_HOST, Constants.GMAIL_HOST_NAME);
		props.put(Constants.GMAIL_PORT, Constants.GMAIL_PORT_NUM);
		logger.info("GenericMailTemplate :: propertiesSetForSmtp :: properties are set for smtp");

		logger.info("GenericMailTemplate :: sendGenericEmail :: Authenticating admin emailid and password ");
		Authenticator authenticator = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromMail, password);
			}
		};
		Session session = Session.getInstance(props, authenticator);
		logger.info(
				"GenericMailTemplate :: sendGenericMail :: exiting from setPropertiesAndAuthenticateAdminMail() method");
		return session;
	}

	/**
	 * @author Rajendra Prasad Dava
	 * 
	 *         This method builds and sends an email to particular user given in
	 *         userMap
	 * 
	 * @param message
	 * @param ccMail
	 * @param userMap
	 * @throws AddressException
	 * @throws MessagingException
	 * @return status whether mail has build and sent
	 * @throws MailServiceException 
	 */
	public boolean buildAndSendMail(Message message, String ccMail, Map<String, GenericEmailDto> userMap)
			throws AddressException, MessagingException, MailServiceException {
		logger.info("GenricMailTemplate :: buildAndSendMail :: Entered into buildAndSendMail(ccMail, userMap)");
		Set<String> toMail = userMap.keySet();
		String bodyMsg = null;
		for (String key : toMail) {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(key));

			if (ccMail != null && !ccMail.equals("")) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccMail));
			}

			GenericEmailDto dto = userMap.get(key);

			if (dto.getStatus() == Ride_Status.APPROVED.getValue()) {
				logger.info(
						"GenericMailTemplate :: buildAndSendMail :: Calling buildMessageBodyTemplate(dto, msgPrefix, msgSuffix, wishMsg) to build a message when a pool is approved by driver "
								+ messageContextDto.getApproveSub());
				message.setSubject(messageContextDto.getApproveSub());
				bodyMsg = buildMessageBodyTemplate(dto, messageContextDto.getMsgPrefix(),
						messageContextDto.getMsgApproveSuffix(), messageContextDto.getMsgWishApprove());
			} else if (dto.getStatus() == Ride_Status.REJECTED.getValue()) {
				logger.info(
						"GenericMailTemplate :: buildAndSendMail ::Calling buildMessageBodyTemplate(dto, msgPrefix, msgSuffix, wishMsg) to build a message when a poolis rejected by driver "
								+ messageContextDto.getRejectSub());

				message.setSubject(messageContextDto.getRejectSub());
				bodyMsg = buildMessageBodyTemplate(dto, messageContextDto.getMsgPrefix(),
						messageContextDto.getMsgRejectSuffix(), messageContextDto.getMsgWish());
			} else if (dto.getStatus() == Ride_Status.CANCELLED.getValue() && dto.getIsRider() == 1) {
				logger.info(
						"GenericMailTemplate ::  buildAndSendMail :: Calling buildNotifyMeMessageBodyTemplate (dto) when rider cancels the ride"
								+ messageContextDto.getRiderCancelSub());
				message.setSubject(messageContextDto.getRiderCancelSub());
				bodyMsg = riderCancelMessageBodyTemplate(dto);
			} else if (dto.getStatus() == Pool_Status.CANCELLED.getValue()) {
				logger.info(
						"GenericMailTemplate ::  buildAndSendMail :: Calling buildMessageBodyTemplate(dto, msgPrefix, msgSuffix, wishMsg) to build a message when driver cancels the ride");
				message.setSubject(messageContextDto.getCancelSub());
				bodyMsg = buildMessageBodyTemplate(dto, messageContextDto.getMsgPrefix(),
						messageContextDto.getMsgCancelSuffix(), messageContextDto.getMsgWish());
			} else if (dto.isNotifyMe()) {
				logger.info(
						"GenericMailTemplate :: buildAndSendMail :: Calling buildNotifyMeMessageBodyTemplate (dto)");
				message.setSubject("Pool has been reopened");
				bodyMsg = buildNotifyMeMessageBodyTemplate(dto);
			}
		}
		message.setText(bodyMsg);
		message.setContent(bodyMsg, "text/html");
		Transport.send(message);
		logger.info(
				"GenericMailTemplate :: buildAndSendMail :: Exiting form buildAndSendMail() as the mail sent successfully");
		return true;
	}

	/**
	 * @author Rajendra Prasad Dava
	 * 
	 *         This method builds message template for approve, reject and driver
	 *         cancel a ride for given parameters
	 * 
	 * @param genericEmailDto
	 * @param msgPrefix
	 * @param msgSuffix
	 * @param wishMsg
	 * @return String which will be set as body of email for approve, reject and
	 *         driver cancel
	 */
	public String buildMessageBodyTemplate(GenericEmailDto genericEmailDto, String msgPrefix, String msgSuffix,
			String wishMsg) {
		logger.info(
				"GenericMailTemplate :: buildMessageBodyTemplate :: Entered into buildMessageBodyTemplate(genericEmailDto, msgPrefix, msgSuffix, wishMsg)");
		StringBuilder sb = new StringBuilder();
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
		logger.info(
				"GenericMailTemplate :: buildMessageBodyTemplate :: Exiting from buildMessageBodyTemplate(genericEmailDto, msgPrefix, msgSuffix, wishMsg)");
		return sb.toString();
	}

	/**
	 * @author Rajendra Prasad Dava
	 * 
	 *         This method builds mail template when rider cancels the ride
	 * 
	 * 
	 * @param genericEmailDto
	 * @return message that to be set for body of email when rider cancels the ride
	 * @throws MailServiceException 
	 */
	public String riderCancelMessageBodyTemplate(GenericEmailDto genericEmailDto) throws MailServiceException {
		logger.info(
				"GenericMailTemplate :: riderCancelMessageBodyTemplate::Entered into riderCancelMessageBodyTemplate(geenricEmailDto)");
		if(genericEmailDto.getIsRider()==0) {
			throw new MailServiceException(
					Constants.INVALIDISRIDERVALUE);
		}
		StringBuilder sb = new StringBuilder();
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
				+ "." + "</b>");
		if (genericEmailDto.getRemarks() != null) {
			sb.append("<br><br>");
			sb.append("<i style='font-size:16px;font-family:Trebuchet MS;padding:10px;colorRed'>Remarks: "
					+ genericEmailDto.getRemarks() + "</b>");
		}
		sb.append("<br><br>");
		sb.append("<i>Thanks & Regards,</i>");
		sb.append("<br>");
		sb.append("<font color=red><i>Carpool Application.</i></font><hr/></html>");
		sb.append(footerContent);
		sb.append(
				"<i style='font-size:10px;font-family:Trebuchet MS;padding:10px'>Note: This is an auto generated e-mail. Please check this in <a href='#'>Carpool</a>.</i>");
		logger.info(
				"GenericMailTemplate :: riderCancelMessageBodyTemplate::Exiting from riderCancelMessageBodyTemplate(geenricEmailDto)");
		return sb.toString();
	}

	/**
	 * @author Rajendra Prasad
	 * 
	 *         This method builds mail template when pool is reopened
	 * 
	 * 
	 * @param genericEmailDto
	 * @return message that to be set for body of email when pool is reopened
	 */
	public String buildNotifyMeMessageBodyTemplate(GenericEmailDto genericEmailDto) {
		logger.info(
				"GenericMailTemplate :: buildNotifyMeMessageBodyTemplate::Entered into buildNotifyMeMessageBodyTemplate(genericEmailDto) ");
		StringBuilder sb = new StringBuilder();
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
		logger.info(
				"GenericMailTemplate :: buildNotifyMeMessageBodyTemplate::Exiting from buildNotifyMeMessageBodyTemplate(genericemailDto)");
		return sb.toString();
	}
}