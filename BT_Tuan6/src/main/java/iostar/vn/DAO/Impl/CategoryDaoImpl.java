package iostar.vn.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import iostar.vn.DAO.ICategoryDAO;
import iostar.vn.DBConnection.DBConnection;
import iostar.vn.Models.CategoryModel;

public class CategoryDaoImpl extends DBConnection implements ICategoryDAO {

	@Override
	public List<CategoryModel> findALL() {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();

		String sql = "SELECT * FROM Category ";
		try {
			Connection con = super.getConnection();// kết nối data
			PreparedStatement ps = con.prepareStatement(sql);

			// THiết lập tham số cho phát biểu nếu có
			ResultSet rs = ps.executeQuery(); // Thực thi thêm Data trả về resultSet

			while (rs.next()) {// Lặp qua từng dòng trong bảng đưa vào Model

				CategoryModel category = new CategoryModel();// Tạo đối tượng CategoryModel

				category.setCateid(rs.getInt("categoryId")); // Đưa dữ liệu CategoryID trong RS sang CategoryModel
				category.setCatename(rs.getString("categoryName")); // Đưa dữ liệu categoryName trong RS sang
																	// CategoryModel
				category.setImages(rs.getString("images")); // Đưa dữ liệu images trong RS sang CategoryModel
				category.setStatus(rs.getInt("status")); // Đưa dữ liệu status trong RS sang CategoryModel

				categories.add(category); // Thêm từng dòng vào danh sách CategoryModel
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categories; // trả về danh sách đã thêm
	}

	@Override
	public void insert(CategoryModel category) {

		String sql = "INSERT INTO category(categoryName,images) VALUES (?,?) ";
		try {
			Connection conn = super.getConnection(); // mở cổng kết nối sql
			PreparedStatement ps = conn.prepareStatement(sql); // tạo ra đối tương có nhiệm vụ trong câu lệnh sql
			ps.setString(1, category.getCatename()); // gán giá trị Catename vào ps có index =1
			ps.setString(2, category.getImages()); // gán giá trị Images vào ps có index =2
			ps.executeUpdate(); // cập nhật dữ liệu trong ps
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(CategoryModel category) {

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM category WHERE categoryId = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CategoryModel get(int id) {
		String sql = ("SELECT * FROM category WHERE  categoryId = ?");
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(); // câu lệnh để đọc dữ liệu 
			while(rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCateid(rs.getInt("categoryId"));
				category.setCatename(rs.getString("categoryName"));
				category.setImages(rs.getString("images"));
				return category;				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoryModel get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryModel> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
