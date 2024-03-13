package vn.iotstar.Controller.Admin;

import java.io.File;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import vn.iotstar.Models.CategoryModel;
import vn.iotstar.Services.ICategoryService;
import vn.iotstar.Services.Impl.CategoryServiceImpl;
import vn.iotstar.util.Constant;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15, // 15 MB
        location            = "D:\\Uploads"
)
public class CategoryAddController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse
			resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/views/admin/add-category.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse
			resp) throws ServletException, IOException {
		CategoryModel category = new CategoryModel();
		ServletFileUpload servletFileUpload = new
				ServletFileUpload();
		servletFileUpload.setHeaderEncoding("UTF-8"); 
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			List<FileItem> items = servletFileUpload.parseRequest((RequestContext) req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("name")) {
					category.setCatename(item.getString("UTF-8"));
				} else if (item.getFieldName().equals("image")) {
					String originalFileName = item.getName();
					int index = originalFileName.lastIndexOf(".");
					String ext = originalFileName.substring(index + 1);
					String fileName = System.currentTimeMillis() + "." + ext;
					File file = new File(Constant.DIR + "/category/" + fileName);
					item.write(file);
					category.setImage("category/"+fileName);
				}
			}
			category.setStatus(1);
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}