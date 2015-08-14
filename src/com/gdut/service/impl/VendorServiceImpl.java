package com.gdut.service.impl;

import org.springframework.stereotype.Service;

import com.gdut.base.DaoSupportImpl;
import com.gdut.domain.Vendor;
import com.gdut.service.VendorService;
@Service("vendorService")
public class VendorServiceImpl extends DaoSupportImpl<Vendor> implements VendorService{

}
