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
import vn.iotstar.Entity.Video;
import vn.iotstar.Service.ICategoryService;
import vn.iotstar.Service.IVideoService;
import vn.iotstar.Service.Impl.CategoryServiceImpl;
import vn.iotstar.Service.Impl.VideoServiceImpl;
import vn.iotstar.Util.Constant;
import vn.iotstar.Util.UploadUtils;

@SuppressWarnings("serial")
@MultipartConfig
@WebServlet(urlPatterns = { "/admin-video", "/admin-video/create", "/admin-video/update", "/admin-video/edit",
		"/admin-video/delete", "/admin-video/reset" })
public class VideoController extends HttpServlet {

	IVideoService VideoService = new VideoServiceImpl();
	ICategoryService categoryService = new CategoryServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// kiểm tra url rồi chuyển đến hàm tương ứng
		// lấy url
		String url = request.getRequestURL().toString();
		Video video = null;
		if (url.contains("create")) {
			request.getRequestDispatcher("/views/admin/video/add.jsp").forward(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
			video = new Video();
			request.setAttribute("video", video);
		} else if (url.contains("edit")) {
			edit(request, response);
		} else if (url.contains("reset")) {
			video = new Video();
			request.setAttribute("video", video);
		}

		// gọi hàm findAll để lấy thông tin từ entity
		String indexPage = request.getParameter("index");
		// phân trang, khởi tạo trang đầu
		if (indexPage == null) {
			indexPage = "1";
		}
		int indexp = Integer.parseInt(indexPage);
		// Get data từ DAO
		int countP = VideoService.count();

		int pagesize = 6;
		// chia trang cho count
		int endPage = countP / pagesize;
		if (countP % pagesize != 0) {
			endPage++;
		}

		List<Video> productListNew = VideoService.findAll(indexp - 1, pagesize);
		request.setAttribute("videos", productListNew);
		// Truyền lên JSP
		request.setAttribute("endP", endPage);
		request.setAttribute("tag", indexp);
		request.getRequestDispatcher("/views/admin/video/list.jsp").forward(request, response);
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
			request.setAttribute("video", new Video());
		}

		// gọi hàm findAll để lấy thông tin từ entity
				String indexPage = request.getParameter("index");
				// phân trang, khởi tạo trang đầu
				if (indexPage == null) {
					indexPage = "1";
				}
				int indexp = Integer.parseInt(indexPage);
				// Get data từ DAO
				int countP = VideoService.count();

				int pagesize = 6;
				// chia trang cho count
				int endPage = countP / pagesize;
				if (countP % pagesize != 0) {
					endPage++;
				}

				List<Video> productListNew = VideoService.findAll(indexp - 1, pagesize);
				request.setAttribute("videos", productListNew);
				// Truyền lên JSP
				request.setAttribute("endP", endPage);
				request.setAttribute("tag", indexp);
				request.getRequestDispatcher("/views/admin/video/list.jsp").forward(request, response);
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			// khỏi tạo đối tượng Model
			Video video = new Video();
			// sử dụng BeanUtils để tự lấy các name Field trên form
			// tên field phải trùng với entity
			BeanUtils.populate(video, request.getParameterMap());
		
			String categoryId = request.getParameter("categoryId");
			Category cate = categoryService.findById(Integer.parseInt(categoryId));
			video.setCategory(cate);
			// xử lý hình ảnh
			String fileName = video.getTitle() + System.currentTimeMillis();
			video.setPoster(UploadUtils.processUpload("poster", request, Constant.DIR + "\\video\\", fileName));
			// gọi hàm insert để thêm dữ liệu
			VideoService.insert(video);
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
			List<Video> list = VideoService.findAll();
			// thông báo
			request.setAttribute("videos", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// khai báo biến userId
			/* Long videoId = Long.getLong(request.getParameter("videoId")); */
			 String videoId = request.getParameter("videoId"); 
			// khởi tạo DAO
			// gọi hàm insert để thêm dữ liệu
			Video video = VideoService.findById(videoId);
			// thông báo
			request.setAttribute("video", video);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// lấy dữ liệu trong jsp
			String VideoId = request.getParameter("videoId");
			// khởi tạo DAO
			// khai báo danh sách và gọi hàm findAll() trong dao
			VideoService.delete(VideoId);
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
			Video video = new Video();
			BeanUtils.populate(video, request.getParameterMap());
			// khởi tạo DAO
			Video oldvideo = VideoService.findById(video.getVideoId());
			// xử lý hình ảnh
			if (request.getPart("poster").getSize() == 0) {
				video.setPoster(oldvideo.getPoster());
			} else {
				if (oldvideo.getPoster() != null) {
					// XOA ANH CU DI
					String fileName = oldvideo.getPoster();
					File file = new File(Constant.DIR + "\\Video\\" + fileName);
					if (file.delete()) {
						System.out.println("Đã xóa thành công");
					} else {
						System.out.println(Constant.DIR + "\\Video\\" + fileName);
					}
				}
				String fileName = video.getTitle() + System.currentTimeMillis();
				video.setPoster(UploadUtils.processUpload("poster", request, Constant.DIR + "\\video\\", fileName));
				// Video.setImages(UploadUtils.processUploadFolderWeb("images", request,
				// "/uploads", fileName));
			}
			// khai báo danh sách và gọi hàm update trong service
			VideoService.update(video);
			// thông báo
			request.setAttribute("video", video);
			request.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}
}
