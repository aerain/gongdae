package kr.ac.jejunu.sslab.gongdae.dao;

import kr.ac.jejunu.sslab.gongdae.model.Request;
import kr.ac.jejunu.sslab.gongdae.model.RequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RequestDetailRepository extends JpaRepository<RequestDetail, Long> {
    List<RequestDetail> findAllByRequestId(@Param("request_id") Long requestId);
}
