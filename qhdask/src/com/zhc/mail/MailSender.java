package com.zhc.mail;

import java.util.List;

import javax.mail.MessagingException;

public interface MailSender {
	
	public static final String ID_NAME = "mailSender";
	
	/**
	 * 纯文本邮件
	* @Title: sendMail 
	* @Description: TODO
	* @param @param tos：接收人
	* @param @param subject：标题
	* @param @param message:邮件内容
	* @param @throws MessagingException
	* @return void
	* @throws 
	* @date 2014年11月26日 上午10:55:49
	 */
	public void sendMail(String[] tos, String subject, String message);
	
	/**
	 * HTML邮件
	* @Title: sendMimeMail 
	* @Description: TODO
	* @param @param tos
	* @param @param subject
	* @param @param message
	* @return void
	* @throws 
	* @date 2014年11月26日 上午10:57:44
	 */
	 public void sendMimeMail(String[] tos, String subject, String message);
	 
	 /**
	  * HTML邮件，指定编码
	 * @Title: sendMimeMail 
	 * @Description: TODO
	 * @param @param tos
	 * @param @param subject
	 * @param @param message
	 * @param @param encoding
	 * @param @throws MessagingException
	 * @return void
	 * @throws 
	 * @date 2014年11月26日 上午10:59:29
	  */
	 public void sendMimeMail(String[] tos, String subject, String message, String encoding);
	 
	 
	 /**
	  * HTML邮件，抄送，密送，带附件
	 * @Title: sendMimeMail 
	 * @Description: TODO
	 * @param @param tos
	 * @param @param ccs：抄送
	 * @param @param bccs：密送
	 * @param @param subject
	 * @param @param message
	 * @param @param encoding
	 * @param @param files
	 * @param @throws MessagingException
	 * @return void
	 * @throws 
	 * @date 2014年11月26日 上午10:59:37
	  */
	 public void sendMimeMail(String[] tos, String[] ccs, String[] bccs, String subject, String message, String encoding, List<MailAttachment> files);
	 
	 

}
