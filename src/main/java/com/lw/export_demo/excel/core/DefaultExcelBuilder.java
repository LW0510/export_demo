package com.lw.export_demo.excel.core;

import com.lw.export_demo.excel.core.container.Pair;
import com.lw.export_demo.excel.core.parser.Table;
import com.lw.export_demo.excel.core.parser.Tr;
import com.lw.export_demo.excel.core.reflect.ClassFieldContainer;
import com.lw.export_demo.excel.utils.ReflectUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 默认excel创建者
 *
 * created by liuwei
 * date 2019/06/02
 */
@Slf4j
public class DefaultExcelBuilder extends AbstractSimpleExcelBuilder {

    private static final Log log = LogFactory.getLog(DefaultExcelBuilder.class);

    private Workbook workbook;

    private DefaultExcelBuilder() {
    }

    /**
     * 获取实例，已废弃，请使用of方法代替
     *
     * @return DefaultExcelBuilder
     */
//    @Deprecated
//    public static DefaultExcelBuilder getInstance() {
//        return new DefaultExcelBuilder();
//    }

    /**
     * 获取实例，设定需要渲染的数据的类类型
     *
     * @param dataType 数据的类类型
     * @return DefaultExcelBuilder
     */
    public static DefaultExcelBuilder of(@NonNull Class<?> dataType) {
        DefaultExcelBuilder defaultExcelBuilder = new DefaultExcelBuilder();
        defaultExcelBuilder.dataType = dataType;
        return defaultExcelBuilder;
    }

    public static DefaultExcelBuilder of(@NonNull Class<?> dataType, @NonNull Workbook workbook) {
        DefaultExcelBuilder defaultExcelBuilder = new DefaultExcelBuilder();
        defaultExcelBuilder.dataType = dataType;
        defaultExcelBuilder.workbook = workbook;
        return defaultExcelBuilder;
    }


    @Override
    public Workbook build(List<?> data, Class<?>... groups) {
        HtmlToExcelFactory htmlToExcelFactory = new HtmlToExcelFactory();
        List<Table> tableList = new ArrayList<>();
        if (Objects.isNull(dataType)) {
            if (Objects.isNull(data) || data.isEmpty()) {
                log.info("No valid data exists");
                return htmlToExcelFactory.build(this.getTableWithHeader());
            }
            Optional<?> findResult = data.stream().filter(Objects::nonNull).findFirst();
            if (!findResult.isPresent()) {
                log.info("No valid data exists");
                return htmlToExcelFactory.build(this.getTableWithHeader());
            }
            ClassFieldContainer classFieldContainer = ReflectUtil.getAllFieldsOfClass(findResult.get().getClass());
            List<Field> sortedFields = getFilteredFields(classFieldContainer, groups);

            if (sortedFields.isEmpty()) {
                log.info("The specified field mapping does not exist");
                return htmlToExcelFactory.build(this.getTableWithHeader());
            }
            List<List<Pair<Class, Object>>> contents = getRenderContent(data, sortedFields);

            this.initStyleMap();

            Table table = this.createTable();
            Tr thead = this.createThead();
            if (Objects.nonNull(thead)) {
                table.getTrList().add(thead);
            }
            List<Tr> tbody = this.createTbody(contents, Objects.isNull(thead) ? 0 : 1);
            table.getTrList().addAll(tbody);
            tableList.add(table);
        } else {
            ClassFieldContainer classFieldContainer = ReflectUtil.getAllFieldsOfClass(dataType);
            List<Field> sortedFields = getFilteredFields(classFieldContainer, groups);

            if (sortedFields.isEmpty()) {
                log.info("The specified field mapping does not exist");
                return htmlToExcelFactory.build(Collections.emptyList());
            }

            Table table = this.createTable();
            Tr thead = this.createThead();
            if (Objects.nonNull(thead)) {
                table.getTrList().add(thead);
            }
            tableList.add(table);

            if (Objects.isNull(data) || data.isEmpty()) {
                log.info("No valid data exists");
                return htmlToExcelFactory.build(tableList);
            }

            this.initStyleMap();

            List<List<Pair<Class, Object>>> contents = getRenderContent(data, sortedFields);
            List<Tr> tbody = this.createTbody(contents, Objects.isNull(thead) ? 0 : 1);
            table.getTrList().addAll(tbody);
        }
        htmlToExcelFactory.rowAccessWindowSize(rowAccessWindowSize).workbookType(workbookType).autoWidthStrategy(autoWidthStrategy);
        return htmlToExcelFactory.build(tableList, workbook);
    }
}
