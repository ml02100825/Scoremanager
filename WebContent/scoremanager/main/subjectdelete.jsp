<%-- 科目削除JSP --%>
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

            <c:set var="name" value="${requestScope.name}" />
            <c:set var="cd" value="${requestScope.cd}" />
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

            <form action="SubjectDeleteexecute.action" method="post">
                <input type="hidden" name="cd" value="${cd}" />
                <div class="text-align">
                    <p>「${name}(${cd})」を削除してもよろしいですか</p>
                    <button class="btn btn-secondary" id="delete-button" style="background-color: red; border: none;">削除</button>
                </div>

                <div class="mt-2 text-warning"><c:out value="${errors}" /></div>
                <div class="mt-2 text-warning">
                    <ul>
                        <c:forEach items="${error}" var="error">
                            <p>${error}</p>
                        </c:forEach>
                    </ul>
                </div>

                <div style="margin-top: 90px;">
                	<a href="SubjectList.action">戻る</a>
                </div>
            </form>

        </section>
    </c:param>

</c:import>