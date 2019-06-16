package kr.ac.jejunu.sslab.gongdae.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import kr.ac.jejunu.sslab.gongdae.payload.ConfirmPayLoad;
import kr.ac.jejunu.sslab.gongdae.repository.*;
import kr.ac.jejunu.sslab.gongdae.model.Request;
import kr.ac.jejunu.sslab.gongdae.model.RequestDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final FileUploadService fileUploadService;
    private final RequestRepository requestRepository;
    private final RequestDetailRepository requestDetailRepository;
    private final ReverseAuctionRepository reverseAuctionRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public Request getRequestById(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        if(!request.isPresent()) return null;

        Request presentRequest = request.get();
        presentRequest.setRequestDetailList(requestDetailRepository.findAllByrequestId(id));
        presentRequest.setReverseAuctionList(reverseAuctionRepository.findAllByrequestId(id));
        presentRequest.setCompanySize((long) presentRequest.getReverseAuctionList().size());
        return presentRequest;
    }

    public void saveRequest(String title, String place, MultipartFile vrImgUrl, String requestList) throws IOException {
        List<RequestDetail> requestDetailList = new Gson().fromJson(requestList, new TypeToken<List<RequestDetail>>(){}.getType());
        String imagePath = fileUploadService.uploadFile(vrImgUrl);

        // Todo 계정 연동
        String name = "aerain";
        Request req = Request.builder()
                .title(title)
                .place(place)
                .imgUrl(imagePath)
                .member(userRepository.findByusername(name).get()).build();

        // cascade
        requestDetailList.parallelStream().forEach(requestDetail ->
                requestDetail.setRequest(req));

        // 리퀘스트 저장
        requestRepository.save(req);
        // 요구사항 저장
        requestDetailRepository.saveAll(requestDetailList);
    }

    public List<Request> getRequestListByUserId(Long clientId) {
        List<Request> requestList = requestRepository.findAllOnProgressByuserId(clientId);
        requestList.parallelStream().forEach(request ->
                request.setCompanySize(reverseAuctionRepository.countByrequestId(request.getId())));
        return requestList;
    }

    public List<RequestDetail> getRequestDetailListByRequestId(Long id) {
        return requestDetailRepository.findAllByrequestId(id);
    }

    public void confirmRequest(ConfirmPayLoad confirmPayLoad) {
        System.out.println(confirmPayLoad.getReverseAuctionId());
        Optional<ReverseAuction> reverseAuctionOptional = reverseAuctionRepository.findById(confirmPayLoad.getReverseAuctionId());
        if(reverseAuctionOptional.isEmpty()) return;

        ReverseAuction reverseAuction = reverseAuctionOptional.get();
        if(!reverseAuction.getRequest()
                .equals(requestRepository.findById(confirmPayLoad.getRequestId()).get())) return;

        // 구매 확정
        reverseAuction.setChosen(true);
        // cascade
        reverseAuction.getRequest().setSold(true);
        reverseAuctionRepository.save(reverseAuction);
    }
}