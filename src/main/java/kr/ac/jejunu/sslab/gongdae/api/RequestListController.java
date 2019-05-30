package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.Request;
import kr.ac.jejunu.sslab.gongdae.dao.RequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/request-list")
public class RequestListController {

    private final RequestDao requestDao;

    @Autowired
    public RequestListController(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @GetMapping
    public Map<String, Object> getRequestList() {
        Collection<Request> requests = requestDao.getAll();
        return new HashMap<>() {{
            put("data", requests);
        }};
    }
}
