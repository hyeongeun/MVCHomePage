package com.java.fileBoard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;
import com.java.command.Command;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		List<FileItem> list = upload.parseRequest(request);
		Iterator<FileItem> iter = list.iterator();
		
		BoardDto boardDto = new BoardDto();
		HashMap<String, String> dataMap = new HashMap<String, String>();
		
		while(iter.hasNext()) {
			FileItem fileItem = iter.next();
			
			if(fileItem.isFormField()) {
				String name = fileItem.getFieldName();
				String value = fileItem.getString("utf-8");
				logger.info(logMsg+name+"\t"+value);
				
				dataMap.put(name, value);
				
			}else {
				if(fileItem.getFieldName().equals("file")) {
					if(fileItem.getName()==null || fileItem.getName().equals("")) continue;
					
					upload.setFileSizeMax(102*1024*10);
					String fileName = System.currentTimeMillis()+"_"+fileItem.getName();
					
					String dir = "C:\\lhe\\mvc\\workspace\\MVCHomePage\\WebContent\\pds\\";
					File file = new File(dir,fileName);
					
					
					BufferedInputStream bis = null;
					
					BufferedOutputStream bos = null;
					
					try {
						bis = new BufferedInputStream(fileItem.getInputStream(),1024);
						bos = new BufferedOutputStream(new FileOutputStream(file),1024);
						while(true) {
							int data = bis.read();
							if(data==-1)break;
							
							bos.write(data);
						}
						bos.flush();
					}catch(IOException e) {
						e.printStackTrace();
					}finally {
						if(bis!=null)bis.close();
						if(bos!=null)bos.close();
						
					}
					
					boardDto.setFileName(fileName);
					boardDto.setFileSize(fileItem.getSize());
					boardDto.setPath(file.getAbsolutePath());
				}
			}
		}
		
		boardDto.setDataMap(dataMap);
		logger.info(logMsg+boardDto);
		
			
//		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
//		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
//
//		BoardDto boardDto =  BoardDao.getInstance().select(boardNumber);
//
//		int check = 0;
//
//		boardDto.setSubject(request.getParameter("subject"));
//		boardDto.setContent(request.getParameter("content"));
//		boardDto.setEmail(request.getParameter("email"));
//		boardDto.setPassowrd(request.getParameter("password"));
//		
//
//		check = BoardDao.getInstance().updateCheck(boardDto);
//
//		request.setAttribute("check", check);
//		request.setAttribute("pageNumber", pageNumber);
//	      
//	      
//		return "/WEB-INF/views/fileBoard/updateOk.jsp";
		return null;
	     
	      
		
	}

}
