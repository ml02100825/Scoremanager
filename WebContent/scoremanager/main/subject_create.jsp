<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
    	<h2 class="h2 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4" style="text-align: center;">科目情報登録</h2>
        <form action="SubjectCreateExecute.action" method="post">
        	<div class="col-4" style="width: 100%; margin-bottom: 10px;">
            	<label>科目コード</label>
                <input type="text" name="cd" value="${not empty cd ? cd : ''}" placeholder="科目コードを入力してください" maxlength="3" required
                	style="width: 100%; height: 40px; margin-bottom: 10px;
					border: 1px solid #ccc; border-radius: 5px;">
            </div>
            <div style="text-align">
            	<c:if test="${not empty errors1}">
            		<c:forEach var="error" items="${errors1}">
                    	<span style="color:#ffd9a3;">${error}</span>
                    </c:forEach>
                    <div style="margin-bottom:10px;"></div>
                </c:if>
            </div>
            <div class="col-4" style="width: 100%; margin-bottom: 10px;">
            	<label>科目名</label>
                <input type="text" name="name" value="${not empty name ? name : ''}" placeholder="科目名を入力してください" maxlength="20" required
                	style="width: 100%; height: 40px; margin-bottom: 10px;
					border: 1px solid #ccc; border-radius: 5px;">
            </div>
            <div style="text-align">
            	<c:if test="${not empty errors2}">
                    <c:forEach var="error" items="${errors2}">
                        <span style="color:#ffd9a3;">${error}</span>
                    </c:forEach>
                    <div style="margin-bottom:10px;"></div>
                </c:if>
            </div>
            <div class="col-4" style="width: 100%; margin-bottom: 10px;">
                <input type="submit" name="end" value="登録"
                	style="background-color: #0d6efd; color: white; border: none; border-radius: 10px;
                    padding: 6px 15px; margin-bottom: 20px;">
            </div>
        </form>
        <div>
            <a href="SubjectList.action">戻る</a>
        </div>
    </c:param>
</c:import>
