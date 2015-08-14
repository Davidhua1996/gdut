package com.gdut.service.impl;

import org.springframework.stereotype.Service;
import com.gdut.base.DaoSupportImpl;
import com.gdut.domain.PageBean;
import com.gdut.domain.Student;
import com.gdut.service.StudentService;
import com.gdut.util.QueryHelper;
@Service("studentService")
public class StudentServiceImpl extends DaoSupportImpl<Student> implements StudentService{
}
