package com.fast.monitorserver.service;

import com.fast.bpserver.base.IService;
import com.fast.monitorserver.entity.RsPcUser;

/**
 * Created by Nelson on 2020/1/10.
 */
public interface IRsPcUser extends IService<RsPcUser>{
    RsPcUser findByEnvType();
}
