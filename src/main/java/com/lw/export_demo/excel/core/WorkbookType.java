package com.lw.export_demo.excel.core;

/**
 * created by liuwei
 * date 2019/06/02
 */
public enum WorkbookType {
    /**
     * .xls
     */
    XLS,
    /**
     * .xlsx
     */
    XLSX,
    /**
     * .xlsx
     */
    SXLSX;

    public static boolean isXls(WorkbookType workbookType) {
        return XLS.equals(workbookType);
    }

    public static boolean isSxlsx(WorkbookType workbookType) {
        return SXLSX.equals(workbookType);
    }



}
