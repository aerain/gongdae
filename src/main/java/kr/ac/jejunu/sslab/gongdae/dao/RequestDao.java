package kr.ac.jejunu.sslab.gongdae.dao;

import kr.ac.jejunu.sslab.gongdae.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class RequestDao implements Dao<Request> {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public RequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(Request dto) {
        String sql = "INSERT INTO request (client_id, title, place, vr_image_url) VALUES (?, ?, ?, ?)";
        Object[] params = new Object[] { request.getClientId(), request.getTitle(), request.getPlace(), request.getVrImgPath() };
        jdbcTemplate.update(sql, params);
        return null;
    }

    @Override
    public Request get(Long id) {
        return null;
    }

    @Override
    public Collection<Request> getAll() {
        return null;
    }

    @Override
    public void update(Request dto) {

    }

    @Override
    public void delete(Long id) {

    }
}
