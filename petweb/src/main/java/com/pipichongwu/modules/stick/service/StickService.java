package com.pipichongwu.modules.stick.service;

import com.pipichongwu.modules.stick.model.Stick;

import java.util.List;

/**
 * Created by dongao on 2018/1/27.
 */
public interface StickService {

    List<Stick> selectByPage(Stick stick);

    Stick selectById(Stick stick);
    void insertStick(Stick stick);

}
