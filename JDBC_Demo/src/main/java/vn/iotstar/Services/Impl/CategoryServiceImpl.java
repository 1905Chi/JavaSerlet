package vn.iotstar.Services.Impl;

import java.util.List;

import vn.iotstar.Dao.ICategoryDao;
import vn.iotstar.Dao.Impl.CategoryDaoImpl;
import vn.iotstar.Models.CategoryModel;
import vn.iotstar.Services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

	ICategoryDao cateDao = new CategoryDaoImpl();
	@Override
	public List<CategoryModel> findALL() {
		// TODO Auto-generated method stub
		return cateDao.findALL();
	}

	@Override
	public void insert(CategoryModel category) {
		cateDao.insert(category);
	}

	@Override
	public void edit(CategoryModel newCategory) {
		CategoryModel oldCate = cateDao.get(newCategory.getCateid());
		oldCate.setCatename(newCategory.getCatename());
		cateDao.edit(oldCate);
	}


	@Override
	public void delete(int id) {
		cateDao.delete(id);
	}

	@Override
	public CategoryModel get(int id) {
		return cateDao.get(id);
	}
}
