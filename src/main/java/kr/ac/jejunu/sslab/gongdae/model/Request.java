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
    private Long clientId;
    private String title;
    private String place;
    @Column(name = "vr_image_url")
    private String imgUrl;
    private Long companySize;
    @OneToMany
    @JoinColumn(name = "request_id")
    @JsonIgnore
//    @JsonIgnoreProperties(value={"request"})
    private List<RequestDetail> requestDetailList;
}
