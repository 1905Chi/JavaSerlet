package iostar.vn.Services.Impl;

import java.util.List;

import iostar.vn.Services.ICategoryService;
import iotstar.vn.Dao.ICategoryDao;
import iotstar.vn.Dao.Impl.CategoryDaoImpl;
import iotstar.vn.Model.CategoryModel;

public class CategoryServiceImpl implements ICategoryService{
	ICategoryDao cateDao = new CategoryDaoImpl();
	@Override
	public List<CategoryModel> findALL() {
		
		return cateDao.findALL();
	}

}
