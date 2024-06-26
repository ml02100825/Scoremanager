<%-- 学生登録JSP --%>
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

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

			<form action="StudentCreateexecute.action" method="post">
				<div style="text-align:">
					<label class="form-label" for="student-f1-select">入学年度 </label> <select
						class="form-select " id="student-f1-select" name="ent_year"
						style="margin-bottom: 10px;">
						<option value="0">--------</option>
						<c:forEach var="year" items="${ent_year_set}">
							<option value="${year}"
								<c:if test="${year eq f1}">selected</c:if>>${year}</option>
						</c:forEach>
					</select>
					<div class="mt-2 text-warning">${errors.f1}</div>
				</div>

				<div class="text-align:">
					<label class="form-label" for="student-f2-select">学生番号</label> <input
						type="text" name="no"
						value="${not empty param.f2 ? param.f2 : ''}"
						placeholder="学生番号を入力してください" maxlength="10" required
						style="width: 100%; height: 40px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 5px;">
					<div class="mt-2 text-warning">${errors.f2}</div>
				</div>

				<div class="text-align:">
					<label class="form-label" for="student-f3-select">氏名</label> <input
						type="text" name="name"
						value="${not empty param.f3 ? param.f3 : ''}"
						placeholder="氏名を入力してください" maxlength="30" required
						style="width: 100%; height: 40px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 5px;">
					<div class="mt-2 text-warning">${errors.f3}</div>

					<label class="form-label" for="student-f4-select">クラス</label> <select
						class="form-select " id="student-f4-select" name="class_num"
						style="margin-bottom: 10px;">
						<option value="0">--------</option>
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}" <c:if test="${num eq f4}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
					<div class="text-align">
						<button class="btn btn-secondary" name=end id="filter-button">登録して終了</button>
					</div>
					<div class="mt-2 text-warning">${errors.f4}</div>
					<a href="StudentList.action">戻る</a>
				</div>
			</form>

		</section>
	</c:param>
</c:import>
