package vn.iotstar.Service.Impl;

import java.util.List;

import vn.iotstar.DAO.IVideoDao;
import vn.iotstar.DAO.VideoDaoImpl;
import vn.iotstar.Entity.Category;
import vn.iotstar.Entity.Video;
import vn.iotstar.Service.IVideoService;

public class VideoServiceImpl implements IVideoService {

	IVideoDao videoDao = new VideoDaoImpl();

	@Override
	public int count() {
		return videoDao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		return videoDao.findAll(page, pagesize);
	}

	@Override
	public List<Video> findByVideoname(String vdname) {
		// TODO Auto-generated method stub
		return videoDao.findByVideoname(vdname);
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return videoDao.findAll();
	}

	@Override
	public Video findById(String videoid) {
		// TODO Auto-generated method stub
		return videoDao.findById(videoid);
	}

	@Override
	public void delete(String videoid) throws Exception {
		videoDao.delete(videoid);

	}

	@Override
	public void update(Video video) {
		videoDao.update(video);

	}

	@Override
	public void insert(Video video) {
		videoDao.insert(video);

	}

	@Override
	public List<Video> findByCategoryId(Category categoryid) {

		return videoDao.findByCategoryId(categoryid);
	}

}