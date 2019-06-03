package kr.ac.jejunu.sslab.gongdae.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String place;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;
    @Column(name = "vr_image_url")
    private String imgUrl;
    @Transient
    private Long companySize;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private List<RequestDetail> requestDetailList;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private List<ReverseAuction> reverseAuctionList;
}
