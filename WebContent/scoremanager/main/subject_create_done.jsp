<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts">
	</c:param>
	<c:param name="content">

		<div class="row border mx-3 mb-3 py-2 align-items-center rounded"
			id="fillter">
			<h2 class="h2 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4"
				style="text-align: center;">科目情報登録</h2>
			<p>登録が完了しました</p>
			<a href="SubjectCreate.action">戻る</a>
			<a href="SubjectList.action">科目一覧</a>
		</div>
	</c:param>
</c:import>