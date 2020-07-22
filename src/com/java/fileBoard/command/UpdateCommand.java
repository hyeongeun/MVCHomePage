package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;
import com.java.command.Command;

public class UpdateCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		logger.info(logMsg+boardNumber+"\t"+pageNumber+"으아아ㅏ아아아아아아");
		
		BoardDto boardDto = BoardDao.getInstance().select(boardNumber);
		
		logger.info(logMsg+ boardDto);
		
		request.setAttribute("boardDto", boardDto);
		request.setAttribute("pageNumber", pageNumber);
		return "/WEB-INF/views/fileBoard/update.jsp";
	}

}
