package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String place;
    @Column(insertable = false, columnDefinition = "DEFAULT 0")
    private Boolean sold;
    @ManyToOne
    @JoinColumn(name="member_id")
    @JsonIgnore
    private Member member;
    @Column(name = "vr_image_url")
    private String imgUrl;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long companySize;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isAlreadySubmit;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RequestDetail> requestDetailList;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "request_id")
//    @JsonIgnore
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ReverseAuction> reverseAuctionList;
}
