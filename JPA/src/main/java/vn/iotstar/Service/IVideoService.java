package vn.iotstar.Service;

import java.util.List;

import vn.iotstar.Entity.Category;
import vn.iotstar.Entity.Video;

public interface IVideoService {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoname(String vdname);

	List<Video> findAll();

	Video findById(String id);

	void delete(String videoid) throws Exception;

	void update(Video video);

	void insert(Video video);

	List<Video> findByCategoryId(Category category);

}
