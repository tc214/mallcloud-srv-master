package org.tc.core.support;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IService<T> {

    List<T> select(T record);

    T selectByKey(Object key);

    List<T> selectAll();

    T selectOne(T record);

    int selectCount(T record);

    int save(T record);

    @Transactional(rollbackFor = Exception.class)
    int batchSave(List<T> list);

    int update(T entity);

    int delete(T record);

    @Transactional(rollbackFor = Exception.class)
    int batchDelete(List<T> list);

    int deleteByKey(Object key);

    List<T> selectByExample(Object example);

    int selectCountByExample(Object example);

    int updateByExample(@Param("record") T record, @Param("example") Object example);

    int deleteByExample(Object example);

    List<T> selectByRowBounds(T record, RowBounds rowBounds);

    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds);


}
