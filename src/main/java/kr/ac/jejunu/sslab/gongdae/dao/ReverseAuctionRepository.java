package kr.ac.jejunu.sslab.gongdae.dao;

import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReverseAuctionRepository extends JpaRepository<ReverseAuction, Long> {
    List<ReverseAuction> findAllByrequestId(@Param("request_id") Long requestId);
}
