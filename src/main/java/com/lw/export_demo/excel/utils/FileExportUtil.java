package com.lw.export_demo.excel.utils;


import com.lw.export_demo.excel.core.constant.Constants;
import com.lw.export_demo.excel.core.io.TempFileOperator;
import lombok.experimental.UtilityClass;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件导出工具类
 *
 * created by liuwei
 * date 2019/06/02
 */
@UtilityClass
public final class FileExportUtil {

    /**
     * 导出
     *
     * @param workbook workbook
     * @param file     file
     * @throws IOException IOException
     */
    public static void export(Workbook workbook, File file) throws IOException {
        String suffix = Constants.XLSX;
        if (workbook instanceof HSSFWorkbook) {
            if (file.getName().endsWith(suffix)) {
                String absolutePath = file.getAbsolutePath();
                file = Paths.get(absolutePath.substring(0, absolutePath.length() - 1)).toFile();
            }
            suffix = Constants.XLS;
        }
        if (!file.getName().endsWith(suffix)) {
            file = Paths.get(file.getAbsolutePath() + suffix).toFile();
        }
        try (OutputStream os = new FileOutputStream(file)) {
            workbook.write(os);
        } finally {
            if (workbook instanceof SXSSFWorkbook) {
                ((SXSSFWorkbook) workbook).dispose();
            }
            workbook.close();
        }
    }

    /**
     * 加密导出
     *
     * @param workbook workbook
     * @param file     file
     * @param password password
     * @throws Exception Exception
     */
    public static void encryptExport(final Workbook workbook, File file, final String password) throws Exception {
        if (workbook instanceof HSSFWorkbook) {
            throw new IllegalArgumentException("Document encryption for.xls is not supported");
        }
        String suffix = Constants.XLSX;
        if (!file.getName().endsWith(suffix)) {
            file = Paths.get(file.getAbsolutePath() + suffix).toFile();
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
            if (workbook instanceof SXSSFWorkbook) {
                ((SXSSFWorkbook) workbook).dispose();
            }
            workbook.close();

            final POIFSFileSystem fs = new POIFSFileSystem();
            final EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            final Encryptor enc = info.getEncryptor();
            enc.confirmPassword(password);

            try (OPCPackage opc = OPCPackage.open(file, PackageAccess.READ_WRITE);
                 OutputStream os = enc.getDataStream(fs)) {
                opc.save(os);
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fs.writeFilesystem(fileOutputStream);
            }
        }
    }

    /**
     * 获取workbook输入流
     *
     * @param workbook workbook
     * @return InputStream
     */
    public static InputStream getInputStream(final Workbook workbook) {
        TempFileOperator tempFileOperator = new TempFileOperator();
        String suffix = Constants.XLSX;
        if (workbook instanceof HSSFWorkbook) {
            suffix = Constants.XLS;
        }
        Path path = tempFileOperator.createTempFile("tem_outs", suffix);
        try {
            workbook.write(Files.newOutputStream(path));
            if (workbook instanceof SXSSFWorkbook) {
                ((SXSSFWorkbook) workbook).dispose();
            }
            workbook.close();
            return Files.newInputStream(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            tempFileOperator.deleteTempFile();
        }
    }
}
