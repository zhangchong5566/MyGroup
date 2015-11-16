package com.zhc.webtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.zhc.sys.service.base.Pages;

public class PagesFormTag extends TagSupport {

	private static final long serialVersionUID = 8318470947313157427L;
	
	Pages pages = null;
	private String name;
	private String action;
	private String method = "post";
	private String scope;
	private String pageSize = "5";
	private String showPage = "5";// 每页显示的页码数
	
	private String urltype="dynamic";//dynamic/staticize（动态/静态），如果是staticize,action为url的前缀，如list,最后连接地址补全页码，如list_1_20.html，代表显示第1页，每页显示20条数据
	
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public int doStartTag() throws JspException {
		try {
			this.pages = ((Pages) TagUtil.getInstance().lookup(
					this.pageContext, this.name, this.scope));
			JspWriter out = this.pageContext.getOut();
			out.println("<div class=\"text-center\">");
			out.println("<script>");
			out.println("function trunPage(value){document.formPage.currentPage.value = value;document.formPage.submit();}");
			out.println("</script>");
			
			out.println("<form name=\"formPage\" action=\"" + this.action
					+ "\" method=\"" + this.method + "\"> ");
			out.println("<input type=\"hidden\" name=\"currentPage\" id=\"currentPage\" value=\""+this.pages.getPage()+"\">");
			out.println("<input type=\"hidden\" name=\"pageSize\" id=\"pageSize\" value=\""+this.getPageSize()+"\">");
			
			out.println("<ul id=\"visible-pages-example\" class=\"pagination\">");
			
			//out.println("<li><i>Showing "+this.pages.getFirstItemIndex()+" to "+this.pages.getLastItemIndex()+" of "+this.pages.getReCount()+" entries</i></li>");
			
			if(this.pages.getPage() != 1){
				out.println("<li class=\"first\"><a href=\""+getHref(1)+"\">第一页</a></li>");
				out.println("<li class=\"prev\"><a href=\""+getHref(this.pages.getPage()-1)+"\">上一页</a></li>");
			}else{
				out.println("<li class=\"first disabled\"><a href='javascript:void(0)'>第一页</a></li>");
				out.println("<li class=\"prev disabled\"><a href='javascript:void(0)'>上一页</a></li>");
			}
			//out.println("<li><ol>");
			int startpage;// 页面中的起始页  
		    int endpage;// 页面中的结束页  
		    int _showPage = Integer.parseInt(this.showPage);
		    
		    
		    if (this.pages.getRePages() < _showPage) {
	            startpage = 1; // 页面中起始页就是1  
	            endpage = this.pages.getRePages();// 页面中的最终页就是总页数  
	        } else {  
	            /** else中是总页数大于4的情况 */  
	  
	            /** 首先当前页的值是否小于等于4 */  
	            if (this.pages.getPage() <= _showPage) {  
	                startpage = 1;  
	                //endpage = this.pages.getPage()  + 2;
	                endpage = _showPage;
	                /** 判断页面的最终页是否大于总页数 */  
	                if (endpage >= this.pages.getRePages()) {  
	                    endpage = this.pages.getRePages();  
	                }  
	            } else {  
	                startpage = this.pages.getPage() - 2;
	                endpage = this.pages.getPage() + _showPage-3;
	                
	                if (endpage >= this.pages.getRePages()) {
	                    endpage = this.pages.getRePages();
	                    if (this.pages.getRePages() < _showPage) {
	                        startpage = 1;  
	                    } else {  
	                        startpage = endpage - _showPage+1;
	                    }  
	  
	                }
	            }
	        }
		    
		    for(int i = startpage;i<=endpage;i++){
		    	
		    	if(i==this.pages.getPage()){
		    		out.println("<li class=\"page active\"><a>"+i+"</a></li>");
		    	}else{
		    		out.println("<li class=\"page\"><a href=\""+getHref(i)+"\">"+i+"</a></li>");
		    	}
		    }
		    
		    //out.println("</ol></li>");
			if(this.pages.getPage() < this.pages.getRePages()){
				out.println("<li class=\"next\"><a href=\""+getHref(this.pages.getPage()+1)+"\">下一页</a></li>");
				out.println("<li class=\"last\"><a href=\""+getHref(this.pages.getRePages())+"\">最后一页</a></li>");
			}else{
				out.println("<li class=\"next disabled\"><a href='javascript:void(0)'>下一页</a></li>");
				out.println("<li class=\"last disabled\"><a href='javascript:void(0)'>最后一页</a></li>");
			}
			
			
		/*	out.println("<li>&nbsp;&nbsp;&nbsp;&nbsp;每页<select name=\"pageSize\" id=\"pageSize\" style=\"width:80px;\" onchange='trunPage("+this.pages.getPage()+")'>"
					+ "<option value=\"10\" "+(this.pages.getPageSize()==10?"selected='selected'":"")+">10</option>"
					+ "<option value=\"20\" "+(this.pages.getPageSize()==20?"selected='selected'":"")+">20</option>"
					+ "<option value=\"50\" "+(this.pages.getPageSize()==50?"selected='selected'":"")+">50</option>"
					+ "<option value=\"100\" "+(this.pages.getPageSize()==100?"selected='selected'":"")+">100</option>"
					+ "</select>条</li>");*/
			out.println("<li style=\"line-height:30px;padding-left:20px;color:red;\">合计："+this.pages.getReCount()+"条记录, 共"+this.pages.getRePages()+"页.</li>");
			out.println("</ul>");
			
			out.println("</div>");
			
//			out.println("<script>");
//			out.println("function pageFormSetPageSize(form,value){ var p1 = /^\\d{1,}$/; if(!p1.test(value)){alert(\"分页只能是数字！\");return ; } form.submit();}");
//			out.println("function pageFormGo(form,value){ form.Pg.value = value; form.submit();}");
//			out.println("function pageFormSetPg(form,value){ var p1 = /^\\d{1,}$/; if(!p1.test(value)){alert(\"分页只能是数字！\");return ; } form.Pg.value = value;form.submit();}");
//			out.println("</script>");
//			out.println("<form name=\"formPage\" action=\"" + this.action
//					+ "\" method=\"" + this.method + "\"> ");
//
//			out.println("每页显示<input name=\"pageSize\" onblur=\"pageFormSetPageSize(this.form,this.value)\" value=\""
//					+ this.pages.getPageSize()
//					+ "\" type=\"text\" id=\"pageSize\"   style=\"width:20px; border:solid #CCC 1px\"/>");
//			out.println("条&nbsp;|&nbsp;共<font color=\"red\">"
//					+ this.pages.getRePages() + "</font>页 , <font color=\"red\">"
//					+ this.pages.getReCount() + "</font>条数据");
//
//			out.println("&nbsp;|&nbsp;");
//
//			out.println("<input type=\"button\"  "
//					+ (this.pages.getPage() == 1 ? "disabled=\"disabled\"" : "")
//					+ " onclick=\"pageFormGo(this.form,1)\" name=\"button\" id=\"button\" value=\"首页\"  style=\"background:#FFF ; border:none; cursor:pointer\"/>&nbsp;");
//			out.println("<input type=\"button\" "
//					+ (this.pages.getPage() == 1 ? "disabled=\"disabled\"" : "")
//					+ " onclick=\"pageFormGo(this.form,"
//					+ (this.pages.getPage() - 1)
//					+ ")\" name=\"button\" id=\"button\" value=\"上一页\"  style=\"background:#FFF ; border:none; cursor:pointer\"/>&nbsp;");
//
//			out.println("<input type=\"button\" "
//					+ (this.pages.getPage() >= this.pages.getRePages() ? "disabled=\"disabled\""
//							: "")
//					+ " onclick=\"pageFormGo(this.form,"
//					+ (this.pages.getPage() + 1)
//					+ ")\" name=\"button\" id=\"button\" value=\"下一页\"  style=\"background:#FFF ; border:none; cursor:pointer\"/>&nbsp;");
//			out.println("<input type=\"button\" "
//					+ (this.pages.getPage() >= this.pages.getRePages() ? "disabled=\"disabled\""
//							: "")
//					+ " onclick=\"pageFormGo(this.form,"
//					+ this.pages.getRePages()
//					+ ")\" name=\"button\" id=\"button\" value=\"尾页\"  style=\"background:#FFF ; border:none; cursor:pointer\"/>&nbsp;");
//
//			out.println("&nbsp;|&nbsp;第<input name=\"Pg\" onblur=\"pageFormSetPg(this.form,this.value)\" value=\""
//					+ this.pages.getPage()
//					+ "\" type=\"text\" id=\"Pg\" style=\"width:20px; border:solid #CCC 1px\"/>页");
			
			
		} catch (Exception e) {
			throw new JspException("分页标签出错了..." + e);
		}
		return 6;
	}
	
	public String getHref(int page){
		if(StringUtils.equals(this.getUrltype(), "staticize")){
			return this.action+"_"+page+"_"+this.getPageSize()+".html";
		}
		return "javascript:trunPage("+page+")";
	}

	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.println("</form> ");
		} catch (Exception e) {
			throw new JspException("分页标签出错了..." + e);
		}
		return 0;
	}

	public void release() {
		this.name = null;
		this.action = null;
		this.method = null;
		this.pages = null;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getScope() {
		return this.scope;
	}

	public String getShowPage() {
		return showPage;
	}

	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}

	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
