package com.lw.export_demo.excel.core;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.concurrent.ExecutorService;


/**
 * 简单的流式excel构建器
 *
 * created by liuwei
 * date 2019/06/02
 */
interface SimpleStreamExcelBuilder {

    /**
     * 线程池设置
     *
     * @param executorService 线程池
     * @return SimpleStreamExcelBuilder
     */
    SimpleStreamExcelBuilder threadPool(ExecutorService executorService);

    /**
     * 流式构建启动，包含一些初始化操作
     *
     * @param waitQueueSize 等待队列容量
     * @param groups        分组
     * @return SimpleStreamExcelBuilder
     */
    SimpleStreamExcelBuilder start(int waitQueueSize, Class<?>... groups);

    /**
     * 使用默认样式
     *
     * @return SimpleStreamExcelBuilder
     */
    SimpleStreamExcelBuilder hasStyle();

    /**
     * 数据追加
     *
     * @param data 需要追加的数据
     */
    void append(List<?> data);

    /**
     * 停止追加数据，开始构建
     *
     * @return Workbook
     */
    Workbook build();
}
