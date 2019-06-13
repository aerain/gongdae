package kr.ac.jejunu.sslab.gongdae.repository;

import kr.ac.jejunu.sslab.gongdae.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
//    @Query(value = "SELECT * FROM request r WHERE r.user_id = :user_id", nativeQuery = true)
    List<Request> findAllBymemberId(@Param("member_id") Long memberId);

    @Query(value = "SELECT r FROM Request r WHERE r.member.id = :user_id and r.sold = false")
    List<Request> findAllOnProgressByuserId(@Param("user_id") Long clientId);
}
