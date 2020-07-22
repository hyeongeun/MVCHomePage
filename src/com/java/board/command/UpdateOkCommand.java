package com.java.board.command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

		BoardDto boardDto =  BoardDao.getInstance().select(boardNumber);

		int check = 0;

		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setContent(request.getParameter("content"));
		boardDto.setEmail(request.getParameter("email"));
		boardDto.setPassowrd(request.getParameter("password"));

		check = BoardDao.getInstance().updateCheck(boardDto);

		request.setAttribute("check", check);
		request.setAttribute("pageNumber", pageNumber);





		//		
		//		BoardDto boardDto = new BoardDto();
		//		boardDto.setBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));
		//		boardDto.setGroupNumber(Integer.parseInt(request.getParameter("groupNumber")));
		//		boardDto.setSequenceNumber(Integer.parseInt(request.getParameter("sequenceNumber")));
		//		boardDto.setSequenceLevel(Integer.parseInt(request.getParameter("sequenceLevel")));
		//		
		//		boardDto.setWriter(request.getParameter("writer"));
		//		boardDto.setSubject(request.getParameter("subject"));
		//		boardDto.setContent(request.getParameter("content"));
		//		boardDto.setEmail(request.getParameter("email"));
		//		boardDto.setPassowrd(request.getParameter("password"));
		//		boardDto.setReadCount(Integer.parseInt(request.getParameter("readCount")));
		//		boardDto.setWriteDate(new Date());
		//		
		//		logger.info(logMsg+"boardDtotoString: "+boardDto);
		//		
		//		int check=BoardDao.getInstance().updateCheck(boardDto);
		//		logger.info(logMsg+check);
		//		
		//		request.setAttribute("check", check);
		return "/WEB-INF/views/boarder/updateOk.jsp";
		//		
		//		//return null;
	}

}
