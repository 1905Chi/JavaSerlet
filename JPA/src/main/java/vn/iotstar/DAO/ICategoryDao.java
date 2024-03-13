package vn.iotstar.DAO;

import java.util.List;

import vn.iotstar.Entity.Category;

public interface ICategoryDao {

	void insert(Category category);

	int count();

	List<Category> findByCategoryname(String catname);

	List<Category> findAll(int page, int pagesize);

	List<Category> findAll();

	Category findById(int categoryId);

	void delete(int cateid) throws Exception;

	void update(Category category);

}
