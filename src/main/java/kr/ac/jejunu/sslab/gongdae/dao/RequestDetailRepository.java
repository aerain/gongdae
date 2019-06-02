package kr.ac.jejunu.sslab.gongdae.dao;

import kr.ac.jejunu.sslab.gongdae.model.RequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RequestDetailRepository extends JpaRepository<RequestDetail, Long> {
//    @Override
//    public Long add(RequestSub requestSub) {
//        return null;
//    }
//
//    @Override
//    public RequestSub get(Long id) {
//        return null;
//    }
//
//    @Override
//    public Collection<RequestSub> getAll(Long requestId) {
//        return null;
//    }
//
//    @Override
//    public void update(RequestSub requestSub) {
//
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
}
