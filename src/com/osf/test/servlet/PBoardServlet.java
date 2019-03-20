package com.osf.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String savePath = "D:\\study\\workspace\\osf-jsp\\WebContent\\upload";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		uri = uri.replace("/pboard/", "");
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
				for(int i=0; i<fileList.size(); i++) {
					FileItem fi = fileList.get(i);
					if(fi.isFormField()) {
						
					}else {
						String fileName = fi.getName();
						File saveFile = new File(savePath + "\\" + fileName);
						fi.write(saveFile);
					}
					//input type file은 폼필드가 다르다 저장방식이 다르다.
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

}
