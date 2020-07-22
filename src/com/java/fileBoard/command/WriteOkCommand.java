package com.java.fileBoard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.command.Command;
import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;

public class WriteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		DiskFileItemFactory factory = new DiskFileItemFactory();				//파일보관 객체
		ServletFileUpload upload = new ServletFileUpload(factory);				//요청처리 객체 

		List<FileItem>list = upload.parseRequest(request);
		Iterator<FileItem> iter = list.iterator();

		BoardDto boardDto = new BoardDto();
		HashMap<String, String> dataMap = new HashMap<String, String>();

		while(iter.hasNext()) {			//11개
			FileItem fileItem = iter.next();

			if(fileItem.isFormField()) {		//텍스트 확인

				//코드중복이 되는 것을 없애 주고자 HashMap 이용
				//				String name = fileItem.getFieldName();
				//				logger.info(logMsg +"text: "+name);
				//				if(fileItem.getFieldName().equals("boardNumber")) {
				////					String boardNumber = fileItem.getString();
				////					int num = Integer.parseInt(boardNumber);
				////					boardDto.setBoardNumber(num);
				//					boardDto.setBoardNumber(Integer.parseInt(fileItem.getString()));
				//				}
				//				
				//				if(fileItem.getFieldName().equals("groupNumber")) {
				//					boardDto.setGroupNumber(Integer.parseInt(fileItem.getString()));
				//				}
				//				
				//				if(fileItem.getFieldName().equals("sequenceNumber")) {
				//					boardDto.setSequenceNumber(Integer.parseInt(fileItem.getString()));
				//				}
				//				
				//				if(fileItem.getFieldName().equals("sequenceLevel")) {
				//					boardDto.setSequenceLevel(Integer.parseInt(fileItem.getString()));
				//				}
				//				
				//				if(fileItem.getFieldName().equals("writer")) {
				//					boardDto.setWriter(fileItem.getString("utf-8"));
				//				}
				//				if(fileItem.getFieldName().equals("subject")) {
				//					boardDto.setSubject(fileItem.getString("utf-8"));
				//				}
				//			
				String name = fileItem.getFieldName();
				String value = fileItem.getString("utf-8");
				logger.info(logMsg+name+"\t"+value);

				dataMap.put(name, value);




			}else {
				//String name = fileItem.getFieldName();
				//logger.info(logMsg+"binary: "+name+","+fileItem.getName()+","+fileItem.getSize());

				if(fileItem.getFieldName().equals("file")) {
					//파일명 fileItem.getName() / 파일사이즈 fileItem.getSize(); 	getInputStream()-> 바이너리이기때문에 1바이트씩 읽어와야한다. (자동으로 경로를 읽어온다. )

					if(fileItem.getName()==null || fileItem.getName().equals("")) continue; //계속 이어나가야한다. //이름이 넘어온게 없고 파일이 비어있다면 

					//					InputStream is= fileItem.getInputStream();
					//					BufferedInputStream bis = new BufferedInputStream(is, 1024);

					upload.setFileSizeMax(1024*1024*10);//byte*kb*mb*gb //10mb	//사이즈제한
					String fileName = System.currentTimeMillis()+"_"+fileItem.getName();

					//절대경로
					String dir="C:\\lhe\\mvc\\workspace\\MVCHomePage\\WebContent\\pds\\";
					File file = new File(dir,fileName);

					//톰켓 실제 서버 경로 (이걸로는 프로젝트에서 쓰지 마세요)
					//String dir=request.getServletContext().getRealPath("\\pds\\");

					//원래는 웹서버 경로를 적어야 한다. ftp가 따로 있어야 한다. 
					//없기 때문에 그냥 c:아래 넣어주기
//					File dir = new File("C:\\pds\\");
//					logger.info(logMsg+dir);
//
//					dir.mkdir();
//					File file =null;
//					if(dir.exists() && dir.isDirectory()) {
//						file= new File(dir,fileName);
//					}

					BufferedInputStream bis =null;
					//					FileOutputStream fos = new FileOutputStream(file);
					//					BufferedOutputStream bos = new BufferedOutputStream(fos,1024);

					BufferedOutputStream bos = null;

					try {
						bis = new BufferedInputStream(fileItem.getInputStream(), 1024);
						bos = new BufferedOutputStream(new FileOutputStream(file),1024);
						while(true) {
							int data = bis.read();
							if(data ==-1) break;

							bos.write(data);
						}
						bos.flush();
					}catch(IOException e) {
						e.printStackTrace();
					}finally {
						if(bis!=null) bis.close();
						if(bos!=null) bos.close();
					}

					boardDto.setFileName(fileName);
					boardDto.setFileSize(fileItem.getSize());
					boardDto.setPath(file.getAbsolutePath());

				}

			}
		}

		boardDto.setDataMap(dataMap);
		boardDto.setWriteDate(new Date());
		logger.info(logMsg+boardDto.toString());

		int check = BoardDao.getInstance().insert(boardDto);
		logger.info(logMsg+check);

		request.setAttribute("check", check);

		return "/WEB-INF/views/fileBoard/writeOk.jsp";
	}

}
