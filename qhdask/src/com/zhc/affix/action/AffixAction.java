package com.zhc.affix.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.zhc.affix.entity.Affix;
import com.zhc.affix.service.AffixConfig;
import com.zhc.affix.service.AffixService;
import com.zhc.sys.action.BaseAction;

@Controller
@ParentPackage("json-default")
public class AffixAction extends BaseAction {

	@Resource(name = AffixService.ID_NAME)
	private AffixService affixService;

	@Resource(name = AffixConfig.ID_NAME)
	private AffixConfig affixConfig;

	/**
	 * 文件内容
	 */
	private File file;

	// 封装上传文件类型的属性
	private String fileContentType;
	// 封装上传文件名的属性
	private String fileFileName;
	/**
	 * 对像id，例如人员id
	 */
	private String objectId;//
	/**
	 * 对像类别，例如 1：人员照片 2：人员档案 3：部门档案
	 */
	private Integer objectType;//

	private Affix affix;

	private List<Affix> beans;

	private String img;

	private int affixSize;

	private String message;

	/**
	 * 通用附件上传，附件有objectType和objectId两个字段，objectType用于区别附件类别，
	 * 一般情况下开发时可以将所有需要上传附件的地方做一个分类
	 * 例如：人员照片、个人文档、部门文档，可以定义三个objectType分别是1、2、3，objectId则用于存放该人员的id或是该部门的id
	 * 如果需要存储某个人员的照片，可以使用条件 objectType=1，objectId=人员id存储，如需存储某人员的文档，可以使用使用条件
	 * objectType=2，objectId=人员id存储
	 */
	@Action(value = "/affix/uploadFile", results = { @Result(name = SUCCESS, location = "/affix/file_upload_ok.jsp") })
	public String uploadFile() {
		
		affix = saveFile();
		request.setAttribute("affix", affix);

		return SUCCESS;
	}
	
	private Affix saveFile(){
		try {

			Calendar c = Calendar.getInstance();
			String uploadPath;

			if (file == null) {
				System.out.println("没有选择附件.");
				return null;

			}

			if (fileContentType.startsWith("image")) {
				uploadPath = "/images/" + c.get(Calendar.YEAR) + "/" + c.get(Calendar.DAY_OF_MONTH);
			} else {
				uploadPath = "/file/" + c.get(Calendar.YEAR) + "/" + c.get(Calendar.DAY_OF_MONTH);
			}

			if (affixConfig.getDirs() != null && affixConfig.getDirs().get(objectType) != null) {
				uploadPath = "/" + affixConfig.getDirs().get(objectType) + uploadPath;
			}
			String path = "";
			String filePath = affixConfig.getBaseDir() + uploadPath;
			path = uploadPath;
			File dir = new File(filePath);
			if (!dir.exists())
				dir.mkdirs();
			// 创建一个随机的文件ID
			String objId = UUID.randomUUID().toString();
			String extendName = fileFileName.substring(fileFileName.lastIndexOf(".") + 1);

			String saveName = objId + "." + extendName;// 完整的文件名
			path += "/" +saveName;
			FileUtils.copyFile(file, new File(filePath,saveName));
//			BufferedOutputStream bout = new BufferedOutputStream(
//					new FileOutputStream(saveName));
//
//			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
//			// System.out.println(fileForm.getFile().getFileData().length);
//			createFile(bin, bout);
			
			Affix _affix = new Affix();
			_affix.setObjectId(objectId);
			_affix.setObjectType(objectType);
			_affix.setSource(filePath + "/" + objId + "." + extendName);

			System.out.println(file.length());
			String fileSizeStr = AffixConfig.formetFileSize(file.length());

			_affix.setSize(fileSizeStr);
			_affix.setCreateDate(new Date());

			_affix.setExtname(extendName.toLowerCase());
			_affix.setType(fileContentType);
			_affix.setName(fileFileName);

			_affix.setPath(path);
			affixService.create(_affix);
			return _affix;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 查询附件列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/affix/list", results = { @Result(name = SUCCESS, location = "/affix/file_upload_list.jsp"), 
			@Result(name = "IMG", location = "/affix/file_upload_list_images.jsp") })
	public String list() {
		beans = affixService.getAffixs(objectType, objectId);

		request.setAttribute("beans", beans);
		request.setAttribute("affixSize", beans.size());

		if (StringUtils.isNotBlank(img)) {
			return "IMG";
		}
		return SUCCESS;
	}

