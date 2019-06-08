package kr.ac.jejunu.sslab.gongdae.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.ac.jejunu.sslab.gongdae.repository.RequestDetailRepository;
import kr.ac.jejunu.sslab.gongdae.repository.RequestRepository;
import kr.ac.jejunu.sslab.gongdae.repository.UserRepository;
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
    private final UserRepository userRepository;

    public Request getRequestById(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        if(!request.isPresent()) return null;

        Request presentRequest = request.get();
        presentRequest.setCompanySize((long) presentRequest.getReverseAuctionList().size());
        return presentRequest;
    }

    public void saveRequest(String title, String place, MultipartFile vrImgUrl, String requestList) throws IOException {
        List<RequestDetail> requestDetailList = new Gson().fromJson(requestList, new TypeToken<List<RequestDetail>>(){}.getType());
        String imagePath = fileUploadService.uploadFile(vrImgUrl);

        // Todo 계정 연동
        String name = "청길";
        Request req = Request.builder()
                .title(title)
                .place(place)
                .imgUrl(imagePath)
                .user(userRepository.findByName(name)).companySize(0L).build();
        requestDetailList.parallelStream().forEach(requestDetail -> {
            requestDetail.setRequest(req);
        });
        req.setRequestDetailList(requestDetailList);
        requestRepository.save(req);
    }

    public List<Request> getRequestListByUserId(Long clientId) {
        List<Request> requestList = requestRepository.findAllByuserId(clientId);
        requestList.parallelStream().forEach(request -> {
            request.setCompanySize((long) request.getReverseAuctionList().size());
        });
        return requestList;
    }

    public List<RequestDetail> getRequestDetailListByRequestId(Long id) {
        return requestDetailRepository.findAllByrequestId(id);
    }
}