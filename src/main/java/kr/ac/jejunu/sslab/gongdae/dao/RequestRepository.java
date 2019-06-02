package kr.ac.jejunu.sslab.gongdae.dao;

import kr.ac.jejunu.sslab.gongdae.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
//    @Query(value = "SELECT r FROM Request r")
//    List<Request> findAll();
    //    @Override
//    public Long add(Request dto) {
//        String sql = "INSERT INTO request (client_id, title, place, vr_image_url) VALUES (?, ?, ?, ?)";
//        Object[] params = new Object[] { dto.getClientId(), dto.getTitle(), dto.getPlace(), dto.getImgUrl() };
//        jdbcTemplate.update(sql, params);
//        return null;

//    }
//    @Override
//    public Request get(Long id) {
//        String sql = "SELECT * from request where id = ?";
//        Object[] params = new Object[]{ id };
//        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> Request.builder()
//                .id(rs.getLong("id"))
//                .clientId(rs.getLong("client_id"))
//                .title(rs.getString("title"))
//                .place(rs.getString("place"))
//                .imgUrl(rs.getString("vr_image_url"))
//                .companySize(rs.getLong("company_size"))
//                .build());
//    }
//
//    @Override
//    public Collection<Request> getAll(Long clientId) {
//        String sql = "SELECT id, title, vr_image_url, company_size from request";
//        Collection<Request> requestCollection = null;
//        try {
//            requestCollection = jdbcTemplate.query(sql, (rs, rowNum) -> Request.builder()
//                    .id(rs.getLong("id"))
//                    .title(rs.getString("title"))
//                    .imgUrl(rs.getString("vr_image_url"))
//                    .companySize(rs.getLong("company_size"))
//                    .build()
//            );
//        } catch (EmptyResultDataAccessException e) { }
//        return requestCollection;
//    }
//
//    @Override
//    public void update(Request dto) {
//
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
}
