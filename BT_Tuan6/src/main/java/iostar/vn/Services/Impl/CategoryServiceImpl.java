package iostar.vn.Services.Impl;

import java.io.File;
import java.util.List;

import iostar.vn.DAO.ICategoryDAO;
import iostar.vn.DAO.Impl.CategoryDaoImpl;
import iostar.vn.Models.CategoryModel;
import iostar.vn.Services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

	ICategoryDAO cateDAO = new CategoryDaoImpl();

	@Override
	public List<CategoryModel> findALL() {

		return cateDAO.findALL();
	}

	@Override
	public void insert(CategoryModel category) {
		cateDAO.insert(category);

	}

	@Override
	public void edit(CategoryModel category) {
		CategoryModel oldCate = cateDAO.get(category.getCateid());
		oldCate.setCatename(category.getCatename());
		if (category.getImages() != null) {
			// Xoá ảnh cũ đi
			String fileName = oldCate.getImages();
			final String dir = "E:\\HCMUTE\\Năm 3\\Web Developer\\CRUD_CategoryTable";
			File file = new File(dir + "\\Images" + fileName);
			if (file.exists()) {
				file.delete();
			}
			oldCate.setImages(category.getImages());
		}
		cateDAO.edit(category);
	}

	@Override
	public void delete(int id) {
		cateDAO.delete(id);

	}

	@Override
	public CategoryModel get(int id) {

		return cateDAO.get(id);
	}

	@Override
	public CategoryModel get(String name) {

		return cateDAO.get(name);
	}

	@Override
	public List<CategoryModel> search(String keyword) {
		
		return cateDAO.search(keyword);
	}

}
