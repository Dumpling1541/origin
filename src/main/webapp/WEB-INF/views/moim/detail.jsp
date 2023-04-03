<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>M.O.I.M</title>
<link rel="stylesheet" href="/resource/css/style.css?${millis }" />
</head>
<body>
	<div class="root">
		<%@ include file="/WEB-INF/views/common/top.jsp"%>
		<div class="moim-detail-container ">
			<div class="moim-detail-content">
				<div style="text-align: left">
					<div>
						<small><b style="color: deeppink">${moim.managerName }</b>
							님이 개설한 모임</small>
					</div>
					<h2 style="margin: 0.2em 0em;">${moim.event }</h2>
					<div style="justify-content: space-between;" class="block-row">
						<div>
							<a href ="/moim/search"><span>전체</span></a><span>&gt;</span>
							<a href ="/moim/search?cate=${moim.cate }"><span>${moim.cate }</span></a> <span>|</span>
							<c:choose>
								<c:when test="${moim.type eq 'public' }">
									<span class="">공개</span>
								</c:when>
								<c:otherwise>
									<span class="">비공개</span>
								</c:otherwise>
							</c:choose>
							<span>|</span> <span>${moim.cate }</span> <span>|</span> <small>
								<fmt:formatDate value="${moim.beginDate }"
									pattern="yyyy.MM.dd (E)" /> <span>|</span> <fmt:formatDate
									value="${moim.beginDate }" pattern="HH:mm" /> ~ <fmt:formatDate
									value="${moim.endDate }" pattern="HH:mm" />
							</small>
						</div>
						<div>
							<c:if test="${moim.currentPerson eq moim.maxPerson }">
								<span style="color: red">마감된 모임</span>
							</c:if>
							${moim.currentPerson }/${moim.maxPerson }
						</div>
					</div>
					<div class="moim-detail-desc">${moim.description }</div>
				</div>
				<div class="block-row"
					style="justify-content: space-between; font-size: small; gap: 4px;">
					<div class="block-row">
						<div class="block-row" style="gap: 1px;">
							<img src="${moim.managerAvatarURL }"
								style="width: :24px; height: 24px;" /><span>${moim.managerName }</span>
						</div>
						<c:forEach var="one" items="${attendances }">
							<div class="block-row" style="gap: 1px;">
								<img src="${one.userAvatarURL }"
									style="width: :24px; height: 24px;" /><span>${one.userName }</span>
							</div>
						</c:forEach>
					</div>
					<div>
						<c:if test="${sessionScope.logonUser.id ne moim.managerId }">
							<c:choose>
								<c:when test="${moim.currentPerson ge moim.maxPerson }">
									<a class="moim-join-bt">참가신청불가</a>
								</c:when>
								<c:when test="${status eq -1 }">
									<a class="moim-join-bt" href="/user/login">참가
										신청을 하기위해서는 로그인이 필요합니다.</a>
								</c:when>
								<c:when test="${status eq 0 }">
									<a class="moim-join-bt"
										href="/moim/join-task?target=${moim.id }">참가 신청</a>
								</c:when>
								<c:when test="${status eq 1 }">
									<a class="moim-join-bt">승인 대기중</a>
								</c:when>
								<c:otherwise>
									<a class="moim-join-bt">신청 완료</a>
								</c:otherwise>
							</c:choose>
						</c:if>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>