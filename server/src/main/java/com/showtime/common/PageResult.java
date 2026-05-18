package com.showtime.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * 分页返回类
 *
 * Controller 返回分页数据时统一使用此类。
 */
@Data
public class PageResult<T> {

    private List<T> records;
    private long total;
    private long page;
    private long pageSize;

    private PageResult(List<T> records, long total, long page, long pageSize) {
        this.records = records;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
    }

    /** 从 MyBatis-Plus 分页结果构建 */
    public static <T> PageResult<T> from(IPage<T> page) {
        return new PageResult<>(
            page.getRecords(),
            page.getTotal(),
            page.getCurrent(),
            page.getSize()
        );
    }

    /** 手动构建 */
    public static <T> PageResult<T> of(List<T> records, long total, long page, long pageSize) {
        return new PageResult<>(records, total, page, pageSize);
    }
}
