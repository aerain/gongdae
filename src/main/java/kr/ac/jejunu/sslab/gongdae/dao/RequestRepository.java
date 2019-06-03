package kr.ac.jejunu.sslab.gongdae.dao;

import kr.ac.jejunu.sslab.gongdae.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByclientId(Long clientId);
}
