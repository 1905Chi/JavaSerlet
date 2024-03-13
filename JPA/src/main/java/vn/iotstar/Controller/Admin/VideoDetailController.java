package vn.iotstar.Controller.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.Entity.Category;
import vn.iotstar.Entity.Video;
import vn.iotstar.Service.ICategoryService;
import vn.iotstar.Service.IVideoService;
import vn.iotstar.Service.Impl.CategoryServiceImpl;
import vn.iotstar.Service.Impl.VideoServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin-video/detail", "/admin-video/pagesize" })
public class VideoDetailController extends HttpServlet {
	IVideoService videoService = new VideoServiceImpl();
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("detail")) {
			videoDetail(req, resp);
		} else if (url.contains("pagesize")) {
			videoPagesize(req, resp);
		} else if (url.contains("search")) {
			List<Video> videos = videoService.findAll();

			req.setAttribute("videos", videos);
			req.getRequestDispatcher("/views/admin/video/search.jsp").forward(req, resp);
		}

	}

	private void searchVideo(HttpServletRequest req, HttpServletResponse resp) {

		String videoname = req.getParameter("title");
		List<Video> videos = videoService.findByVideoname(videoname);

		req.setAttribute("videos", videos);
		req.setAttribute("tenvideo", videoname);
		try {
			req.getRequestDispatcher("/views/admin/video/search.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void videoPagesize(HttpServletRequest req, HttpServletResponse resp) {
		IVideoService VideoService = new VideoServiceImpl();

		// gọi hàm findAll để lấy thông tin từ entity
		String indexPage = req.getParameter("index");
		// phân trang, khởi tạo trang đầu
		if (indexPage == null) {
			indexPage = "1";
		}
		int indexp = Integer.parseInt(indexPage);
		// Get data từ DAO
		int countP = VideoService.count();

		int pagesize = 2;
		// chia trang cho count
		int endPage = countP / pagesize;
		if (countP % pagesize != 0) {
			endPage++;
		}

		List<Video> productListNew = VideoService.findAll(indexp - 1, pagesize);
		req.setAttribute("videos", productListNew);
		// Truyền lên JSP
		req.setAttribute("endP", endPage);
		req.setAttribute("tag", indexp);
		try {
			req.getRequestDispatcher("/views/admin/video/add.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void videoDetail(HttpServletRequest req, HttpServletResponse resp) {

		String id = req.getParameter("cid");
		
		List<Video> videoAll;
		if (id == null) {
			videoAll = videoService.findAll();
		} else {
			Category cate = cateService.findById(Integer.parseInt(id));
			videoAll = videoService.findByCategoryId(cate);
		}
		List<Category> cateList = cateService.findAll();
		req.setAttribute("cateList", cateList);
		req.setAttribute("videoAll", videoAll);
		try {
			req.getRequestDispatcher("/views/admin/video/detail.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("search")) {
			searchVideo(req, resp);
		}
	}

}
