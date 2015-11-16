package com.zhc.ask.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zhc.ask.entity.AskClassify;
import com.zhc.ask.entity.AskMember;
import com.zhc.ask.service.AskClassifyService;
import com.zhc.sys.service.base.Pages;
import com.zhc.util.BeanUtil;

public class WebBaseAction extends ActionSupport {

	private static final long serialVersionUID = 2L;
	
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected HttpSession session = null;

	private Pages pages;

	public static final String memberSession = "member_session";
	
	public static final String context_askclassify = "context_askclassify";
	
	private static boolean start = false;
	

	public WebBaseAction() {
		// if(BaseAction.start){
		if (this.request == null) {
			this.request = ServletActionContext.getRequest();
			this.response = ServletActionContext.getResponse();
			this.session = ServletActionContext.getRequest().getSession();
		} else {
			WebBaseAction.start = true;
		}
		if(ServletActionContext.getServletContext().getAttribute(context_askclassify) == null){
			updateContextClassify(ServletActionContext.getServletContext());
		}
	}
	
	public static void updateContextClassify(ServletContext context){
		AskClassifyService classifyService = (AskClassifyService) BeanUtil.getInstance().getBean(AskClassifyService.ID_NAME);
		List<AskClassify> context_askClassifyList = classifyService.getTree(1);
		context.setAttribute(context_askclassify, context_askClassifyList);
	}

	@JSON(serialize = false)
	public int getIntParamter(String _param, int _defaultValue) {
		int dv = _defaultValue;
		int rdv = 0;
		String strIn = request.getParameter(_param);
		if (StringUtils.isBlank(strIn)) {
			return dv;
		}
		try {
			rdv = Integer.parseInt(strIn);
		} catch (Exception e) {
			rdv = dv;
		}
		return rdv;
	}

	@JSON(serialize = false)
	public long getLongParamter(String _param, long _defaultValue) {
		long dv = _defaultValue;
		long rdv = 0L;
		String strIn = request.getParameter(_param);
		if (StringUtils.isBlank(strIn)) {
			return dv;
		}
		try {
			rdv = Long.parseLong(strIn);
		} catch (Exception e) {
			rdv = dv;
		}
		return rdv;
	}

	@JSON(serialize = false)
	public String getStr(String _strSrc) {
		return getStr(_strSrc, null);
	}

	@JSON(serialize = false)
	public String getStr(String _param, String _defaultValue) {
		String dv = _defaultValue;

		String strIn = request.getParameter(_param);
		if (StringUtils.isBlank(strIn)) {
			return dv;
		}
		return strIn.trim();
	}

	@JSON(serialize = false)
	public String getStr(String _param, String _defaultValue, String _encoding) {
		String dv = _defaultValue;

		String strIn = request.getParameter(_param);
		if (StringUtils.isBlank(strIn)) {
			return dv;
		}
		return encodingStr(strIn, _encoding);
	}

	public static String encodingStr(String _strSrc, String _encoding) {
		if ((_encoding == null) || (_encoding.length() == 0)) {
			return _strSrc;
		}
		try {
			byte[] byteStr = new byte[_strSrc.length()];
			char[] charStr = _strSrc.toCharArray();
			for (int i = byteStr.length - 1; i >= 0; i--) {
				byteStr[i] = ((byte) charStr[i]);
			}
			return new String(byteStr, _encoding);
		} catch (Exception ex) {
		}
		return _strSrc;
	}

	@JSON(serialize = false)
	public Pages getReqPages() {

		int Pg = this.getIntParamter("currentPage", 1);
		int pageSize = this.getIntParamter("pageSize", 10);

		pages = new Pages();
		pages.setPage(Pg);
		pages.setPageSize(pageSize);
		return pages;
	}

	@JSON(serialize = false)
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public void setLoginMember(AskMember member) {
		session.setAttribute(memberSession, member);
	}

	@JSON(serialize = false)
	public AskMember getLoginMember() {
		return (AskMember) session.getAttribute(memberSession);
	}


	// 判断是否已经登录
	public static boolean hasLogin(HttpServletRequest request) {
		return request.getSession().getAttribute(memberSession) != null ? true
				: false;
	}

}
