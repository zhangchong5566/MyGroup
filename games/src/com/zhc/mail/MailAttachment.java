package com.zhc.mail;

import java.io.File;

/**
 * Email 附件实体
* @ClassName: MailAttachment 
* @Description: TODO
* @author zhangchong
* @date 2014年11月26日 上午11:07:51 
*
 */
public class MailAttachment {
	
	private String fileName;
	
	private File file;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	

}
