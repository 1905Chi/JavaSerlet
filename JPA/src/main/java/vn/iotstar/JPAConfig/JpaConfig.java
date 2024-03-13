package vn.iotstar.JPAConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// Kết nối cơ sở dữ liệu và thực hiện các thao tác CRUD
public class JpaConfig {
	public static EntityManager getEntityManager() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("springDBMSSQL");

		return factory.createEntityManager();

	}
}
