<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
for(int i = 0;i <1000;i++){
	out.println(i+"<br/>");
	if(i>0&&i%5==0){
		out.flush();
		Thread.sleep(3000);
	}
	
}
%>