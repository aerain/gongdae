package kr.ac.jejunu.sslab.gongdae.repository;

import kr.ac.jejunu.sslab.gongdae.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Member, Long> {
    Member findByName(@Param("name") String name);
}
