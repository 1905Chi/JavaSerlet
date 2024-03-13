package iostar.vn.Services;

import java.util.List;

import iostar.vn.Models.CategoryModel;

public interface ICategoryService {

	void insert(CategoryModel category);

	void edit(CategoryModel category);

	void delete(int id);

	CategoryModel get(int id);

	CategoryModel get(String name);

	List<CategoryModel> search(String keyword);

	List<CategoryModel> findALL();
}
