package vn.iotstar.Services;

import java.util.List;

import vn.iotstar.Models.CategoryModel;

public interface ICategoryService {

	List<CategoryModel> findALL();
	void insert(CategoryModel categoryModel);
	void edit(CategoryModel category);
	void delete(int id);
	CategoryModel get(int id);
}
