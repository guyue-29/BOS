package cn.itcast.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.bos.domain.base.Courier;

public interface CourierRepository extends JpaRepository<Courier, Integer>,
		JpaSpecificationExecutor<Courier> {

	@Query(value = "update Courier set deltag=?2 where id = ?1")
	@Modifying
	public void updateDelTag(Integer id,Integer type);
	


}
