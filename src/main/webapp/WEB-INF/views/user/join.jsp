<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>M.O.I.M</title>
<link rel="stylesheet" href="/resource/css/style.css?${millis }" />
</head>
<body>
	<div class="root">
		<c:url value="/user/join-task" var="joinTo" />
		<form action="${joinTo }" method="post" class="sign-form">
			<h2>가입을 시작합니다</h2>
			<p>회원가입하시면 다양한 서비스를 이용하실 수 있습니다.</p>
			<div class="block">
				<label>사용할 아이디</label><input
					type="text" placeholder="영문+숫자 조합만 가능" name="id" />
			</div>
			<div class="block">
				<label>사용할 비밀번호</label><input
					type="password" placeholder="4글자 이상만 가능" name="pass" />
			</div>
			<div class="block">
				<label>사용할 대표 이름</label><input type="text" placeholder="2글자 이상만 가능"
					name="name" />
			</div>
			<div class="block">
				<label>사용할 아바타</label>
				<div style="display: flex; gap: 20px">
					<c:forEach items="${avatars }" var="one" varStatus="status">
						<div style="display: flex; flex-direction: column;">
							<label for="${status.count }"> <img src="${one.url }"
								style="width: 96px" />
							</label> <input type="radio" value="${one.id }" name="avatar"
								id="${status.count }" ${status.first ? 'checked' : '' } />
						</div>
					</c:forEach>
				</div>
			</div>


			<c:if test="${param.cause eq 'valid' }">
				<h3 style="color:red">유효하지 않은 정보가 전달되었습니다. 형식을 맞춰주세요.</h3>
			</c:if>
			<h3>* 모든 정보는 필수 기입 정보 입니다.</h3>
			<div>
				<button type="submit" class="join-btn">다음</button>
			</div>

		</form>
	</div>
</body>
</html>