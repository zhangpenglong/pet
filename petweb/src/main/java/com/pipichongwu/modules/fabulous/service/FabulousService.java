package com.pipichongwu.modules.fabulous.service;

import com.pipichongwu.modules.fabulous.model.Fabulous;

/**
 * Created by dongao on 2018/1/31.
 */
public interface FabulousService {
    void addFabu(Fabulous fabulous);
    Fabulous selectByUserId(Fabulous fabulous);
    Fabulous selectByComId(Fabulous fabulous);

    Integer countFabu(Integer stickId);
    Integer countFabuByComId(Integer stickId);

}
