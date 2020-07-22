package com.java.fileBoard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.java.command.Command;
import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;

public class DownLoadCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		logger.info(logMsg+boardNumber);
		
		BoardDto boardDto = BoardDao.getInstance().readSelect(boardNumber);
		logger.info(logMsg+boardDto);
		
		int index = boardDto.getFileName().indexOf("_")+1;
		String fName = boardDto.getFileName().substring(index);
		String fileName = new String(fName.getBytes("utf-8"),"ISO-8859-1");
		
		
		long fileSize = boardDto.getFileSize();
		String path = boardDto.getPath();
		
		//텍스트가 아닌 바이너리기 때문에 헤더부분을 바이너리를 받을 수 있도록 바꿔야 한다. 
		//content-Disposition 다운받는 창이 나오도록 해준다. 
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		response.setContentType("application/octet-stream");
		response.setContentLengthLong(fileSize);
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(path),1024);
			bos = new BufferedOutputStream(response.getOutputStream(),1024);
			
			while(true) {
				int data = bis.read();
				if(data==-1) break;
				bos.write(data);
			}
			bos.flush();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(bis!=null)bis.close();
			if(bos!=null)bos.close();
			
		}
		
		return null;
	}

}
