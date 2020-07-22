package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		//HttpSession session = request.getSession();		
		MemberDto memberDto = new MemberDto();
		memberDto.setNum(Integer.parseInt(request.getParameter("num")));
		//memberDto.setId(request.getParameter("id"));
		//memberDto.setId((String)session.getAttribute("id")); //session 이용해서 아이디 가져오기
		memberDto.setPassword(request.getParameter("password"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setZipcode(request.getParameter("zipcode"));
		memberDto.setAddress(request.getParameter("address"));
		memberDto.setJob(request.getParameter("job"));
		memberDto.setMailing(request.getParameter("mailing"));
		memberDto.setInterest(request.getParameter("resultInterest"));
		logger.info(logMsg+memberDto.toString());
		
		int check = MemberDao.getInstance().update(memberDto);
		logger.info(logMsg+"updateCheck: "+check);
		
		
		request.setAttribute("check", check);

		return "/WEB-INF/views/member/updateOk.jsp";
	}

}
