package iotstar.vn.Controllers.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iostar.vn.Services.ICategoryService;
import iostar.vn.Services.Impl.CategoryServiceImpl;
import iotstar.vn.Model.CategoryModel;


@WebServlet(urlPatterns = { "/category" })
public class CategoryListController extends HttpServlet{


	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<CategoryModel> cateList = cateService.findALL();
		req.setAttribute("cateList", cateList);
		RequestDispatcher dispatcher =
		req.getRequestDispatcher("/views/admin/list-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPost(req, resp);
	}

}
