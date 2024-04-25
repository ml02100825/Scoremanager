<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>
            <div class="my-2 text-end px-4">
                <a href="StudentCreate.action">新規登録</a>
            </div>
            <form method="get" class="row g-3 align-items-center mx-3" id="subject-filter" action="TestListSubjectExecute.action">
                <div class="col-auto">
                    <label class="form-label">入学年度</label>
                    <select class="form-select" name="f1">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-auto">
                    <label class="form-label">クラス</label>
                    <select class="form-select" name="f2">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-auto">
                    <label class="form-label">科目</label>
                    <select class="form-select" name="f3">
                        <option value="0">--------</option>
                        <c:forEach var="subject" items="${subject_set}">
                            <option value="${subject.cd}" <c:if test="${subject.name==f3}">selected</c:if>>${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-secondary">検索</button>
                </div>
            </form>
            <form method="get" class="row g-3 align-items-center mx-3" id="student-id-filter" action="StudentSearch.action">
                <div class="col-auto">
                    <label class="form-label">学籍番号</label>
                    <input type="text" class="form-control" name="f5">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-secondary">検索</button>
                </div>
            </form>
            <c:choose>
                <c:when test="${tests.size()>0}">
                    <div>検索結果：${tests.size()}件</div>
                    <table class="table table-hover">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                        <c:forEach var="test" items="${tests}">
                            <tr>
                                <td>${test.entYear}</td>
                                <td>${test.classNum}</td>
                                <td>${test.student.no}</td>
                                <td>${test.student.name}</td>
                                <td>${test.point}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div>学生情報が存在しませんでした</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
