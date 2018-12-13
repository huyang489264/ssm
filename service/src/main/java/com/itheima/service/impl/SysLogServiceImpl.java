package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.ISysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {



    @Autowired
    private ISysLogDao sysLogDao;
    /**
     * 保存数据到数据库
     * @param sysLog
     * @throws Exception
     */
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    /**
     * 查询所有日志信息
     * @return
     * @throws Exception
     * @param page
     * @param size
     */
    @Override
    public List<SysLog> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
