package com.lw.export_demo.excel.exception;

/**
 * sax读取异常
 *
 * created by liuwei
 * date 2019/06/02
 */
public class SaxReadException extends RuntimeException {

    public SaxReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
