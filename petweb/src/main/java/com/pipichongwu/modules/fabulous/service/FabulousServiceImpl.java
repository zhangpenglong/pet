package com.pipichongwu.modules.fabulous.service;

import com.pipichongwu.modules.fabulous.dao.FabulousMapper;
import com.pipichongwu.modules.fabulous.model.Fabulous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dongao on 2018/1/31.
 */
@Service
public class FabulousServiceImpl implements FabulousService {
    @Autowired
    private FabulousMapper fabulousMapper;
    @Override
    public void addFabu(Fabulous fabulous) {
        fabulousMapper.addFabu(fabulous);
    }

    @Override
    public Fabulous selectByUserId(Fabulous fabulous) {
        return fabulousMapper.selectByUserId(fabulous);
    }

    @Override
    public Integer countFabu(Integer stickId) {
        return fabulousMapper.countFabu(stickId);
    }

    @Override
    public Fabulous selectByComId(Fabulous fabulous){
        return fabulousMapper.selectByComId(fabulous);
    }

    @Override
    public Integer countFabuByComId(Integer stickId){
        return fabulousMapper.countFabuByComId(stickId);
    }
}
