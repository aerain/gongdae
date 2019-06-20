package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.model.CompanyReview;
import kr.ac.jejunu.sslab.gongdae.payload.CompanyReviewPayLoad;
import kr.ac.jejunu.sslab.gongdae.repository.ReviewRepository;
import kr.ac.jejunu.sslab.gongdae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final CompanyService companyService;
    private final ReviewRepository reviewRepository;

    public void addReview(Long companyId, CompanyReview review) throws IllegalAccessException {
        review.setCompany(companyService.findCompany(companyId));
        review.setMember(Optional.of(userRepository.findById(userService.getCurrentSessionId()).get()).orElseThrow(IllegalAccessException::new));
        reviewRepository.save(review);
    }
}
