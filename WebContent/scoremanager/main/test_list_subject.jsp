
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

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧（科目）</h2>

			<div
				class="row border mx-3 mb-3 py-2 align-items-center justify-content-center rounded"
				id="filter">

				<form action="TestListSubjectExecute.action" method="get"
					class="row align-items-center">
					<div class="col-2">科目情報</div>

					<div class="col-2">
						<label class="form-label" for="student-f1-select">入学年度 </label> <select
							class="form-select" id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
						<div class="mt-2 text-warning">${errors.get("f1")}</div>
					</div>

					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label> <select
							class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="classNum" items="${class_num_set}">
								<option value="${classNum}"
									<c:if test="${classNum==f2}">selected</c:if>>${classNum}</option>
							</c:forEach>
						</select>
						<div class="mt-2 text-warning">${errors.get("f2")}</div>
					</div>

					<div class="col-4" style="margin-top: -1px;">
						<label class="form-label" for="student-f3-select">科目</label> <select
							class="form-select" id="student-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subject_set}">
								<option value="${subject.cd}"
									<c:if test="${subject.name==f3}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
						<div class="mt-2 text-warning">${errors.get("f3")}</div>
					</div>

					<div class="col-2">
						<button class="btn btn-secondary" id="filter-button"
							style="background-color: #69727a; border: none;">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f3")}</div>
				</form>

				<div class="text-align">
					<div style="border-bottom: 1px solid #ccc; margin-bottom: 10px;"></div>
				</div>

				<form action="TestListStudentExecute.action" method="get"
					class="row align-items-center">
					<div class="col-2">学生情報</div>

					<div class="col-4">
						<label class="form-label" for="student-f4-select">学生番号</label>
						<c:forEach var="student" items="${student_set}">
							<option value="${student}"
								<c:if test="${student==f4}">selected</c:if>>${student}</option>
						</c:forEach>
						<input type="text" id="student-f4-select" name="f4" value="${f4}"
							placeholder="学生情報を入力してください" maxlength="10" required
							style="width: 100%; height: 40px; border: 1px solid #ccc; border-radius: 5px;">
						<div class="mt-2 text-warning">${errors.get("f4")}</div>
					</div>

					<div class="col-2">
						<button class="btn btn-secondary" id="filter-button"
							style="background-color: #69727a; border: none;">検索</button>
					</div>
				</form>

			</div>

		</section>

		<c:choose>
			<c:when test="${tests.size() > 0}">
				<div>科目：${subject.name}</div>
				<table class="table table-hover">
					<tr>
						<th>入学年度</th>
						<th>クラス</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th>1回目</th>
						<th>2回目</th>
					</tr>

					<c:forEach var="test" items="${tests}">
						<tr>
							<td>${test.entYear}</td>
							<td>${test.classNum}</td>
							<td>${test.studentNo}</td>
							<td>${test.studentName}</td>
							<td>${test.getPoint(1) == "null" ? '-' : test.getPoint(1)}</td>
							<td>${test.getPoint(2) == "null" ? '-' : test.getPoint(2)}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div>成績情報が存在しませんでした</div>
			</c:otherwise>
		</c:choose>


	</c:param>
</c:import>
