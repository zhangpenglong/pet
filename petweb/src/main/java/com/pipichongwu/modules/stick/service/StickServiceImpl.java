package com.pipichongwu.modules.stick.service;

import com.pipichongwu.modules.stick.dao.StickMapper;
import com.pipichongwu.modules.stick.model.Stick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dongao on 2018/1/27.
 */
@Service
public class StickServiceImpl implements StickService {

    @Autowired
    private StickMapper stickMapper;

    @Override
    public List<Stick> selectByPage(Stick stick) {
        Integer integer = stickMapper.selectByPageCount(stick);
        if(null == integer || integer == 0){
            return null;
        }else{
            stick.setTotalCount(integer);
            return stickMapper.selectByPage(stick);
        }
    }

    @Override
    public Stick selectById(Stick stick) {
        return stickMapper.selectById(stick);
    }

    public   void insertStick(Stick stick){
        stickMapper.insertStick(stick);
    }
}
