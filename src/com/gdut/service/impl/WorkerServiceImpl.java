package com.gdut.service.impl;

import org.springframework.stereotype.Service;

import com.gdut.base.DaoSupportImpl;
import com.gdut.domain.Worker;
import com.gdut.service.WorkerService;
@Service("workerService")
public class WorkerServiceImpl extends DaoSupportImpl<Worker> implements WorkerService {

}
