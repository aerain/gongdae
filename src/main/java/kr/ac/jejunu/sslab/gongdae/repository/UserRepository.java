package kr.ac.jejunu.sslab.gongdae.repository;

import kr.ac.jejunu.sslab.gongdae.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByusername(@Param("name") String name);
    Optional<Member> findByemail(@Param("email") String email);
}
