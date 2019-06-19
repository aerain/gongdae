package kr.ac.jejunu.sslab.gongdae.payload;

import kr.ac.jejunu.sslab.gongdae.model.Member;
import lombok.Data;

@Data
public class MemberPayLoad {
    private Member member;
    private String companyDescription;
}
