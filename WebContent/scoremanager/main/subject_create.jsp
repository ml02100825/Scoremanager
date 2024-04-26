<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">

			<h2 class="h2 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4"
				style="text-align: center;">科目情報登録</h2>

			<form action="SubjectCreateExecute.action" method="post">
				<div style="text-align;">
					<label>科目コード</label><br>
					<input type="text" name="cd" value="${cd }"
						placeholder="科目コードを入力してください" maxlength="3" required
						style="width: 100%; height: 40px; margin-bottom: 10px;
						border: 1px solid #ccc; border-radius: 5px;">
				</div>

				<div style="text-align">
					<c:if test="${not empty errors1 }">
						<c:forEach var="errors1" items="${errors1 }">
							<span style="color:#ffd9a3;">${errors1 }</span>
						</c:forEach>
						<div style="margin-bottom:10px;"></div>
					</c:if>
				</div>

				<div style="text-align">
					<c:if test="${not empty errors2 }">
						<c:forEach var="errors2" items="${errors2 }">
							<span style="color:#ffd9a3;">${errors2 }</span>
						</c:forEach>
						<div style="margin-bottom:10px;"></div>
					</c:if>
				</div>

				<div style="text-align;">
					<label>科目名</label><br>
					<input type="text" name="name" value="${name }"
						placeholder="科目名を入力してください" maxlength="20" required
						style="width: 100%; height: 40px; margin-bottom: 10px;
						border: 1px solid #ccc; border-radius: 5px;">
				</div>

				<div style="text-align;">
					<input type="submit" name="end" value="登録"
						style="background-color: #0d6efd; color: white; border: none; border-radius: 10px;
						padding: 6px 15px; margin-bottom: 20px;">
				</div>

				<div style="text-align;">
					<a href="SubjectList.action">戻る</a>
				</div>
			</form>

		</section>
	</c:param>

</c:import>