package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBConnect;
import vn.iotstar.Dao.IProductDao;
import vn.iotstar.Models.ProductModel;

public class ProductWebDaoImpl extends DBConnect implements IProductDao   {

	@Override
	public List<ProductModel> findLastProduct() {
		// TODO Auto-generated method stub
		List<ProductModel> products = new ArrayList<ProductModel>();
		String sql = "select top (4) productId,productName,productCode,description,price,images,status,seller"
				+ "\r\n"+"from [ShopOnline].[dbo].[Product]\r\n"
		+"order by productId Desc";
		try
		{
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ProductModel product = new ProductModel();
				
				product.setStatus(rs.getString("status"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("images"));
				product.setPrice(rs.getDouble("price"));
				product.setProductCode(rs.getInt("productCode"));
				product.setProductId(rs.getInt("productId"));
				product.setProductName(rs.getString("productName"));
				product.setSeller(rs.getInt("sellerId"));
				products.add(product);
			}
		}catch(Exception e)
		
		{
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<ProductModel> findbestProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductModel findTopproduct() {
		// TODO Auto-generated method stub
		return null;
	}

}
