package com.example.canal.processor;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @Author song
 * @Description:
 * @Date 2022/4/19 18:20
 * @Version 1.0
 * com.example.canal.processor
 */

public interface Processor {
    /**
     * 表名
     * @return
     */
    String tableName();

    /**
     * 处理记录数
     * @param entry
     * @return
     */
    int process(CanalEntry.Entry entry);
}
