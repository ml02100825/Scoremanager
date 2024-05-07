<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts">
        <script>
            function togglePassword(){
                var passwordInput = document.getElementById("password");
                if (passwordInput.type === "password") {
                    passwordInput.type = "text";
                } else {
                    passwordInput.type = "password";
                }
            }
            function checkInput(event) {
                const input = event.target.value;
                const regex = /[^\x00-\x7F]/; // ASCII文字以外の文字を含むかどうかをチェックする正規表現

                if (regex.test(input)) {
                    event.target.value = input.replace(/[^\x00-\x7F]/g, ''); // ASCII文字以外の文字を削除
                    alert("日本語の入力は許可されていません。英字または数字を入力してください。");
                }
            }
        </script>
    </c:param>

    <c:param name="content">
        <div class="row border mx-3 mb-3 align-items-center" id="filter">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4 text-center">ログイン</h2>
            <form action="LoginExecute.action" method="post">
                <div style="text-align: center;">

                    <div class="mt-2 text-warning">
                        <c:forEach items="${errors}" var="error">
                            <ul style="color: black;">${error} </ul>
                        </c:forEach>
                    </div>

                    <input type="text" name="id" id="noJapaneseInput"  oninput="checkInput(event)"  pattern="^[a-zA-Z0-9]+$"  value="${not empty param.id ? param.id : ''}" placeholder="半角でご入力ください"
                        maxlength="20" required style="width: 100%; height: 50px; margin-bottom: 10px;"><br>
                    <input type="password" name="password" id="password" oninput="checkInput(event)"   pattern="^[a-zA-Z0-9]+$" value="${not empty param.password ? param.password : ''}" placeholder="20文字以内の半角英数字でご入力ください"
                        maxlength="20" required style="width: 100%; height: 50px; margin-bottom: 10px;"><br>
                    <input type="checkbox" id="chk_d_ps" onclick="togglePassword()">
                    <label for="chk_d_ps" style="margin-bottom: 10px;">パスワードを表示</label><br>
                    <input type="submit" value="ログイン"
                        style="background-color: #0d6efd; color: white; border: none; border-radius: 10px;
                        padding: 8px 50px; margin-bottom: 20px;">
                </div>
            </form>
        </div>
    </c:param>
</c:import>