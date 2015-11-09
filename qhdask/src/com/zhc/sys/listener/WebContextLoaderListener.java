package com.zhc.sys.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zhc.util.AppProps;
import com.zhc.util.BeanUtil;

public class WebContextLoaderListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {

		super.contextInitialized(event);
		BeanUtil.getInstance().setAppContext(
				WebApplicationContextUtils
						.getRequiredWebApplicationContext(event
								.getServletContext()));

		AppProps props = (AppProps) BeanUtil.getInstance().getBean("appProps");
		String webkey = (String) props.get("web_url");
		event.getServletContext().setAttribute("web_url", webkey);
		webkey = (String) props.get("web_title");
		event.getServletContext().setAttribute("web_title", webkey);
		event.getServletContext().setAttribute("web_image_website",
				(String) props.get("web_image_website"));
		event.getServletContext().setAttribute("website_analytics",
				(String) props.get("website_analytics"));

	}

}
