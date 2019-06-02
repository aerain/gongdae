package kr.ac.jejunu.sslab.gongdae.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.ac.jejunu.sslab.gongdae.dao.RequestRepository;
import kr.ac.jejunu.sslab.gongdae.model.Request;
import kr.ac.jejunu.sslab.gongdae.model.RequestDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private FileUploadService fileUploadService;
    private RequestRepository requestRepository;
    public void saveRequest(String title, String place, MultipartFile vrImgUrl, String requestList) throws IOException {
        String imagePath = fileUploadService.uploadFile(vrImgUrl);
        Request req = Request.builder().title(title).place(place).imgUrl(imagePath).clientId(1L).companySize(0L).build();
        requestRepository.save(req);

        Type listType = new TypeToken<List<RequestDetail>>(){}.getType();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(requestList);
    }
}