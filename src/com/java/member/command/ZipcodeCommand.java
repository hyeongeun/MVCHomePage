package com.java.member.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.ZipcodeDto;

public class ZipcodeCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String checkDong = request.getParameter("dong");
		logger.info(logMsg+"checkDong: "+checkDong);
		
		if(checkDong !=null) {
			ArrayList<ZipcodeDto> zipcodeList = MemberDao.getInstance().zipcodeReader(checkDong);
			
			logger.info(logMsg+"zipcodeList.size: " + zipcodeList.size());
			
			request.setAttribute("zipcodeList", zipcodeList);
		}
		
				
		return "/WEB-INF/views/member/zipcode.jsp";
	}

}
