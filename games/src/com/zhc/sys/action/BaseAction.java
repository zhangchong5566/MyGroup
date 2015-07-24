package com.zhc.sys.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.zhc.sys.entity.SysMenu;
import com.zhc.sys.entity.SysUser;
import com.zhc.sys.service.base.Pages;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;


	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected HttpSession session = null;

	private Pages pages;
	private String sEcho; // 点击分页的次数，需要不变的传回去
	private String iDisplayStart; // 当你点击下一页或者页数的时候会传到后台的值
	private String iDisplayLength; // 默认是传10
	private String iTotalRecords;
	private String iTotalDisplayRecords;
	private Integer iSortCol_0;// 排序字段索引
	private String sSortDir_0;// desc/asc

	public static String sysuserSession = "sysuser_session";
	public static String syspopedomSession = "syspopedom_session";
	public static String sysroleSession = "sysrole_session";
	public static String sysmenuSession = "sysmenu_session";

	private static boolean start = false;

	public BaseAction() {
		// if(BaseAction.start){
		if (this.request == null) {
			this.request = ServletActionContext.getRequest();
			this.response = ServletActionContext.getResponse();
			this.session = ServletActionContext.getRequest().getSession();
		} else {
			BaseAction.start = true;
		}
		String sortCol = this.getStr("iSortCol_0");
		iSortCol_0 = sortCol != null ? Integer.parseInt(sortCol) : -1;
		sSortDir_0 = this.getStr("sSortDir_0");
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

		int Pg = 0;
		int pageSize = 0;

		if (StringUtils.isNotBlank(this.getStr("sEcho"))) {
			sEcho = this.getStr("sEcho");
			iDisplayStart = this.getStr("iDisplayStart");
			iDisplayLength = this.getStr("iDisplayLength");

			pageSize = Integer.parseInt(iDisplayLength);
			Pg = (int) (Integer.parseInt(iDisplayStart) / pageSize) + 1;

		} else {
			Pg = this.getIntParamter("currentPage", 1);
			pageSize = this.getIntParamter("pageSize", 10);
		}

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

	public void setLoginSysUser(SysUser user) {
		session.setAttribute(sysuserSession, user);
	}

	@JSON(serialize = false)
	public SysUser getLoginSysUser() {
		return (SysUser) session.getAttribute(sysuserSession);
	}

	public void setPopedom(List<String> list) {
		session.setAttribute(syspopedomSession, list);
	}

	public void setMenu(List<SysMenu> menus) {
		session.setAttribute(sysmenuSession, menus);
	}

	// 判断是否已经登录
	public static boolean hasLogin(HttpServletRequest request) {
		return request.getSession().getAttribute(sysuserSession) != null ? true
				: false;
	}

	// ///////////////////////////////////////以下为dataTables序列化的参数////////////////////////////////////////////////////
	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(String iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public String getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(String iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public String getiTotalRecords() {
		return this.getPages() != null ? this.getPages().getReCount() + "" : "";
	}

	public void setiTotalRecords(String iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public String getiTotalDisplayRecords() {
		return this.getPages() != null ? this.getPages().getReCount() + "" : "";
	}

	public void setiTotalDisplayRecords(String iTotalDisplayRecords) {

		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public Integer getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(Integer iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}


}
