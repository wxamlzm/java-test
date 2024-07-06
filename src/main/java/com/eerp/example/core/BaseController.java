/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eerp.example.core;

import com.eerp.example.core.cnostant.HttpStatus;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;


import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BaseController {

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        PageInfo pageInfo = new PageInfo(list);
        rspData.setTotal(pageInfo.getTotal());
        rspData.setTotalPage(pageInfo.getPages());
        rspData.setPageNum(pageInfo.getPageNum());
        rspData.setPageSize(pageInfo.getPageSize());
        return rspData;
    }
}
