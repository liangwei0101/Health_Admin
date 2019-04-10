package com.liangwei.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liangwei.demo.model.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
