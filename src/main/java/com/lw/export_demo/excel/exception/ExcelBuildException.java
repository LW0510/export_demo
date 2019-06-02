package com.lw.export_demo.excel.exception;

/**
 * created by liuwei
 * date 2019/06/02
 */
public class ExcelBuildException extends RuntimeException {

    public ExcelBuildException(String message) {
        super(message);
    }

    public ExcelBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ExcelBuildException of(String message, Throwable cause) {
        return new ExcelBuildException(message, cause);
    }
}
