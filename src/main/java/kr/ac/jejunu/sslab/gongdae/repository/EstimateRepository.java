package kr.ac.jejunu.sslab.gongdae.repository;

import kr.ac.jejunu.sslab.gongdae.model.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    public List<Estimate> findAllByReverseAuctionId(Long reverseAuctionId);
    @Query("SELECT SUM(e.price) FROM Estimate e WHERE e.reverseAuction.id = :id")
    public Long sumByReverseAuctionId(@Param("id") Long reverseAuctionId);
}
