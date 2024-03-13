package vn.iotstar.Controller.Admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.Entity.Category;
import vn.iotstar.Service.ICategoryService;
import vn.iotstar.Service.Impl.CategoryServiceImpl;
import vn.iotstar.Util.Constant;
import vn.iotstar.Util.UploadUtils;

@SuppressWarnings("serial")
@MultipartConfig
@WebServlet(urlPatterns = { "/admin-category", "/admin-category/create", "/admin-category/update",
		"/admin-category/edit", "/admin-category/delete", "/admin-category/reset" })
public class CategoryController extends HttpServlet {

	ICategoryService categoryService = new CategoryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// kiểm tra url rồi chuyển đến hàm tương ứng
		// lấy url
		String url = request.getRequestURL().toString();
	
		Category category = null;
		if (url.contains("create")) {
			request.getRequestDispatcher("/views/admin/category/add.jsp").forward(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
			category = new Category();
			request.setAttribute("category", category);
		} else if (url.contains("edit")) {
			edit(request, response);
		} else if (url.contains("reset")) {
			category = new Category();
			request.setAttribute("category", category);
		}
		
		int count= 	categoryService.count();
		int enpage=count/3;
		if(count %3 !=0)
			enpage++;
		// gọi hàm findAll để lấy thông tin từ entity
		findAll(request, response);
		
		request.setAttribute("tag", "cate");
		
		request.setAttribute("enpage",enpage);
		
		findAllPage(request,response);
		request.getRequestDispatcher("/views/admin/category/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// lấy url
		String url = request.getRequestURL().toString();
		// kiểm tra url rồi chuyển đến hàm tương ứng
		if (url.contains("create")) {
			insert(request, response);
		} else if (url.contains("update")) {
			update(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
		} else if (url.contains("reset")) {
			request.setAttribute("category", new Category());
		}

		// gọi hàm findAll để lấy thông tin từ entity
		findAll(request, response);
		findAllPage(request,response);
		request.getRequestDispatcher("/views/admin/category/list.jsp").forward(request, response);
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			// khỏi tạo đối tượng Model
			Category category = new Category();
			// sử dụng BeanUtils để tự lấy các name Field trên form
			// tên field phải trùng với entity
			BeanUtils.populate(category, request.getParameterMap());
			// xử lý hình ảnh
			String fileName = category.getCategoryCode() + System.currentTimeMillis();
			category.setImages(UploadUtils.processUpload("images", request, Constant.DIR + "\\category\\", fileName));
			// gọi hàm insert để thêm dữ liệu
			categoryService.insert(category);
			// thông báo
			request.setAttribute("message", "Đã thêm thành công");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// khởi tạo DAO
			// khai báo danh sách và gọi hàm findAll() trong dao
			List<Category> list = categoryService.findAll();
			// thông báo
			request.setAttribute("categorys", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}
	
	protected void findAllPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// khởi tạo DAO
			// khai báo danh sách và gọi hàm findAll() trong dao
			String a =request.getParameter("index");
	
			if( a== null)
			{
				a="1";
			}
		
			int page=Integer.parseInt(a);
			// 3 là số lượng mẫu tin trên 1 trang
			
			page=(page-1)*3;
		//page là vị trí bắt đầu
			List<Category> list = categoryService.findAll(page,3);
		
			// thông báo
			request.setAttribute("categorys1", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: lỗi tại đây " + e.getMessage());
		}
	}


	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// khai báo biến userId
			String categoryId = request.getParameter("categoryId");
			// khởi tạo DAO
			// gọi hàm insert để thêm dữ liệu
			Category category = categoryService.findById(Integer.parseInt(categoryId));
			// thông báo
			request.setAttribute("category", category);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// lấy dữ liệu trong jsp
			String categoryId = request.getParameter("categoryId");
			// khởi tạo DAO
			// khai báo danh sách và gọi hàm findAll() trong dao
			categoryService.delete(Integer.parseInt(categoryId));
			// thông báo
			request.setAttribute("message", "Đã xóa thành công");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			// lấy dữ liệu từ jsp bằng BeanUtils
			Category category = new Category();
			BeanUtils.populate(category, request.getParameterMap());
			// khởi tạo DAO
			Category oldcate = categoryService.findById(category.getCategoryId());
			// xử lý hình ảnh
			if (request.getPart("images").getSize() == 0) {
				category.setImages(oldcate.getImages());
			} else {
				if (oldcate.getImages() != null) {
					// XOA ANH CU DI
					String fileName = oldcate.getImages();
					File file = new File(Constant.DIR + "\\category\\" + fileName);
					if (file.delete()) {
						System.out.println("Đã xóa thành công");
					} else {
						System.out.println(Constant.DIR + "\\category\\" + fileName);
					}
				}
				String fileName = category.getCategoryCode() + System.currentTimeMillis();
				category.setImages(
						UploadUtils.processUpload("images", request, Constant.DIR + "\\category\\", fileName));
				// category.setImages(UploadUtils.processUploadFolderWeb("images", request,
				// "/uploads", fileName));
			}
			// khai báo danh sách và gọi hàm update trong service
			categoryService.update(category);
			// thông báo
			request.setAttribute("category", category);
			request.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}
}
