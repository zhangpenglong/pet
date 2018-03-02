package com.pipichongwu.modules.stick.dao;

import com.pipichongwu.modules.stick.model.Stick;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dongao on 2018/1/27.
 */
@Repository
public interface StickMapper {

    Integer selectByPageCount(Stick stick);

    List<Stick> selectByPage(Stick stick);

    Stick selectById(Stick stick);

    void insertStick(Stick stick);
}
