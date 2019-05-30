package kr.ac.jejunu.sslab.gongdae.dao;

public interface Dao {
    public Long add(Object dto);
    public Object get(Long id);
    public void update(Object dto);
    public void delete(Long id);
}
