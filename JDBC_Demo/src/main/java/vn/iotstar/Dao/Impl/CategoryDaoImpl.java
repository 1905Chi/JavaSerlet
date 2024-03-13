package vn.iotstar.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBConnect;
import vn.iotstar.Dao.ICategoryDao;
import vn.iotstar.Models.CategoryModel;

public class CategoryDaoImpl extends DBConnect implements ICategoryDao {

	@Override
	public List<CategoryModel> findALL() {
		List<CategoryModel> categories = new ArrayList<CategoryModel>(); // tao mot list danh sach
		String sql = "SELECT * FROM Category";
		try {
			Connection conn = super.getConnection(); // ket noi database
			PreparedStatement ps = conn.prepareStatement(sql); // nem cau sql vao cho phat bieu preparestatement
			ResultSet rs = ps.executeQuery(); // executeUpdate voi cau lenh thay doi database (thuc thi cau sql tra ve ResultSet))
			while (rs.next()) { // doc tung hang trong bang RS dua vao Model
				CategoryModel category = new CategoryModel(); // khoi tao doi tuong CategoryModel

				category.setCateid(rs.getInt("categoryId")); // dua du lieu CateID trong RS sag CateID cua model
				category.setCatename(rs.getString("categoryName"));
				category.setImage(rs.getString("images"));
				category.setStatus(rs.getInt("status"));

				categories.add(category); // them vao danh sach List
			}} catch (Exception e) {
				e.printStackTrace();
			}
		return categories; // tra ve danh sach
	
	}
	@Override
	public void insert(CategoryModel categoryModel) {
		String sql = "INSERT INTO category(categoryName,images,status) VALUES (?,?,?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoryModel.getCatename());
			ps.setString(2, categoryModel.getImage());
			ps.setInt(3, categoryModel.getStatus());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(CategoryModel category) {
		String sql = "UPDATE category SET categoryName = ?, images=? WHERE categoryId = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getImage());
			ps.setInt(3, category.getCateid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public void delete(int id) {String sql = "DELETE FROM category WHERE categoryId = ?";
	try {
		Connection con = super.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}


	}

	@Override
	public CategoryModel get(int id) {
		String sql = "SELECT * FROM category WHERE categoryId = ? ";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCateid(rs.getInt("categoryId"));
				category.setCatename(rs.getString("categoryName"));
				category.setImage(rs.getString("images"));
				return category;
			}} catch (Exception e) {
				e.printStackTrace();}
		return null;

	}
}
