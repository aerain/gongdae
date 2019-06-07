package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.dao.ReverseAuctionRepository;
import kr.ac.jejunu.sslab.gongdae.model.Estimate;
import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReverseAuctionService {
    private final ReverseAuctionRepository reverseAuctionRepository;

    public ReverseAuction getReverseAuctionbyId(Long id) {
        Optional<ReverseAuction> reverseAuctionOptional = reverseAuctionRepository.findById(id);
        if(!reverseAuctionOptional.isPresent())
            return null;
        ReverseAuction reverseAuction = reverseAuctionOptional.get();
        long price = 0;
        for(Estimate estimate : reverseAuction.getEstimateList())
            price += estimate.getPrice();
        return reverseAuction;
    }

    public List<ReverseAuction> getListByRequestId(Long requestId) {
        return reverseAuctionRepository.findAllByrequestId(requestId);
    }
}
