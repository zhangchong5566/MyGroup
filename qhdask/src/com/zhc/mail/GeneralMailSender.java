package com.zhc.mail;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.zhc.mail.entity.MailFailLog;
import com.zhc.mail.service.MailFailLogService;

public class GeneralMailSender implements MailSender {

	protected final Log logger = LogFactory.getLog(getClass());
	private JavaMailSender javaMailSender = null;
	private MailFailLogService mailFailLogService;
	private String from;
	private String defaultEncoding;
	private String fromName;

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public MailFailLogService getMailFailLogService() {
		return mailFailLogService;
	}

	public void setMailFailLogService(MailFailLogService mailFailLogService) {
		this.mailFailLogService = mailFailLogService;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

	@Override
	public void sendMail(String[] tos, String subject, String message) {

		StringBuffer mailReceives = new StringBuffer();
		for (int i = 0; i < tos.length; i++) {
			mailReceives.append(tos[i]);
			mailReceives.append(",");
		}
		this.logger.info("Mail Receiver[" + mailReceives.toString()
				+ "] Total:" + tos.length+",Subject:"+subject);
		
		
		try {
			
			SimpleMailMessage messageObj = new SimpleMailMessage();
			messageObj.setFrom(fromName+"<"+this.from+">");
			messageObj.setTo(tos);
			messageObj.setSubject(subject);
			messageObj.setText(message);
			this.javaMailSender.send(messageObj);
			
		} catch (Exception e) {
			this.logger.error("Mail Receiver[" + mailReceives.toString()
					+ "]",e);
			this.saveFailLog(e, mailReceives.toString(), subject, message);
		}
	}

	@Override
	public void sendMimeMail(String[] tos, String subject, String message) {
		sendMimeMail(tos, subject, message, defaultEncoding);
	}

	@Override
	public void sendMimeMail(String[] tos, String subject, String message,
			String encoding) {

		StringBuffer mailReceives = new StringBuffer();
		for (int i = 0; i < tos.length; i++) {
			mailReceives.append(tos[i]);
			mailReceives.append(",");
		}
		this.logger.info("Mail Receiver[" + mailReceives.toString()
				+ "] Total:" + tos.length+",Subject:"+subject);
		MimeMessage messageObj = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(messageObj, encoding);
		
		try {
			helper.setFrom(this.from,this.fromName);
			helper.setTo(tos);
			helper.setText(message, true);
			helper.setSubject(subject);
			this.javaMailSender.send(messageObj);
		} catch (Exception e) {
			this.logger.error("Mail Receiver[" + mailReceives.toString()
					+ "]",e);
			this.saveFailLog(e, mailReceives.toString(), subject, message);e.printStackTrace();
		}
	}

	@Override
	public void sendMimeMail(String[] tos, String[] ccs, String[] bccs,
			String subject, String message, String encoding,
			List<MailAttachment> attachmentList) {
		StringBuffer mailReceives = new StringBuffer();
		for (int i = 0; i < tos.length; i++) {
			mailReceives.append(tos[i]);
			mailReceives.append(",");
		}
		this.logger.info("Mail Receiver[" + mailReceives.toString()
				+ "] Total:" + tos.length+",Subject:"+subject);
		try {
			MimeMessage messageObj = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = null;
			if ((attachmentList != null) && (!attachmentList.isEmpty())) {
				helper = new MimeMessageHelper(messageObj, true, encoding);
			} else {
				helper = new MimeMessageHelper(messageObj, encoding);
			}
			helper.setFrom(this.from,this.fromName);
			helper.setTo(tos);
			if (ccs != null) {
				helper.setCc(ccs);
			}
			if (bccs != null) {
				helper.setBcc(bccs);
			}
			helper.setText(message, true);
			helper.setSubject(subject);
			if (attachmentList != null) {
				for (MailAttachment ma : attachmentList) {
					helper.addAttachment(ma.getFileName(), ma.getFile()
							.getAbsoluteFile());
				}
			}
			this.javaMailSender.send(messageObj);

		} catch (Exception e) {
			this.logger.error("Mail Receiver[" + mailReceives.toString()
					+ "]",e);
			this.saveFailLog(e, mailReceives.toString(), subject, message);
		}

	}
	
    /** 
     * 发送失败时保存异常信息
     *  
     * @param t 
     * @return 
     */  
    private void saveFailLog(Throwable t,String mailReceives, String subject, String message)  
    {  
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
      
        try  
        {  
            t.printStackTrace(pw);
            
            MailFailLog log = new MailFailLog();
			log.setReceiverMails(mailReceives.toString());
			log.setTitle(subject);
			log.setContent(message);
			log.setException(sw.toString());
			log.setSendDate(new Date());
			mailFailLogService.create(log);
        }  
        finally  
        {  
            pw.close();  
        }  
    }  

}
