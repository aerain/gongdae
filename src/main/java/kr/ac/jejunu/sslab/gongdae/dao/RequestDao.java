package kr.ac.jejunu.sslab.gongdae.dao;

import kr.ac.jejunu.sslab.gongdae.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        Object[] params = new Object[] { dto.getClientId(), dto.getTitle(), dto.getPlace(), dto.getImgUrl() };
        jdbcTemplate.update(sql, params);
        return null;
    }

    @Override
    public Request get(Long id) {
        return null;
    }

    @Override
    public Collection<Request> getAll() {
        String sql = "SELECT id, title, vr_image_url, company_size from request";
        Collection<Request> requestCollection = null;
        try {
            requestCollection = jdbcTemplate.query(sql, (rs, rowNum) -> Request.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .imgUrl(rs.getString("vr_image_url"))
                    .companySize(rs.getLong("company_size"))
                    .build()
            );
        } catch (EmptyResultDataAccessException e) { }
        return requestCollection;
    }

    @Override
    public void update(Request dto) {

    }

    @Override
    public void delete(Long id) {

    }
}
