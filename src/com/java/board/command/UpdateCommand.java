package com.java.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class UpdateCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		logger.info(logMsg+boardNumber+"\t"+pageNumber);
		
		BoardDto boardDto = BoardDao.getInstance().select(boardNumber);
		
		logger.info(logMsg+ boardDto);
		
		request.setAttribute("boardDto", boardDto);
		request.setAttribute("pageNumber", pageNumber);
		return "/WEB-INF/views/boarder/update.jsp";
	}

}
