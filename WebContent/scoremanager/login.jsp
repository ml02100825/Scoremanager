<%-- 共通テンプレート --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
	<c:param name="content">
		<form action="LoginExecute.action" method="post">
			ID<input type="text" name="id"  maxlength = 20  placeholder="半角でご入力ください">
			パスワード<input type="password"
				name="password"  id = password maxlength = 20   placeholder="20文字以内の半角英数字でご入力ください">
				<input type="checkbox" id="chk_d_ps" onclick="togglePassword()">
				<label for="chk_d_ps">パスワードを表示</label>
				<input type="submit" value="ログイン">
		</form>
		<div class="mt-2 text-warning">
			<ul>

				<c:forEach items="${errors}" var="error">
					<p>${error}</p>
				</c:forEach>
			</ul>
		</div>

	</c:param>

</c:import>


<script>
			function togglePassword(){
				var passwordInput = document.getElementById("password");
				if (passwordInput.type === "password") {
					passwordInput.type = "text";
				} else {
					passwordInput.type = "password";
				}
			}
		</script>