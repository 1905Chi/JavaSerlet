package vn.iostar;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
			//Tiếng việt trong Servlet
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			//lấy thông tin từ views
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String gender=request.getParameter("gender");
			String married=request.getParameter("married");
			String nation=request.getParameter("nation");
			String[]
			favorite=request.getParameterValues("favorite");
			String note=request.getParameter("note");
			//tạo biến để đẩy thông tin qua views
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.setAttribute("gender", gender);
			request.setAttribute("married", married);
			request.setAttribute("nation", nation);
			StringBuilder sb = new StringBuilder();
			for(String item : favorite) {
			sb.append(item).append(",");
			}
			if(sb.length()!=0) {
				request.setAttribute("favorite", sb.toString());
			}
			else
			{
				
			}
			
			
			request.setAttribute("note", note);
			//đẩy thông tin qua views
			request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			

}
