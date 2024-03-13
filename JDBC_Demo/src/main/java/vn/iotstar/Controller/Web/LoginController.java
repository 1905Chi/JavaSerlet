package vn.iotstar.Controller.Web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("Serial")
@WebServlet(urlPatterns ="/login" )
public class LoginController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		//lấy dữ liệu từ tham số của form
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		if(user.equals("trung") && pass.equals("123"))
		{
		//khởi tạo cookie
		Cookie cookie = new Cookie("username", user);
		
		//thiết lập thời gian tồn tại 30s của cookie
		cookie.setMaxAge(30);
		//thêm cookie vào response
		resp.addCookie(cookie);
		//chuyển sang trang HelloServlet
		resp.sendRedirect("/Demo_Form/admin/home");
		}else {
		//chuyển sang trang LoginServlet
		resp.sendRedirect("login");
		}
	}
}
