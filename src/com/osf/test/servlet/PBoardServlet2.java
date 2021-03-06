package com.osf.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.osf.test.service.PBoardService2;
import com.osf.test.service.impl.PBoardServiceImpl2;
import com.osf.test.vo.PhotoBoardVO;

public class PBoardServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String savePath = "D:\\study\\workspace\\osf-jsp\\WebContent\\upload";
    
    private PBoardService2 pbs = new PBoardServiceImpl2();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard2/", "");
		if("list".equals(uri)) { 
			request.setAttribute("pBoardList", pbs.selectPBoardList());
			RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board2/list.jsp");
			rd.forward(request, response);
		}else {
			try {
			int pbNum = Integer.parseInt(uri);
			request.setAttribute("pBoard", pbs.selectPBoard(pbNum));
			RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board2/view.jsp");
			rd.forward(request, response);
			}catch(NumberFormatException e) {
				throw new ServletException("상세조회는 번호조회만 가능합니다.");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		uri = uri.replace("/pboard2/", "");
		if("insert".equals(uri)) {
			DiskFileItemFactory dfiFactory = new DiskFileItemFactory();
			dfiFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			dfiFactory.setSizeThreshold(10*1024*1024); //임시 용량
			
			ServletFileUpload sfu = new ServletFileUpload(dfiFactory);
			sfu.setHeaderEncoding("utf-8");
			sfu.setSizeMax(20*1024*1024);     // 전체 용량
			sfu.setFileSizeMax(20*1024*1024); //파일 하나당
			try {
				List<FileItem> fileList = sfu.parseRequest(request);
				Map<String,String> pBoard = new HashMap<>();
				for(int i=0; i<fileList.size(); i++) {
					FileItem fi = fileList.get(i);
					if(fi.isFormField()) {
						pBoard.put(fi.getFieldName(), fi.getString("utf-8"));
					}else {
						String rFileName = fi.getName();
						String extName = rFileName.substring(rFileName.lastIndexOf(".")+1);
						String fileName = System.currentTimeMillis()+"";
						File saveFile = new File(savePath + "\\" + fileName+"."+extName);
						pBoard.put("pb_real_path", rFileName);
						pBoard.put("pb_file_path", "/upload/"+fileName+"."+extName);
						fi.write(saveFile);
					}
					//input type file은 폼필드가 다르다 저장방식이 다르다.
				}
				PhotoBoardVO pbvo = new PhotoBoardVO();
				pbvo.setPbTitle(pBoard.get("pb_title"));
				pbvo.setPbContent(pBoard.get("pb_content"));
				pbvo.setPbFilePath(pBoard.get("pb_file_path"));
				pbvo.setPbRealPath(pBoard.get("pb_real_path"));
				if(pbs.insertPBoard(pbvo)==1) {
					request.setAttribute("msg", "집중해라!");
					request.setAttribute("url", "/views/photo-board2/insert.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/views/result.jsp");
					rd.forward(request, response);
					return;
				}else {
					
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if("update".equals(uri)) {
			
		}else if("delete".equals(uri)) {
			
		}else {
			
		}
	}
	public static void main(String[] args) {
	
	}
}
