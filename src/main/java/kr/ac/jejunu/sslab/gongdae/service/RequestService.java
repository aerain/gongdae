package kr.ac.jejunu.sslab.gongdae.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.ac.jejunu.sslab.gongdae.dao.RequestDetailRepository;
import kr.ac.jejunu.sslab.gongdae.dao.RequestRepository;
import kr.ac.jejunu.sslab.gongdae.dao.UserRepository;
import kr.ac.jejunu.sslab.gongdae.model.Request;
import kr.ac.jejunu.sslab.gongdae.model.RequestDetail;
import kr.ac.jejunu.sslab.gongdae.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final FileUploadService fileUploadService;
    private final RequestRepository requestRepository;
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

    public List<Request> getRequestListByClientId(Long clientId) {
        List<Request> requestList = requestRepository.findAllByclientId(clientId);
        requestList.parallelStream().forEach(request -> {
            request.setCompanySize((long) request.getReverseAuctionList().size());
        });
        return requestList;
    }
}