<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../includes/header.jsp" %>
<section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-9 col-sm-12 join-form">
                    <h2>로그인<small>(가운데정렬)</small></h2>
                    
                    
                    <form action="<c:url value='/user/UserLogin' />" method="post" id="loginForm">
                        <div class="form-group">
                            <label for="id">아이디</label>
                            <input type="text" class="form-control" id="id" name="userId" placeholder="아이디">
                        </div>
                        <div class="form-group">
                            <label for="password">비밀번호</label>
                            <input type="password" class="form-control" id="password" name="userPw" placeholder="비밀번호 ">
                        </div>
                        
                        <div class="form-group">
                            <button type="button" class="btn btn-lg btn-success btn-block">회원가입</button>
                        </div>

                        <div class="form-group">
                            <button type="button" class="btn btn-lg btn-info btn-block" id="loginBtn">로그인</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </section>
    <%@ include file="../includes/footer.jsp" %>
    
    <script>
    	const msg = '${msg}';
    	if(msg === 'joinSuccess') {
    		alert('회원 가입 정상 처리되었습니다.');
    	} else if(msg === 'loginFail') {
    		alert('로그인 실패! 아이디와 비밀번호를 확인하세요.');
    	}
    	
    	//입력란이 공백인 지를 확인한 후, 공백이 아니라면 submit() 진행.
    	//mapper에 작성한 login 메서드의 리턴 타입은 UserVO
    	//리턴 타입에 맞게 sql문을 작성 해 주시면 되겠습니다.
    	$(function() {
			$('#loginBtn').click(function() {
				if($('#id').val() === '') {
					alert('아이디를 적어야 로그인을 하죠~');
					return;
				} else if($('#password').val() === '') {
					alert('비밀번호를 작성하세요!');
					return;
				} else {
					$('#loginForm').submit();
				}
			});
		});
    	
    </script>
</body>
</html>