package vn.iostar;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class HelloSerlet extends HttpServlet{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8373198440115664715L;
	/**
	 * 
	 */
	
	public void init() throws ServletException {
		System.out.println("bắt đầu servlet");
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		System.out.println("phương thức sử dụng là: "+ req.getMethod());
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter wr=resp.getWriter();
		wr.println("<h1>Xin chào bạn</h1>");
		wr.close();
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Kết thức servlet");
	}

}
