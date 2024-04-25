<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>テスト情報一覧</title>
</head>
<body>
    <h1>テスト情報一覧</h1>
    <table border="1">
        <thead>
            <tr>
                <th>テスト番号</th>
                <th>得点</th>
                <!-- 他のカラムのヘッダー -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="test" items="${tests}">
                <tr>
                    <td>${test.no}</td>
                    <td>${test.point}</td>
                    <!-- 他のカラムのデータ -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
