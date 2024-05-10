<%-- 科目管理JSP --%>
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

			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">教員管理</h2>

			<div class="my-2 text-end px-4">
				<a href="TeacherCreate.action">新規登録</a>
			</div>

			<table class="table table-hover">
				<tr>
					<th>教員コード</th>
					<th>教員名</th>
					<th></th>
					<th></th>
				</tr>

				<c:choose>
					<c:when test="${empty teacher_list}"></c:when>
					<c:otherwise>
						<c:forEach var="teacher" items="${teacher_list}">
							<tr>
								<td>${teacher.id}</td>
								<td>${teacher.name}</td>
								<td><a href="TeacherUpdate.action?id=${teacher.id}">変更</a></td>
								<td><a href="TeacherDelete.action?id=${teacher.id}">削除</a></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>

		</section>
	</c:param>

</c:import>