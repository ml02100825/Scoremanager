<%-- 学生変更JSP --%>
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


			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員情報変更</h2>

			<form action="TeacherUpdateExecute.action" method="get">
				<div class="text-align:">
					<label class="form-label" for="teacher-f1-select">教員番号</label><br>
					<input type="text" name="f1" value="${teacher.getId()}" readonly
						style="width: 100%; height: 40px; margin-bottom: 10px; border: none;">
				</div>

				<div class="text-align:">
					<label class="form-label" for="teacher-f2-select">教員名</label><br>
					<input type="text" name="f2" value="${teacher.getName()}"
						maxlength="30" required
						style="width: 100%; height: 40px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 5px;">
				</div>

				<div class="text-align:">
					<div class="text-align:">
						<label class="form-label" for="teacher-f3-select">管理者権限</label> <input
							type="checkbox" name="f3" value="t">
					</div>


					<div class="text_align">
						<button class="btn btn-secondary" id="filter-button"
							style="margin-bottom: 10px;">変更</button>
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
				</div>


				<div style="">
					<a href="TeacherList.action">戻る</a>
				</div>
			</form>

		</section>
	</c:param>

</c:import>
