<%-- 共通テンプレート --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name = "title">
	ログアウト
</c:param>
<c:param name="scripts"></c:param>

<c:param name="content">
ログアウトしました
<a href="../Login.action">ログイン</a>

	</c:param>
</c:import>
