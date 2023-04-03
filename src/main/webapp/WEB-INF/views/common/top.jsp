<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="width: 100vw">
<c:choose>
	<c:when test="${sessionScope.logon }">
		<div
			class="block-row" style="justify-content: space-between;">
			<div
				style="padding: 10px 20px; display: flex; align-items: center; gap: 10px">
				<a href="/">홈</a> <a href="/friend/manage">친구관리</a> <a
					href="/moim/create">모임개설</a>
			</div>
			<div
				style="padding: 10px 20px; display: flex; align-items: center; gap: 10px">
				<a href="/private/mypage"> <img
					src="${sessionScope.logonUser.avatarURL }" style="width: 36px" />
				</a> <b>${logonUser.name }</b>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div style="display: flex; justify-content: flex-end; width: 100%;">
			<div style="padding: 10px 20px;">
				<a href="/user/login">로그인</a>
			</div>
		</div>
	</c:otherwise>
</c:choose>
</div>