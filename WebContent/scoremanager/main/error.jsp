<%-- エラーページJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
    	得点管理システム - エラー
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <p class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            	エラーが発生しました
            </p>
            <p style="text-align: center;">
        </section>
    </c:param>

</c:import>
