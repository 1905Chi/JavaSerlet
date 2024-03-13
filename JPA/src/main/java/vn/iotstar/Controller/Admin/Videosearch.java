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



import vn.iotstar.Entity.Video;
import vn.iotstar.Service.IVideoService;
import vn.iotstar.Service.Impl.VideoServiceImpl;



@SuppressWarnings("serial")
@MultipartConfig
@WebServlet(urlPatterns = {"/admin-video/search"})
public class Videosearch extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IVideoService VideoService = new VideoServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String videoname = request.getParameter("title");
		List<Video> videos = VideoService.findByVideoname(videoname);

		request.setAttribute("", videos);
		
		try {
			request.getRequestDispatcher("/views/admin/video/listvideoname.jsp").forward(request, response);
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
		req.getRequestDispatcher("/views/admin/video/listvideoname.jsp").forward(req, resp);	
	}


}
