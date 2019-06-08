package kr.ac.jejunu.sslab.gongdae.repository;

import kr.ac.jejunu.sslab.gongdae.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(@Param("name") String name);
}
