package kr.ac.jejunu.sslab.gongdae.service;

import kr.ac.jejunu.sslab.gongdae.dao.ReverseAuctionRepository;
import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReverseAuctionService {
    private final ReverseAuctionRepository reverseAuctionRepository;

    public List<ReverseAuction> getListByRequestId(Long requestId) {
        return reverseAuctionRepository.findAllByrequestId(requestId);
    }
}
