package com.pipichongwu.modules.fabulous.dao;

import com.pipichongwu.modules.fabulous.model.Fabulous;
import org.springframework.stereotype.Repository;

/**
 * Created by dongao on 2018/1/31.
 */
@Repository
public interface FabulousMapper {

    void addFabu(Fabulous fabulous);

    Fabulous selectByUserId(Fabulous fabulous);
    Fabulous selectByComId(Fabulous fabulous);

    Integer countFabu(Integer stickId);
    Integer countFabuByComId(Integer stickId);
}
