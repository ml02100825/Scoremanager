<%-- 共通テンプレート --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name = "title">
	メニュー
	</c:param>
<c:param name="content">

<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">メニュー	</h2>
<div class="row">
	<div class="col-3 offset-3">
<div class="b" style="height: 10rem; background-color: #dbb;">
<a   href="StudentList.action" >学生管理</a>
</div>
</div>
</div>
</c:param>
</c:import>