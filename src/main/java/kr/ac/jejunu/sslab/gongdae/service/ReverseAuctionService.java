package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.repository.CompanyRepository;
import kr.ac.jejunu.sslab.gongdae.repository.EstimateRepository;
import kr.ac.jejunu.sslab.gongdae.repository.RequestRepository;
import kr.ac.jejunu.sslab.gongdae.repository.ReverseAuctionRepository;
import kr.ac.jejunu.sslab.gongdae.model.Estimate;
import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import kr.ac.jejunu.sslab.gongdae.vo.ReverseAuctionVO;
import lombok.RequiredArgsConstructor;
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
    private final UserService userService;

    public ReverseAuction getReverseAuctionbyId(Long id) throws IllegalAccessException {
        return Optional.ofNullable(reverseAuctionRepository.findById(id)).map(reverseAuctionOptional -> {
            ReverseAuction reverseAuction = reverseAuctionOptional.get();
            reverseAuction.setPrice(estimateRepository.sumByReverseAuctionId(id));
            reverseAuction.setEstimateList(estimateRepository.findAllByReverseAuctionId(id));
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
        ReverseAuction reverseAuction = reverseAuctionRepository
                .save(ReverseAuction.builder()
                        .company(companyRepository.findBymemberId(userService.getCurrentSessionId()).orElseThrow(IllegalAccessException::new))
                        .request(requestRepository.findById(requestId).orElseThrow(IllegalAccessException::new))
                        .build());
        estimateList.parallelStream().forEach(estimate -> estimate.setReverseAuction(reverseAuction));
        estimateRepository.saveAll(estimateList);
    }
}
