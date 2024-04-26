<%-- 科目変更JSP --%>
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

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

			<form action="SubjectUpdateExecute.action" method="get">
				<div class="text-align:">
					<label class="form-label" for="student-f1-select">科目コード</label><br>
					<input type="text"  name = "f1"  value="${subject.getCd()}"
						readonly style="width: 100%; height: 40px; margin-bottom: 10px; border: none;">
				</div>

				<div class="text-align:">
					<label class="form-label" for="student-f2-select">科目名</label><br>
					<input type="text"  name = "f2"  value="${subject.getName()}"
						maxlength="20" required style="width: 100%; height: 40px; margin-bottom: 10px;
						border: 1px solid #ccc; border-radius: 5px;">
				</div>

				<div style="text-align;">
					<button class="btn btn-secondary" id="filter-button"
						style="margin-bottom: 10px; background-color: #0d6efd; border: none;">
						変更
					</button>
				</div>

				<div style="text-align;">
					<a href="SubjectList.action">戻る</a>
				</div>
			</form>

		</section>
	</c:param>

</c:import>
