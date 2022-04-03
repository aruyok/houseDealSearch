<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp"%>
<div align="left" style="margin: 15px 25px 15px 10px">
	<h3>Happy House</h3>
	<br>

	<div align="right" style="margin: 15px 25px 15px 0px">
		<c:if test="${empty userInfo}">
			<a href="${root}/main?act=gotojoin">회원가입</a>
			<a href="${root}/main?act=gotologin">로그인</a>
		</c:if>
	</div>
	<div align="right" style="margin: 15px 25px 15px 0px">
		<c:if test="${!empty userInfo}">
			<strong>${userInfo.name}(${userInfo.id})</strong>님 안녕하세요
				<a href="${root}/main?act=logout">로그아웃</a>
			<a href="${root}/main?act=userinfo">회원정보</a>
		</c:if>
	</div>
</div>

<body>
	<script type="text/javascript">
        $(document).ready(function () {
            $("#searchBtn").click(function () {
            	$("#searchform").attr("action", "${root}/main").submit();
            });
        });
    </script>
	<section>
		<div align="center">
			<c:if test="${empty userInfo}">
				<h1>검색하려면 로그인하세요.</h1>
			</c:if>
			<c:if test="${!empty userInfo}">
				<h1>거래 정보 검색</h1>
				<div>
					<form id="searchform" action="" method="get">
						<input type="hidden" name="act" id="act" value="searchCategory">
						<select id="city">
							<option value="all">도/광역시</option>
							<option value="서울특별시">서울시</option>
						</select> 
						<select id="gu">
							<option value="all">시/구/군</option>
							<option value="종로구">종로구</option>
						</select> 
						<select id="dong" name="searchfor">
							<option value="">동</option>
							<option value="내수동">내수동</option>
							<option value="당주동">당주동</option>
							<option value="명륜1가">명륜1가</option>
							<option value="명륜2가">명륜2가</option>
							<option value="사직동">사직동</option>
						</select>
						<button type="button" id="searchBtn" class="btn btn-outline-primary">검색</button>
					</form>
				</div>
			</c:if>
		</div>
	</section>
</body>
<%@ include file="/template/footer.jsp"%>