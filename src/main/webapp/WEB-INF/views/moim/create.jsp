<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>M.O.I.M</title>
<link rel="stylesheet" href="/resource/css/style.css?${millis }" />
</head>
<body>
	<div class="root">
		<h2 style="margin: 15px 0px;">모임 만들기</h2>
		<p style="font-weight: bolder; margin: 2px 0px;">
			모임에 필요한 정보들을 설정하여 개설해보세요. <span style="color: orangered;">새로운
				경험</span>이 찾아옵니다.
		</p>
		<form action="/moim/create-task" method="post" class="moim-form"
			autocomplete="off">
			<div class="block">
				<label>만들고자 하는 모임의 <span style="color: orangered;">이름</span>을
					설정하세요.
				</label>
				<div class="block">
					<input type="text" name="event" style="flex: 1" />
				</div>
			</div>
			<div class="block">
				<label>만들고자 하는 모임의 <span style="color: orangered;">공개
						여부, 목적, 최대 참가 인원 수</span>를 설정하세요.
				</label>
				<div class="block-row" style="justify-content: center">
					<div class="block-row" style="flex: 1">
						<select name="type" style="flex: 1; text-align: center">
							<c:forTokens items="공개,비공개" delims="," var="one">
								<option value="${one eq '공개' ? 'public':'private' }">${one }</option>
							</c:forTokens>
						</select>
					</div>
					<div class="block-row" style="flex: 1">
						<select name="cate" style="flex: 1; text-align: center">
							<c:forTokens items="스포츠,문화,학습,봉사,사교,게임,기타" delims="," var="one">
								<option value="${one }">${one }</option>
							</c:forTokens>
						</select>
					</div>
					<div class="block-row" style="flex: 1">
						<select name="maxPerson" style="flex: 1; text-align: center">
							<c:forEach var="cnt" begin="3" end="10">
								<option value="${cnt }">${cnt }명</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="block">
				<label>모임을 하고자하는 <span style="color: orangered;">날짜</span>와
					<span style="color: orangered;">언제부터 언제까지</span>진행되는지 선택하세요. 
					<br/>
					<small>(종료시간을 알수 없다면 설정을 비워두세요.)</small></label>
				<div class="block-row" style="flex: 1">
					<div class="block" style="flex: 2">
						<fmt:formatDate value="${today }" pattern="yyyy-MM-dd" var="defaultDate"/>
						<input type="date" style="text-align: center" name="date" value="${defaultDate }"/>
				
					</div>
					<div class="block" style="flex: 1">
						<input type="time" step="3600" style="text-align: center" name="begin"/>
					</div>
					<div class="block" style="flex: 1">
						<input type="time" step="3600" style="text-align: center" name="end"/>
					</div>
				</div>
			</div>
			<div class="block">
				<label>모임의 <span style="color: orangered;">목적과 특징, 참석대상 등</span>을 포함하는 소개글을 작성세요.</label>
				<div class="block" style="min-height: 120px">
					<textarea style="resize: none; flex: 1" name="description"></textarea>
				</div>
			</div>
			<div class="block">
				<button type="submit">모임 개설하기</button>
			</div>
		</form>
	</div>
</body>
</html>