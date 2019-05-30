package kr.ac.jejunu.sslab.gongdae.dao;

import kr.ac.jejunu.sslab.gongdae.Request;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDao implements Dao {

    @Override
    public Long add(Object dto) {
        Request request = (Request) dto;

        return null;
    }

    @Override
    public Object get(Long id) {
        return null;
    }

    @Override
    public void update(Object dto) {

    }

    @Override
    public void delete(Long id) {

    }
}
