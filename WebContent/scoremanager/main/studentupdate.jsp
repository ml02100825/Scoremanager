<%-- 共通テンプレート --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name = "title">
	得点管理システム
</c:param>
<c:param name="scripts"></c:param>

<c:param name="content">
<section class="me-4">
<c:set var="name" value="${requestScope.name}" />

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

			<form action="StudentUpdateexecute.action" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-4">
						<label class="form-label" for="student-f1-select">入学年度 </label>
							<input type="text"  name = "f1" value="${student.getEntyear()}" readonly>
					</div>
					<div class="col-4">
						<label class="form-label" for="student-f2-select">学生番号</label>
					<input type="text"  name = "f2"  value="${student.getNo()}" readonly>
					</div>
					<div class="col-4">
							<div class="col-4">
						<label class="form-label" for="student-f3-select"  >氏名</label>
					<input type="text"  name = "f3" value="${student.getName()}">
					</div>
					<div class="col-4">
						<label class="form-label" for="student-f4-select">クラス</label>
						<select class="form-select " id="student-f4-select" name="f4"  >
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${num}" 	<c:if test="${num.contains(student.getClassNum())}">selected</c:if>>${num}</option>
							</c:forEach>

						</select>
					</div>
					<div class="col-2 form-check text-center">
						<label class="form-check-label" for="student-f5-check">在学中
							<%-- パラメーターf3が存在している場合checkedを追記 --%>
							<input class="form-check-input" type="checkbox"
							id="student-f5-check" name="f5" value="t" <c:if test="${student.getIsAttend()}">checked</c:if> />
							<c:if test="${!empty f5}">checked</c:if>
						</label>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">登録して終了</button>
					</div>
			<div class="mt-2 text-warning"><c:out value="${errors}" /></div>
					<div class="mt-2 text-warning">
			<ul>

				<c:forEach items="${error}" var="error">
					<p>${error}</p>
				</c:forEach>
			</ul>
		</div>
			<a href="StudentList.action">戻る</a>
		</div>
		</form>

	</section>
	</c:param>
</c:import>
