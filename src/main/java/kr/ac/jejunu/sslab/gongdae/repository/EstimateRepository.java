package kr.ac.jejunu.sslab.gongdae.repository;

import kr.ac.jejunu.sslab.gongdae.model.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    public List<Estimate> findAllByReverseAuctionId(Long reverseAuctionId);
}
