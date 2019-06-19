package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.repository.EstimateRepository;
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
    public ReverseAuction getReverseAuctionbyId(Long id) {
        Optional<ReverseAuction> reverseAuctionOptional = reverseAuctionRepository.findById(id);
        if(!reverseAuctionOptional.isPresent())
            return null;

        ReverseAuction reverseAuction = reverseAuctionOptional.get();
        reverseAuction.setPrice(estimateRepository.sumByReverseAuctionId(id));
        reverseAuction.setEstimateList(estimateRepository.findAllByReverseAuctionId(id));
        System.out.println(reverseAuction.getCompany().getMember().getUsername());
        return reverseAuction;
    }

    public List<ReverseAuction> getListByRequestId(Long requestId) {
        List<ReverseAuction> reverseAuctionList = reverseAuctionRepository.findAllByrequestId(requestId);
        reverseAuctionList.parallelStream().forEach(item ->
                item.setPrice(estimateRepository.sumByReverseAuctionId(item.getId())));
        return reverseAuctionList;
    }
}
