package kr.ac.jejunu.sslab.gongdae.repository;

import kr.ac.jejunu.sslab.gongdae.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
//    @Query(value = "SELECT * FROM request r WHERE r.user_id = :user_id", nativeQuery = true)
    List<Request> findAllByuserId(@Param("user_id") Long userId);
}
