package com.datao.service;

import com.datao.mapper.CustomerMapper;
import com.datao.mapper.ProgressFileMapper;
import com.datao.mapper.ProgressMapper;
import com.datao.pojo.Customer;
import com.datao.pojo.Progress;
import com.datao.pojo.ProgressFile;
import com.datao.util.Page;
import com.datao.util.QiniuUtil;
import com.datao.util.ShiroUtil;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by 海涛 on 2016/5/4.
 */
@Named
public class ProgressService {
    @Inject
    private ProgressMapper progressMapper;

    @Inject
    private ProgressFileMapper progressFileMapper;

    @Inject
    private CustomerMapper customerMapper;

    @Inject
    private QiniuUtil qiniuUtil;

    @Value("${qiniu.domain}")
    private String qiniuUrl;


    /**
     * 根据参数查询相应的结果
     *
     * @param userid
     * @param progress
     * @param date
     * @param context
     * @param p
     * @return
     */
    public Page<Progress> findByParam(String userid, String progress, String date, String context, String p) {
        Map<String, Object> param = Maps.newHashMap();

        param.put("userid", userid);
        param.put("progress", progress);

        if (context != null && context.length() > 0) {
            param.put("context", "%" + context + "%");
        }

        DateTime dateTime = new DateTime();
        if ("今天".equals(date)) {
            param.put("date", dateTime.toString("yyyy-MM-dd"));
        } else if ("昨天".equals(date)) {
            dateTime = dateTime.minusDays(1);
            param.put("date", dateTime.toString("yyyy-MM-dd"));
        } else if ("本周".equals(date)) {
            LocalDate localDate = new LocalDate();

            localDate = localDate.withDayOfWeek(DateTimeConstants.MONDAY);
            param.put("date", localDate.toString("yyyy-MM-dd"));
        } else if ("本月".equals(date)) {
            LocalDate localDate = new LocalDate();

            localDate = localDate.dayOfMonth().withMinimumValue();
            param.put("date", localDate.toString("yyyy-MM-dd"));
        } else if ("更早".equals(date)) {
            LocalDate localDate = new LocalDate();

            localDate = localDate.dayOfMonth().withMinimumValue();
            param.put("date", localDate.toString("yyyy-MM-dd"));
        }

        int count = progressMapper.countByParam(param).intValue();

        Page<Progress> page = new Page<>(p, count, 10);

        param.put("start", page.getStart());
        param.put("size", page.getSize());

        List<Progress> progresses = progressMapper.findByParam(param);

        page.setItems(progresses);

        return page;
    }

    /**
     * 新增跟进记录
     *
     * @param progress
     * @param file
     */
    public void saveNewProgress(Progress progress, MultipartFile[] file) {

        Customer customer = customerMapper.findByCustomerId(progress.getCustid());
        customer.setProgress(progress.getProgress());
        customer.setProgresstime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        customerMapper.update(customer);

        progress.setUserid(ShiroUtil.getCurrentUserId());
        progress.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        progressMapper.savePro(progress);


        //判断文件集合是否有文件
        if (file != null && file.length > 0) {
            for (MultipartFile multipartFile : file) {
                if (!multipartFile.isEmpty()) {
                    String key = null;
                    try {
                        key = qiniuUtil.uploadFile(multipartFile.getInputStream());
                    } catch (IOException e) {
                        throw new RuntimeException("获取inputstream异常");
                    }

                    key = qiniuUrl + key.substring(4);


                    ProgressFile progressFile = new ProgressFile();

                    progressFile.setCreatetime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
                    progressFile.setUserid(ShiroUtil.getCurrentUserId());
                    progressFile.setCustid(progress.getCustid());
                    progressFile.setFilename(multipartFile.getOriginalFilename());
                    progressFile.setPath(key);
                    progressFile.setProgressid(progress.getId());

                    progressFileMapper.save(progressFile);
                }
            }
        }
    }

    /**
     * 查询某个客户的所有跟进记录
     *
     * @param id
     * @return
     */
    public List<Progress> findProgressByCustId(Integer id) {
        return progressMapper.findProgressByCusId(id);
    }

    /**
     * 查找某个用户的所有相关文件
     *
     * @param id
     * @return
     */
    public List<ProgressFile> findProgressFileByCustId(Integer id) {

        return progressFileMapper.findFileByCustId(id);
    }
}
