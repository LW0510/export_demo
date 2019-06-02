package com.lw.export_demo.excel.utils;


import com.lw.export_demo.excel.core.constant.Constants;
import com.lw.export_demo.excel.core.io.TempFileOperator;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * 附件导出工具类
 *
 * created by liuwei
 * date 2019/06/02
 */
@UtilityClass
public final class AttachmentExportUtil {
    private static final Log log = LogFactory.getLog(AttachmentExportUtil.class);

    /**
     * 导出
     *
     * @param workbook workbook
     * @param fileName file name,suffix is not required,and it is not recommended to carry a suffix
     * @param response HttpServletResponse
     * @throws IOException IOException
     */
    public static void export(Workbook workbook, String fileName, HttpServletResponse response) throws IOException {
        String suffix = Constants.XLS;

        if (workbook instanceof HSSFWorkbook) {
            if (fileName.endsWith(suffix)) {
                fileName = fileName.substring(0, fileName.length() - 1);
            }
            suffix = Constants.XLS;
        }
        if (!fileName.endsWith(suffix)) {
            fileName += suffix;
        }
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.setContentType("multipart/form-data");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, CharEncoding.UTF_8));
        workbook.write(response.getOutputStream());
        if (workbook instanceof SXSSFWorkbook) {
            ((SXSSFWorkbook) workbook).dispose();
        }
        workbook.close();
    }

    /**
     * 加密导出
     *
     * @param workbook workbook
     * @param fileName fileName
     * @param response response
     * @param password password
     * @throws Exception Exception
     */
    public static void encryptExport(final Workbook workbook, String fileName, HttpServletResponse response, final String password) throws Exception {
        if (workbook instanceof HSSFWorkbook) {
            throw new IllegalArgumentException("Document encryption for.xls is not supported");
        }
        TempFileOperator tempFileOperator = null;
        try {
            tempFileOperator = new TempFileOperator();
            String suffix = Constants.XLSX;
            Path path = tempFileOperator.createTempFile("encrypt_temp", suffix);
            workbook.write(Files.newOutputStream(path));
            if (workbook instanceof SXSSFWorkbook) {
                ((SXSSFWorkbook) workbook).dispose();
            }
            workbook.close();

            final POIFSFileSystem fs = new POIFSFileSystem();
            final EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            final Encryptor enc = info.getEncryptor();
            enc.confirmPassword(password);

            try (OPCPackage opc = OPCPackage.open(path.toFile(), PackageAccess.READ_WRITE);
                 OutputStream os = enc.getDataStream(fs)) {
                opc.save(os);
            }
            if (!fileName.endsWith(suffix)) {
                fileName += suffix;
            }
            response.setCharacterEncoding(CharEncoding.UTF_8);
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, CharEncoding.UTF_8));
            fs.writeFilesystem(response.getOutputStream());
        } finally {
            if (Objects.nonNull(tempFileOperator)) {
                tempFileOperator.deleteTempFile();
            }
        }
    }
}
