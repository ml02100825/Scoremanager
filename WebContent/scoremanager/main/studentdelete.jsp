<%-- 学生削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">

			<c:set var="name" value="${requestScope.name}" />
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

			<form action="StudentDeleteexecute.action" method="get">
				<input type="hidden" name="f1" value="${student.getNo()}"></input>
				<div class="text-align">
					<button class="btn btn-secondary" id="filter-button"
						style="background-color: red; border: none;">削除</button>
				</div>
				<div class="mt-2 text-warning">
					<c:out value="${errors}" />
				</div>
				<div class="mt-2 text-warning">
					<ul>
						<c:forEach items="${error}" var="error">
							<p>${error}</p>
						</c:forEach>
					</ul>
				</div>

				<div style="margin-top: 130px;">
					<a href="StudentList.action">戻る</a>
				</div>

			</form>

		</section>
	</c:param>

</c:import>
