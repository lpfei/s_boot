package com.example.core.util.page;

import com.example.core.util.http.HttpRequestUtil;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * description:
 * Created by lpfei on 2019/8/15
 */
@Data
public class PageModel {

    private static final int defaultPage = 1;
    private static final int defaultSize = 20;
    private static final int MaxSize = 100;
    private Integer pageNum;
    private Integer pageSize;

    PageModel(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public static PageModel init() {
        int pageNum = defaultPage;
        int pageSize = defaultSize;
        HttpServletRequest request = HttpRequestUtil.getRequest();
        if (request.getParameter("pageNum") != null) {
            pageNum = Integer.valueOf(request.getParameter("pageNum")).intValue();
        }

        if (request.getParameter("pageSize") != null) {
            pageNum = Integer.valueOf(request.getParameter("pageSize")).intValue();
        }
        return init(pageNum, pageSize);
    }

    public static PageModel init(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 1) {
            pageNum = defaultPage;
        }
        if (pageSize == null || pageSize > MaxSize) {
            pageSize = defaultSize;
        }
        return new PageModel(pageNum, pageSize);
    }


}
