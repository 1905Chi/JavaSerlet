package iostar.vn.DAO;

import java.util.List;

import iostar.vn.Models.CategoryModel;

public interface ICategoryDAO {
	void insert(CategoryModel category);
	void edit(CategoryModel category);
	void delete(int id);
	CategoryModel get(int id);
	CategoryModel get(String name);
	List<CategoryModel> findALL();
	List<CategoryModel> search(String keyword);

}
