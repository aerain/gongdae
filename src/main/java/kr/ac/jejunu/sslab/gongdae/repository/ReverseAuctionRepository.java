package kr.ac.jejunu.sslab.gongdae.repository;

import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReverseAuctionRepository extends JpaRepository<ReverseAuction, Long> {
    List<ReverseAuction> findAllByrequestId(@Param("request_id") Long requestId);

    Long countByrequestId(Long id);

    Integer countBycompanyIdAndChosen(Long companyId, Boolean chosen);
}
