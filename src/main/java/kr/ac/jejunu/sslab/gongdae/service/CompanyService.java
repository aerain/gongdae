package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.model.Company;
import kr.ac.jejunu.sslab.gongdae.repository.CompanyRepository;
import kr.ac.jejunu.sslab.gongdae.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;

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
                    return company;
                })
                .orElseThrow(IllegalAccessException::new);
    }
}
