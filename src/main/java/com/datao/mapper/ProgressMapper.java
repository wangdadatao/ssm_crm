package com.datao.mapper;

import com.datao.pojo.Progress;

import java.util.List;
import java.util.Map;

/**
 * Created by 海涛 on 2016/5/4.
 */
public interface ProgressMapper {

    Long countByParam(Map<String, Object> param);

    List<Progress> findByParam(Map<String, Object> param);

    void savePro(Progress progress);

    void delProgress(Integer custid);

    List<Progress> findProgressByCusId(Integer custid);
}
