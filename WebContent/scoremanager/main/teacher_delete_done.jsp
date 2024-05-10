<%-- 科目削除完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<section class="me-4">

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員情報削除</h2>
		<c:if test="${flag == true}">
			<p style="text-align: center;">
				<label style="width: 100%; background-color: #8cc3a9;">
					削除が完了しました </label>
			</p>
			</c:if>
		<c:if test="${flag == false}">
				<p style="text-align: center;">
				<label style="width: 100%; background-color: #8cc3a9;">
					管理者権限をもつ教員を削除することはできません </label>
			</p>
		</c:if>

			<div style="margin-top: 130px;">
				<a href="TeacherList.action">教員一覧</a>
			</div>

		</section>
	</c:param>

</c:import>