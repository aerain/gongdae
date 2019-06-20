package kr.ac.jejunu.sslab.gongdae.payload;

import kr.ac.jejunu.sslab.gongdae.model.Estimate;
import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RequestReversePayLoad implements Serializable {
    private Long requestId;
    private List<Estimate> estimateList;
}
