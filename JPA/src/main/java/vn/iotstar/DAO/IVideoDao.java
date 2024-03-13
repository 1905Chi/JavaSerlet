package vn.iotstar.DAO;

import java.util.List;

import vn.iotstar.Entity.Category;
import vn.iotstar.Entity.Video;

public interface IVideoDao {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoname(String vdname);

	List<Video> findAll();

	Video findById(String videoid);

	List<Video> findByCategoryId(Category category);
	
	void delete(String videoid) throws Exception;

	void update(Video video);

	void insert(Video video);

}
