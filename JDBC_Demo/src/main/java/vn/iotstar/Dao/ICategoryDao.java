package vn.iotstar.Dao;

import java.util.List;

import vn.iotstar.Models.CategoryModel;

public interface ICategoryDao {
	List<CategoryModel> findALL();
	void insert(CategoryModel categoryModel);
	void edit(CategoryModel category);
	void delete(int id);
	CategoryModel get(int id);
}
