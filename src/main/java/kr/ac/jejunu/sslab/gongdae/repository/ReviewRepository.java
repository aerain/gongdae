package kr.ac.jejunu.sslab.gongdae.repository;

import kr.ac.jejunu.sslab.gongdae.model.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<CompanyReview, Long> {
    @Query("SELECT AVG(r.score) FROM CompanyReview r WHERE r.company.id = :id")
    Optional<Integer> findAvgByCompanyId(@Param("id") Long companyId);

    List<CompanyReview> findBycompanyId(Long companyId);
}
