<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${root}/javascript/board/board.js"></script>
</head>
<body>
	${boardDto.boardNumber}
	<div id="board">
		${pageNumber}
		<form action="${root}/fileBoard/updateOk.do" method="post" onsubmit="return updateCheck(this)" enctype="multipart/form-data">
			<input type="hidden" value="${pageNumber}" name="pageNumber" /> 
			<input type="hidden" value="${boardDto.boardNumber}" name="boardNumber" />

<div class="createform">
				<div class="tuple">
					<span class="one"> <label class="index">작성자</label>
						${boardDto.writer}
					</span>
				</div>
				<div class="tuple">
					<span class="two"> <label class="index">제목</label> <input
						type="text" value="${boardDto.subject}" name="subject" />
					</span>
				</div>



				<div class="three">
					<label class="index">내용</label>
					<textarea class="content" name="content"> ${boardDto.content}</textarea>

				</div>
				
				<div class="three">
					<label class="index">email</label>
					<input type="text" value="${boardDto.email}" name="email" />

				</div>

				<div class="three">
					<label class="index">비밀번호</label> <input type="password"
						value="${boardDto.content}" name="password">
				</div>
				
				  <c:if test="${boardDto.fileSize!= 0}">
            	 <div class="tuple">
               <span class="two">
               <label class="index">파일명</label>
             	
             	<a href="${root}/fileBoard/downLoad.do?boardNumber=${boardDto.boardNumber}">
             	${boardDto.fileName}</a>
             
             	<input class="content" type="file" name="file" size=40 />
               </span>
               </div>
            
            </c:if>



				<div class="foot">
					<input type="submit" value="완료"/>
					<input type="reset" value="취소"/>
					<input type="button" value="글목록"/>

				</div>
			</div>


		</form>
	</div>


</body>
</html>