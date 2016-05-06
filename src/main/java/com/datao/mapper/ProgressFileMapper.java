package com.datao.mapper;

import com.datao.pojo.ProgressFile;

import java.util.List;

/**
 * Created by 海涛 on 2016/5/4.
 */
public interface ProgressFileMapper {

    List<ProgressFile> findByProgressId(Integer progressid);

    void save(ProgressFile progressFile);

    void delProgressFile(Integer custid);

    List<ProgressFile> findFileByCustId(Integer custid);
}
