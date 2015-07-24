package com.zhc.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtils {

	private static Configuration cfg = new Configuration();

	public static String parseTemplateContent(String sourceContent, Map dataMap) {
		
		if(StringUtils.isBlank(sourceContent)){
			return null;
		}

		try {
			
			StringReader reader = new StringReader(sourceContent);
			Template template = new Template("content", reader, cfg);
			StringWriter stringWriter = new StringWriter();
			template.process(dataMap, stringWriter);
			return stringWriter.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static void main(String[] args) {
		String s = "hello world! ${name}";
		Map map = new HashMap();
		map.put("name", "zhangchong");
		System.out.println(parseTemplateContent(s, map));
	}

}
