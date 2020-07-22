<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var ="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="${root}/css/boarder/write.css">
  <script type="text/javascript" src ="${root}/javascript/board/board.js" ></script>
 </head>
<body>

<div id="board">
${boardNumber},${groupNumber},${sequenceNumber},${sequenceLevel}
      <div class="list">
         <a href="${root}/fileBoard/list.do">글목록</a>
      </div>

      <div class="createform">

         <form action="${root}/fileBoard/writeOk.do" method="post" onsubmit="return boardCheck(this)" enctype="multipart/form-data">
			<input type="hidden" name="boardNumber" value ="${boardNumber}"/>
			<input type="hidden" name="groupNumber" value ="${groupNumber}"/>
			<input type="hidden" name="sequenceNumber" value ="${sequenceNumber}"/>
			<input type="hidden" name="sequenceLevel" value ="${sequenceLevel}"/>
            <div class="tuple">
               <span class="one">
                  <label class="index">작성자</label>
                  <input class="content" type="text" name="writer" />
               </span>
            </div>

            <div class="tuple">
               <span class="two">
                  <label class="index">제목</label>
                  <input class="content" type="text" name="subject" />
               </span>
            </div>

            <div class="tuple">
               <span class="two">
                  <label class="index">이메일</label>
                  <input class="content" type="text" name="email" />
               </span>
            </div>

            <div class="three">
               <label class="index">내용</label>
               <textarea class="content" name="content"></textarea>
            </div>

            <div class="tuple">
               <span class="one">
                  <label class="index">비밀번호</label>
                  <input class="content" type="password" name="password" />
               </span>
            </div>
            <!-- 파일 업로드 -->
            <div class="tuple">
               <span class="one">
                  <label class="index">파일명</label>
                  <input class="content" type="file" name="file" size=40 />
               </span>
            </div>

            <div class="foot">
               <input type="submit" value="글쓰기" />
               <input type="reset" value="다시작성" />
               <input type="button" value="목록보기" onclick="location.href='${root}/fileBoard/list.do'" />
            </div>

         </form>
      </div>
   </div>

</body>
</html>