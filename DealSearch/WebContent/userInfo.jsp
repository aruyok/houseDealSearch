<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
</head>
<%@ include file="/template/header.jsp"%>
<body>
	<script type="text/javascript">
        $(document).ready(function () {
            $("#updateBtn").click(function () {
                if (!$("#password").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if (!$("#name").val()) {
                    alert("이름 입력!!!");
                    return;
                } else if (!$("#email").val()) {
                    alert("비밀번호 입력!!!");
                    return;
                } else if (!$("#tel").val()) {
                    alert("전화번호 입력!!!");
                    return;
                } else {
                    $("#updateform").attr("action", "${root}/main").submit();
                }
            });
            $("#deleteBtn").click(function () {
                location.href = "${root}/main?act=delete";
            });
        });
    </script>
	<section>
		<div align="center" style="margin-top:50px">
			<form id="updateform" method="post" action="">
                <input type="hidden" name="act" id="act" value="update">
				<div>
					<div>
						<h2>회원 정보 확인</h2>
					</div>
				</div>
				<div>
					<label>아이디</label>
					<div>
						<input type="text" value ="${id }" id="id" name="id" readonly>					
					</div>
				</div>
				<div>
					<label>비밀번호</label>
					<div>
						<input type="password" value ="${password }" id="password" name="password" required="required">					
					</div>
				</div>
				<div>
					<label>이름</label>
					<div>
						<input type="text" value ="${name }" id="name" name="name" required="required">					
					</div>
				</div>
				<div>
					<label>주소</label>
					<div>
						<input type="text" value ="${email }" id="email" name="email" required="required">
					</div>
				</div>
				<div>
					<label>전화번호</label>
					<div>
						<input type="tel"value ="${tel }" id="tel" name="tel" required="required">
					</div>
				</div>
				
				<div>
					<div style="margin-top:10px">
						<button type="button" id="updateBtn">수정
							&nbsp; &#10003;</button>
						<button type="button" id="deleteBtn">탈퇴 
							&nbsp; &#10003;</button>
					</div>
				</div>
			</form>
			
		</div>
	</section>

	<%@ include file="/template/footer.jsp"%>
</body>

</html>

