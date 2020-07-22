<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<c:set var ="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../css/member/signUpStyle.css">
<script type="text/javascript" src ="${root}/javascript/member/register.js" ></script>
</head>
<body>
	<jsp:include page="../../../index.jsp"></jsp:include>
	<br><br>
	
<div id = "createform">
          <div class="subject">
            <a>회원수정</a>
         </div>    
      <form name="createForm" action="${root}/member/updateOk.do" method ="post" onsubmit="return createFormCheck(this)">
      	<input type="hidden" name="num" value="${memberDto.num}"/>
      
      
         <div class="eachDiv">
            <label>아이디</label>
            <span class="second">
               *<input type="text" name="id" value="${memberDto.id}" disabled="disabled">
            </span>
         </div>
         
         <div class="eachDiv">
            <label>비밀번호</label>
            <span class="second">
               *<input type="password" name="password" value="${memberDto.password}" >
            </span>
         </div>
         
         <div class="eachDiv">
            <label>비밀번호 확인</label>
            <span class="second">
               *<input type="password" name="passwordCheck" value="${memberDto.password}">
            </span>
         </div>
      
         <div class="eachDiv">
            <label>이름</label>
            <span class="second">
               *<input type="text" name="name" value="${memberDto.name}" disabled="disabled">
            </span>
         </div>
      
         <div class="eachDiv">
            <label>주민번호</label>
            <span class="second">
               *<input type="text" style="width: 100px;" name="jumin1" size="6" value="${memberDto.jumin1}" disabled="disabled">
               -<input type="text" style="width: 100px;" name="jumin2" size="7" value="${memberDto.jumin2}" disabled="disabled">
            </span>
         </div>
      
         <div class="eachDiv">
            <label>이메일</label>
            <span class="second">
               <input type="text" style="width: 300px;" name="email" value="${memberDto.email}">
            </span>
         </div>
      
         <div class="eachDiv">
            <label>우편번호</label>
            <span class="second">
               <input type="text" name="zipcode" value="${memberDto.zipcode}">
               <button type="button" onclick="zipcodeButton('${root}')">우편번호검색</button>
             </span>  
         </div>
      
         <div class="eachDiv">
            <label>주소</label>
            <span class="second">
               <input type="text" style="width: 450px;" name="address" value="${memberDto.address}">
            </span>
         </div>
      
         <div class="eachDiv">
            <label>직업</label>
            <span class="second">
               <select style="margin-left:2px; width: 150px;" name="job">
               <option value="none">직업을 선택하세요</option>
               
               	<option value ="apple" >사과</option>
            	  <option value ="banana" >바나나</option>
              	<option value = "pinapple" >파인애플</option>
               </select>
            </span>
            
            <script type="text/javascript">
            createForm.job.value="${memberDto.job}"
            </script>
         </div>
         
         <div class="eachDiv">
            <label>메일수신</label>
            <span class="second">
               <input type="radio" name="mailing" value="yes" />
               <span>yes</span>
               <input type="radio" name="mailing" value="no"/>
               <span>no</span>
               
            </span>
            <script type="text/javascript">
            	for(var i=0; i<createForm.mailing.length; i++){
            		if(createForm.mailing[i].value == '${memberDto.mailing}'){
            			createForm.mailing[i].checked = true;
            		}
            	}
            </script>
         </div>
         
         <div class="eachDiv">
            <label>관심심분야</label>
            <span class="second">
               <input type="checkbox" name="interest" value="경제">
               <span> 경제 </span>
               <input type="checkbox" name="interest" value="IT">
               <span> IT </span>
               <input type="checkbox" name="interest" value="음악">
               <span> 음악 </span>
               <input type="checkbox" name="interest" value="미술">
               <span> 미술 </span>
               <input type="hidden" name="resultInterest"/>
            </span>
             <c:forTokens var="interest" items="${memberDto.interest}" delims=",">
             	<script type="text/javascript">
             		for(var i=0; i<createForm.interest.length; i++){
             			if(createForm.interest[i].value == "${interest}"){
             				createForm.interest[i].checked = true;
             			}
             		}
             	</script>
             </c:forTokens>
              
         </div>
         
         <div class="eachDiv" style="border-bottom-width: 2px; text-align: center">
            <div>
               <input type="submit" value="수정">
               <input type="reset" value="취소">
            </div>
         </div>
      </form>
   </div>

</body>
</html>