package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class RegisterOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		MemberDto memberDto = new MemberDto();
		
		memberDto.setId(request.getParameter("id"));
		memberDto.setPassword(request.getParameter("password"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setJumin1(request.getParameter("jumin1"));
		memberDto.setJumin2(request.getParameter("jumin2"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setZipcode(request.getParameter("zipcode"));
		memberDto.setAddress( request.getParameter("address"));
		memberDto.setJob(request.getParameter("job"));
		memberDto.setMailing(request.getParameter("mailing"));
		memberDto.setInterest(request.getParameter("resultInterest"));
		//memberDto.setInterest(request.getParameter("interest"));
		
		//String id = request.getParameter("id")); 
		//System.out.println(id) 이런식으로 제대로 넘어오는 지 확인 해주고 나서 Dto연결하기 		
		
		
		memberDto.setMemberLevel("BB");		
		//시퀀스, 가입날짜 - DB
		
		logger.info(logMsg+memberDto.toString() );
		
		//DAO -- DB - DAO(DTO) - Command
		
		int check  = MemberDao.getInstance().insert(memberDto);
		logger.info(logMsg + check);
		
		request.setAttribute("check", check);
		
		
		
		return "/WEB-INF/views/member/registerOk.jsp" ;
	}

}
