package com.zhc.sys.action.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.zhc.sys.action.BaseAction;

public class LoginFilter implements Filter {
	private String login_url = "/login.jsp";
	private String defaultEncode = "UTF-8";
	private String[] notCheckPath = new String[0];


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.defaultEncode);

		String nowUrl = ((HttpServletRequest) request).getRequestURI();
		System.out.println("LoginFilter:"+nowUrl);
		boolean isNotCheckJsp = false;
		
		if (this.notCheckPath.length > 0) {
			for (String s : this.notCheckPath) {
				if (nowUrl.indexOf(s) != -1) {
					isNotCheckJsp = true;
					break;
				}
			}
		}

		if (isNotCheckJsp) {
			chain.doFilter(request, response);
		} else if (!BaseAction.hasLogin((HttpServletRequest) request)) {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath() + this.login_url);
//			request.getRequestDispatcher(this.login_url).forward(request,
//					response);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		String _login_url = config.getInitParameter("login_url");
		String encode = config.getInitParameter("Encode");
		String _notCheckPath = config.getInitParameter("notCheckPath");
		if (_login_url != null) {
			this.login_url = _login_url;
		}
		if (encode != null) {
			this.defaultEncode = encode;
		}
		if (StringUtils.isNotBlank(_notCheckPath)) {
			this.notCheckPath = _notCheckPath.split(",");
		}
		System.out.println("验证的Filter:" + this.login_url);
		System.out.println("当前编码：" + this.defaultEncode);
		System.out.println("不需要验证的页面：" + _notCheckPath);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
