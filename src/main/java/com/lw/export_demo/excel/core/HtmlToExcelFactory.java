package com.lw.export_demo.excel.core;

import com.lw.export_demo.excel.core.parser.HtmlTableParser;
import com.lw.export_demo.excel.core.parser.ParseConfig;
import com.lw.export_demo.excel.core.parser.Table;
import com.lw.export_demo.excel.core.parser.Tr;
import com.lw.export_demo.excel.utils.StringUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * HtmlToExcelFactory
 * 用于将html table解析成excel
 *
 * created by liuwei
 * date 2019/06/02
 */
@Slf4j
public class HtmlToExcelFactory extends AbstractExcelFactory {

    private static final Log log = LogFactory.getLog(HtmlToExcelFactory.class);

    private HtmlTableParser htmlTableParser;

    /**
     * 读取html
     *
     * @param htmlFile html文件
     * @return HtmlToExcelFactory
     * @throws Exception 解析异常
     */
    public static HtmlToExcelFactory readHtml(File htmlFile) throws Exception {
        if (Objects.isNull(htmlFile) || !htmlFile.exists()) {
            throw new NoSuchFileException("html file is not exist");
        }
        HtmlToExcelFactory factory = new HtmlToExcelFactory();
        factory.htmlTableParser = HtmlTableParser.of(htmlFile);
        return factory;
    }

    /**
     * 读取html
     *
     * @param html html字符串
     * @return HtmlToExcelFactory
     */
    public static HtmlToExcelFactory readHtml(@NonNull String html) {
        HtmlToExcelFactory factory = new HtmlToExcelFactory();
        factory.htmlTableParser = HtmlTableParser.of(html);
        return factory;
    }

    /**
     * 读取html
     *
     * @param htmlFile           html文件
     * @param htmlToExcelFactory 实例对象
     * @return HtmlToExcelFactory
     * @throws Exception 解析异常
     */
    public static HtmlToExcelFactory readHtml(File htmlFile, HtmlToExcelFactory htmlToExcelFactory) throws Exception {
        if (Objects.isNull(htmlFile) || !htmlFile.exists()) {
            throw new NoSuchFileException("Html file is not exist");
        }
        if (Objects.isNull(htmlToExcelFactory)) {
            throw new NullPointerException("HtmlToExcelFactory can not be null");
        }
        htmlToExcelFactory.htmlTableParser = HtmlTableParser.of(htmlFile);
        return htmlToExcelFactory;
    }

    /**
     * 读取html
     *
     * @param html               html内容
     * @param htmlToExcelFactory 实例对象
     * @return HtmlToExcelFactory
     * @throws Exception 解析异常
     */
    public static HtmlToExcelFactory readHtml(String html, HtmlToExcelFactory htmlToExcelFactory) throws Exception {
        if (StringUtil.isBlank(html)) {
            throw new IllegalArgumentException("Html content is empty");
        }
        if (Objects.isNull(htmlToExcelFactory)) {
            throw new NullPointerException("HtmlToExcelFactory can not be null");
        }
        htmlToExcelFactory.htmlTableParser = HtmlTableParser.of(html);
        return htmlToExcelFactory;
    }

    /**
     * 开始构建
     *
     * @return Workbook
     */
    @Override
    public Workbook build() {
        try {
            ParseConfig parseConfig = new ParseConfig();
            parseConfig.setAutoWidthStrategy(autoWidthStrategy);

            List<Table> tables = htmlTableParser.getAllTable(parseConfig);
            return this.build(tables);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 开始构建
     *
     * @param tables   tables
     * @param workbook workbook
     * @return Workbook
     */
    Workbook build(List<Table> tables, Workbook workbook) {
        if (Objects.nonNull(workbook)) {
            this.workbook = workbook;
        }
        return build(tables);
    }

    /**
     * 开始构建
     *
     * @param tables tables
     * @return Workbook
     */
    Workbook build(List<Table> tables) {
        if (Objects.isNull(tables) || tables.isEmpty()) {
            log.warn("There is no any table exist");
            return emptyWorkbook();
        }
        log.info("Start building excel");
        long startTime = System.currentTimeMillis();
        // 1、创建工作簿
        if (Objects.isNull(workbook)) {
            workbook = new XSSFWorkbook();
        }
        this.initCellStyle(workbook);
        // 2、处理解析表格
        for (int i = 0, size = tables.size(); i < size; i++) {
            Table table = tables.get(i);
            String sheetName = Objects.isNull(table.getCaption()) || table.getCaption().length() < 1 ? "Sheet" + (i + 1) : table.getCaption();
            Sheet sheet = workbook.createSheet(sheetName);

            boolean hasTd = table.getTrList().stream().map(Tr::getTdList).anyMatch(list -> !list.isEmpty());
            if (!hasTd) {
                continue;
            }
            // 设置单元格样式
            this.setTdOfTable(table, sheet);
            this.freezePane(i, sheet);
        }
//     补充：   log.info("Build excel takes {} ms", System.currentTimeMillis() - startTime);
        return workbook;
    }

    /**
     * 设置所有单元格，自适应列宽，单元格最大支持字符长度255
     */
    private void setTdOfTable(Table table, Sheet sheet) {
        Map<Integer, Integer> colMaxWidthMap = this.getColMaxWidthMap(table.getTrList());
        for (int i = 0, size = table.getTrList().size(); i < size; i++) {
            this.createRow(table.getTrList().get(i), sheet);
            table.getTrList().set(i, null);
        }
        this.setColWidth(colMaxWidthMap, sheet);
    }

}
