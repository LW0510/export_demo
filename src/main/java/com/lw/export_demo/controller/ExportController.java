package com.lw.export_demo.controller;

import com.github.liaochong.myexcel.core.DefaultExcelBuilder;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import com.lw.export_demo.entity.ArtCrowd;
import com.lw.export_demo.service.ExportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/testExport")
public class ExportController {

    @Autowired
    private ExportService exportService ;

    @RequestMapping("export")
    public void export(){
        exportService.export();
    }

    @GetMapping("/default/excel/example")
    public void defaultBuild(HttpServletResponse response) throws Exception {
        List<ArtCrowd> dataList = this.getDataList();
        Workbook workbook = DefaultExcelBuilder.of(ArtCrowd.class)
                .build(dataList);
        AttachmentExportUtil.export(workbook, "艺术生信息", response);
        // 加密导出AttachmentExportUtil.encryptExport(workbook, "艺术生信息", response,"123456");

    }

//    /**
//     * 方式二：生产者消费者模式导出，分批获取数据，分批写入Excel，真正意义上实现海量数据导出
//     */
//    @GetMapping("/default/excel/stream/example")
//    public void streamBuild(HttpServletResponse response) throws Exception {
//        // 显式标明开始构建
//        DefaultStreamExcelBuilder defaultExcelBuilder = DefaultStreamExcelBuilder.of(ArtCrowd.class)
//                .threadPool(Executors.newFixedThreadPool(10))
//                .start();
//        // 多线程异步获取数据并追加至excel，join等待线程执行完成
//        List<CompletableFuture> futures = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            CompletableFuture future = CompletableFuture.runAsync(() -> {
//                List<ArtCrowd> dataList = this.getDataList();
//                // 数据追加
//                defaultExcelBuilder.append(dataList);
//            });
//            futures.add(future);
//        }
//        futures.forEach(CompletableFuture::join);
//        // 最终构建
//        Workbook workbook = defaultExcelBuilder.build();
//        AttachmentExportUtil.export(workbook, "艺术生信息", response);
//    }


    // 数据获取
    private List<ArtCrowd> getDataList() {
        List<ArtCrowd> dataList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            ArtCrowd artCrowd = new ArtCrowd();
            if (i % 2 == 0) {
                artCrowd.setName("张三");
                artCrowd.setAge(19);
                artCrowd.setGender("Man");
                artCrowd.setPaintingLevel("一级证书");
                artCrowd.setDance(false);
                artCrowd.setAssessmentTime(LocalDateTime.now());
                artCrowd.setHobby("摔跤");
            } else {
                artCrowd.setName("李四");
                artCrowd.setAge(18);
                artCrowd.setGender("Woman");
                artCrowd.setPaintingLevel("一级证书");
                artCrowd.setDance(true);
                artCrowd.setAssessmentTime(LocalDateTime.now());
                artCrowd.setHobby("钓鱼");
            }
            dataList.add(artCrowd);
        }
        return dataList;
    }
}
