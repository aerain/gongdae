package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.model.Company;
import kr.ac.jejunu.sslab.gongdae.model.CompanyReview;
import kr.ac.jejunu.sslab.gongdae.repository.CompanyRepository;
import kr.ac.jejunu.sslab.gongdae.repository.ReverseAuctionRepository;
import kr.ac.jejunu.sslab.gongdae.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    private final ReverseAuctionRepository reverseAuctionRepository;
    private final UserService userService;

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company findCompany(Long companyId) throws IllegalAccessException {
        return Optional.of(companyRepository.findById(companyId).get())
                .map(company -> {
                    company.setScore(Optional
                            .of(reviewRepository.findAvgByCompanyId(companyId)
                                    .get())
                            .orElse(0));
                    company.setChosenCount(reverseAuctionRepository.countBycompanyIdAndChosen(companyId, true));
                    company.setCompanyReview(reviewRepository.findBycompanyId(companyId));
                    return company;
                })
                .orElseThrow(IllegalAccessException::new);
    }


}