	/**
	 * 通用附件下载类 通过附件id直接下载该附件
	 * 
	 * @throws IOException
	 */
	@Action(value = "/affix/download")
	public void download() throws IOException {
		// response.setCharacterEncoding("UTF-8");
		// response.setContentType("application/x-msdownload");//
		// 不同类型的文件对应不同的MIME类型
		long id = super.getLongParamter("id", -1);

		if (id == -1)
			return;
		Affix affix = affixService.find(Affix.class, id);

		response.reset();
		response.addHeader("Content-Disposition", "attachment; filename=" + new String(affix.getName().getBytes("UTF-8"), "iso-8859-1"));
		// response.setContentType(affix.getAtype());
		response.setContentType("application/octet-stream");
		// 取得文件上传地址
		String filePath = affix.getSource();
		OutputStream out = response.getOutputStream();
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(filePath));

		createFile(bin, out);

		// 增加下载次数
		if (affix.getDownNum() == null) {
			affix.setDownNum(0l);
		} else {
			affix.setDownNum(affix.getDownNum() + 1);
		}
		affixService.update(affix);

	}
	
	@Action(value = "/uploadBaiduImg")
	public void uploadBaiduImg() throws IOException {
		response.setCharacterEncoding("utf-8");
		
		String callback = request.getParameter("callback");
		String state = "SUCCESS";
		affix = saveFile();
		String fileUrl = "/affix/showImage.do?id="+affix.getId();
		String result = "{\"name\":\"" + fileUrl + "\", \"originalName\": \""
				+ affix.getName() + "\", \"size\": "
				+ file.length() + ", \"state\": \"" + state
				+ "\", \"type\": \""
				+ affix.getExtname() + "\", \"url\": \""
				+ fileUrl + "\"}";

		result = result.replaceAll("\\\\", "\\\\");
		// ajax返回数据
		if (callback == null) {
			response.getWriter().print("<pre>"+result+"</pre>");
		} else {
			response.getWriter().print(
					"<script>" + callback + "(" + result + ")</script>");
		}
		
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 图片查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/affix/showImage")
	public void showImage() throws IOException {
		// 不同类型的文件对应不同的MIME类型
		long id = super.getLongParamter("id", -1);
		String filePath = "";
		if (id == -1) {
			filePath = request.getRealPath("/images/no-photo.png");
		} else {
			Affix affix = affixService.find(Affix.class, id);
			response.reset();
			response.addHeader("Content-Disposition", "attachment; filename=" + new String(affix.getName().getBytes(), "iso-8859-1"));
			// response.setContentType(affix.getType());
			String ctype = "application/octet-stream";

			if (affix.getExtname().toLowerCase().equals("jpg")) {
				ctype = "image/jpeg";
			} else if (affix.getExtname().toLowerCase().equals("png")) {
				ctype = "image/png";
			} else if (affix.getExtname().toLowerCase().equals("gif")) {
				ctype = "image/gif";
			}

			response.setContentType(ctype);
			System.out.println("===>" + ctype);
			// response.setContentType("application/octet-stream");
			// 取得文件上传地址
			filePath = affix.getSource();
		}
		try {
			OutputStream out = response.getOutputStream();
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(filePath));
			createFile(bin, out);
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
	}

	@Action(value = "/affix/delete", results = { @Result(name = SUCCESS, type = "json", params = { "ignoreHierarchy", "false" }) })
	public String delete() {
		long id = super.getLongParamter("id", 0);
		affixService.deleteByAffixId(id);
		
		message = "Success";
		return SUCCESS;
		// return mapping.findForward(this.SUCCESS);
	}


	
	public void createFile(InputStream in, OutputStream out) throws IOException {
		try {
			byte[] bf = new byte[1048576];
			int len = -1;
			while ((len = in.read(bf)) != -1) {
				out.write(bf, 0, len);
			}
			out.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Affix getAffix() {
		return affix;
	}

	public void setAffix(Affix affix) {
		this.affix = affix;
	}

	public List<Affix> getBeans() {
		return beans;
	}

	public void setBeans(List<Affix> beans) {
		this.beans = beans;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getAffixSize() {
		return affixSize;
	}

	public void setAffixSize(int affixSize) {
		this.affixSize = affixSize;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
