<%-- 成績登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
        得点管理システム
    </c:param>
	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="text-align">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			<form action="TestReference.action" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded"
					id="filter">

					<div class="col-2">
						科目情報
					</div>
					<div class="col-2">
						<label class="form-label" for="student-f1-select">入学年度 </label>
						<select class="form-select" id="student-f1-select" name="f1"
							style="margin-bottom: 10px;">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
						<div class="mt-2 text-warning">${errors.get("f1")}</div>
					</div>
					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label> <select
							class="form-select" id="student-f2-select" name="f2"
							style="margin-bottom: 10px;">
							<option value="0">--------</option>
							<c:forEach var="classNum" items="${class_num_set}">
								<option value="${classNum}"<c:if test="${classNum==f2}">selected</c:if>>${class}</option>
							</c:forEach>
						</select>
						<div class="mt-2 text-warning">${errors.get("f2")}</div>
					</div>
					<div class="col-4">
						<label class="form-label" for="student-f3-select">科目</label>
						<select class="form-select" id="student-f3-select" name="f3"
							style="margin-bottom: 10px;">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${sbject_set}">
								<option value="${subject}"<c:if test="${subject==f3}">selected</c:if>>${subject}</option>
							</c:forEach>
						</select>
						<div class="mt-2 text-warning">${errors.get("f3")}</div>
					</div>
					<div class="col-2">
						<button class="btn btn-secondary" id="filter-button"
							style="background-color: #69727a; border: none;">
							変更
						</button>
					</div>
				</div>
			</form>
		</section>
	</c:param>
</c:import>