<%-- 共通テンプレート --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
</head>
<body>
<div>
    <%
        // リダイレクト先のURLを設定
        String redirectURL = "/scoremanager/scoremanager/Login.action";
        // リダイレクトを実行
        response.sendRedirect(redirectURL);
    %>
</div>
</body>
</html>