package com.lw.export_demo.excel.core.io;

import com.lw.export_demo.excel.exception.ExcelBuildException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 临时文件操作类
 *
 * created by liuwei
 * date 2019/06/02
 */
@Slf4j
public class TempFileOperator {

    private static final Log log = LogFactory.getLog(TempFileOperator.class);

    public static final String HTML_SUFFIX = ".html";

    private static final int MAX_CREATE_NO = 9_999;

    private static int createNo;

    private static Path templateDir;

    private Path templateFile;

    static {
        try {
            templateDir = Paths.get(new File("").getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 依据前缀名称创建临时文件
     *
     * @param prefix 临时文件前缀
     * @param suffix 临时文件后缀
     * @return Path
     */
    public Path createTempFile(String prefix, String suffix) {
        try {
            templateFile = Files.createTempFile(templateDir, this.getTempFileName(prefix), suffix);
            return templateFile;
        } catch (IOException e) {
            throw ExcelBuildException.of("Failed to create temp file", e);
        }
    }

    /**
     * 获取临时文件名称
     *
     * @param prefix 文件前缀
     * @return 文件名称
     */
    private String getTempFileName(String prefix) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this) {
            if (createNo > MAX_CREATE_NO) {
                createNo = 0;
            }
            createNo++;
        }
        return prefix + "_" + Thread.currentThread().getId() + "_" + currentTimeMillis + "_" + createNo;
    }

    /**
     * 删除临时文件
     */
    public void deleteTempFile() {
        try {
            Files.deleteIfExists(templateFile);
        } catch (IOException e) {
            log.warn("Delete temp file failure");
        }
    }

}
