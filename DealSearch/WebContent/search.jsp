<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">

<title>Happy House</title>

</head>
<%@ include file="/template/header.jsp"%>

<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#searchBtn").click(function() {
				$("#searchform").attr("action", "${root}/main").submit();
			});
		});
	</script>
	<div>
		<form id="searchform" action="" method="get">
			<input type="hidden" name="act" id="act" value="searchCategory">
			<div align="center">
				<select name="search">
					<option value="dong" selected>동</option>
					<c:if test="${search ne 'apt'}">
						<option value="apt">아파트</option>
					</c:if>
					<c:if test="${search eq 'apt'}">
						<option value="apt" selected>아파트</option>
					</c:if>
				</select> <input type="text" value="${searchfor }"
					style="margin-top: 50px; min-width: 500px"
					placeholder="검색할  동 이름 또는 아파트 명을 입력하세요" name="searchfor">
				<button id="searchBtn" style="margin-top: 10px; min-width: 100px"
					type="button">검색</button>
			</div>
		</form>
	</div>
	<div>
		<div>
			<h3>거래 정보</h3>
			<hr>
			<div style="height: 500px; overflow: scroll;">
				<div id="dealList" style="text-align: left;"></div>
				<table class="table table-active text-left">
					<c:if test="${!empty deals}">
						<thead>
							<tr>
								<td>동 이름</td>
								<td>아파트 이름</td>
								<td>가격</td>
								<td>면적</td>
								<td>거래월</td>
								<td>거래일</td>
							</tr>
						</thead>
						<c:forEach var="deal" items="${deals}">
							<tbody>
								<tr>
									<td>${deal.dongName }</td>
									<td>${deal.aptName }</td>
									<td>${deal.dealPrice }</td>
									<td>${deal.area }</td>
									<td>${deal.dealMonth }</td>
									<td>${deal.dealDay }</td>
								</tr>
							</tbody>
						</c:forEach>
					</c:if>
					<c:if test="${empty deals}">
						<tr class="table-info">
							<td colspan="2">거래가 없습니다.</td>
						</tr>
					</c:if>
				</table>
			</div>
		</div>
		<div id="map" style="width: 100%; height: 500px;"></div>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=79f64790b1ebb1f01157f3a1c8edb6fb"></script>
		<script>
			const mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				level : 3
			// 지도의 확대 레벨
			};

			// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
			const map = new kakao.maps.Map(mapContainer, mapOption);
		</script>
	</div>

</body>
<%@ include file="/template/footer.jsp"%>
</html>