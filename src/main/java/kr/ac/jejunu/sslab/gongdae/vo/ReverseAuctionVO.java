package kr.ac.jejunu.sslab.gongdae.vo;

import kr.ac.jejunu.sslab.gongdae.model.Estimate;
import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReverseAuctionVO {
    private ReverseAuction reverseAuction;
    private List<Estimate> estimateList;
}
