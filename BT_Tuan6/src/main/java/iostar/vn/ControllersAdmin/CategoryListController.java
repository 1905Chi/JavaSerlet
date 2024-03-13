package iostar.vn.ControllersAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iostar.vn.Models.CategoryModel;
import iostar.vn.Services.ICategoryService;
import iostar.vn.Services.Impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/category" })
public class CategoryListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CategoryModel> cateList = cateService.findALL();
		int STT =1;
		req.setAttribute("cateList", cateList);
		req.setAttribute("STT", STT);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/Views/list-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
