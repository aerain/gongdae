package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.repository.*;
import kr.ac.jejunu.sslab.gongdae.model.Estimate;
import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import kr.ac.jejunu.sslab.gongdae.vo.ReverseAuctionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReverseAuctionService {
    private final ReverseAuctionRepository reverseAuctionRepository;
    private final EstimateRepository estimateRepository;
    private final CompanyRepository companyRepository;
    private final RequestRepository requestRepository;
    private final ReviewRepository reviewRepository;
    private final UserService userService;

    public ReverseAuction getReverseAuctionbyId(Long id) throws IllegalAccessException {
        return Optional.of(reverseAuctionRepository.findById(id).get()).map(reverseAuction -> {
            reverseAuction.setPrice(estimateRepository.sumByReverseAuctionId(id));
            reverseAuction.setEstimateList(estimateRepository.findAllByReverseAuctionId(id));
            reverseAuction.getCompany()
                    .setScore(reviewRepository.findAvgByCompanyId(reverseAuction.getCompany().getId()).orElse(0));
            return reverseAuction;
        }).orElseThrow(IllegalAccessException::new);
    }

    public List<ReverseAuction> getListByRequestId(Long requestId) {
        List<ReverseAuction> reverseAuctionList = reverseAuctionRepository.findAllByrequestId(requestId);
        reverseAuctionList.parallelStream().forEach(item ->
                item.setPrice(estimateRepository.sumByReverseAuctionId(item.getId())));
        return reverseAuctionList;
    }

    public void saveReverse(Long requestId, List<Estimate> estimateList) throws IllegalAccessException {

        if(userService.getCurrentUser().getType() != 1 || reverseAuctionRepository.
                existsBycompanyIdAndRequestId(userService.getCurrentSessionId(), requestId))
            throw new IllegalAccessException();

        ReverseAuction reverseAuction = reverseAuctionRepository.save(
                ReverseAuction.builder()
//                        .id(userService.getCurrentSessionId())
                        .company(companyRepository.findById(userService.getCurrentSessionId()).orElseThrow(IllegalAccessException::new))
                        .request(requestRepository.findById(requestId).orElseThrow(IllegalAccessException::new))
                        .build());
        estimateList.parallelStream().forEach(estimate -> estimate.setReverseAuction(reverseAuction));
        estimateRepository.saveAll(estimateList);
    }

    public boolean isExists(Long requestId, Long companyId) {
        return reverseAuctionRepository.existsBycompanyIdAndRequestId(companyId, requestId);
    }
}
