package com.imooc.springbootlearn;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 学生Mapper
 */

@Mapper
@Repository
public interface StudentMapper {
        @Select("select * from employee where eno= #{id}")
        Student findById(Integer id);

}
