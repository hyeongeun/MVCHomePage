package com.java.fileBoard.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;
import com.java.command.Command;

public class ListCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber == null) pageNumber ="1";
		
		int currentPage = Integer.parseInt(pageNumber);		//요청 1page
		logger.info(logMsg + "currentPage: "+currentPage);
		
		int boardSize = 10;	//[1] start:1 end:10 [2] start:11 end:20
		
		int startRow =(currentPage-1)*boardSize+1;		//한페이지당 게시물 시작번호
		int endRow = currentPage*boardSize;				//한페이지당 게시물 끝번호
		
		
		int count = BoardDao.getInstance().getCount();
		logger.info(logMsg+"listCount: "+count);
		
		
		ArrayList<BoardDto> boardList=null;
		
		if(count>0) {
			//startRow, endRow
			boardList= BoardDao.getInstance().getBoardList(startRow, endRow);
			logger.info(logMsg+boardList.size());
		}
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("boardSize", boardSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("count", count);
		
		return "/WEB-INF/views/fileBoard/list.jsp";
	}

}
