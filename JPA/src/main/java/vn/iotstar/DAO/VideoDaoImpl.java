package vn.iotstar.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import vn.iotstar.Entity.Category;
import vn.iotstar.Entity.Video;
import vn.iotstar.JPAConfig.JpaConfig;

public class VideoDaoImpl implements IVideoDao {

	@Override
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Video c";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public List<Video> findByVideoname(String vdname) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Video c WHERE c.title like :vdname";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("vdname","%"+vdname+"%");
		return query.getResultList();
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(String videoid) {
		EntityManager enma = JpaConfig.getEntityManager();
		Video video = enma.find(Video.class, videoid);
		return video;
	}

	@Override
	public void delete(String videoid) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// TÌm video
			Video video = enma.find(Video.class, videoid);
			if (video != null) {
				// delete
				enma.remove(video);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}

	}

	@Override
	public void update(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// update
			enma.merge(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}

	}

	@Override
	public void insert(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// thêm
			enma.persist(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}

	}

	@Override
	public List<Video> findByCategoryId(Category category) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Video c WHERE c.Category like : cate";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("cate",category);
		return query.getResultList();
	}

}
