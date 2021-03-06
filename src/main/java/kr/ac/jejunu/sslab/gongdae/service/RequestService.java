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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final FileUploadService fileUploadService;
    private final RequestRepository requestRepository;
    private final RequestDetailRepository requestDetailRepository;
    private final ReverseAuctionRepository reverseAuctionRepository;
    private final UserService userService;
    private final CompanyRepository companyRepository;

    public Request getRequestById(Long id) throws IllegalAccessException {
        return Optional.of(requestRepository.findById(id).get()).map(request -> {
            request.setRequestDetailList(requestDetailRepository.findAllByrequestId(id));
            request.setCompanySize(reverseAuctionRepository.countByrequestId(id));
            // if Admin
            if(userService.getCurrentUser().getType() == 1) {
                request.setIsAlreadySubmit(reverseAuctionRepository.existsBycompanyIdAndRequestId(userService.getCurrentSessionId(), request.getId()));
            }
            return request;
        }).orElseThrow(IllegalAccessException::new);
    }

    public void saveRequest(String title, String place, MultipartFile vrImgUrl, String requestList) throws IOException {
        List<RequestDetail> requestDetailList = new Gson().fromJson(requestList, new TypeToken<List<RequestDetail>>(){}.getType());
        String imagePath = fileUploadService.uploadFile(vrImgUrl);

        Request req = Request.builder()
                .title(title)
                .place(place)
                .imgUrl(imagePath)
                .member(userService.getCurrentUser()).build();

        // cascade
        requestDetailList.parallelStream().forEach(requestDetail ->
                requestDetail.setRequest(req));

        // 리퀘스트 저장
        requestRepository.save(req);
        // 요구사항 저장
        requestDetailRepository.saveAll(requestDetailList);
    }

    public List<Request> getRequestListByUserId(Long clientId, boolean sold) {
        List<Request> requestList = requestRepository.findAllOnProgressByuserId(clientId, sold);
        requestList.parallelStream().forEach(request ->
                request.setCompanySize(reverseAuctionRepository.countByrequestId(request.getId())));
        return requestList;
    }

    public List<RequestDetail> getRequestDetailListByRequestId(Long id) {
        return requestDetailRepository.findAllByrequestId(id);
    }

    public void confirmRequest(ConfirmPayLoad confirmPayLoad) {
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

    public List<Request> getRequestSubmitList(boolean chosen) {
        return reverseAuctionRepository.findAllBycompanyIdAndChosen(userService.getCurrentSessionId(), chosen).stream().map(ReverseAuction::getRequest).collect(Collectors.toList());
    }
    public List<Request> getRequestList(boolean done) {
        return requestRepository.findAllOnProgress(false);
    }
}