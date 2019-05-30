package kr.ac.jejunu.sslab.gongdae;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {
    private Long id;
    private Long clientId;
    private String title;
    private String place;
    private String imgUrl;
    private Long companySize;
}
