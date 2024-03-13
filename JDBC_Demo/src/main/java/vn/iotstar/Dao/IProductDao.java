package vn.iotstar.Dao;

import java.util.List;

import vn.iotstar.Models.ProductModel;

public interface  IProductDao {
	List<ProductModel> findLastProduct();
	List<ProductModel> findbestProduct();
	ProductModel findTopproduct();
}
