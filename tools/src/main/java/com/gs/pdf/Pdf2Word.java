package com.gs.pdf;

import com.gs.Constants;
import com.gs.exception.ToolException;
import com.gs.utils.DateUtils;
import com.gs.utils.DirUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.Date;

/**
 * 提取PDF中文字
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/09 15:55
 **/
public class Pdf2Word {

    public static String pdf2word(InputStream in, String outPath, String fileName) {
        DirUtils.makeDir(outPath);
        String outFilePath = outPath + fileName + "." + Constants.DOCX;
        try {
            File file = new File(outFilePath);
            PDDocument document = PDDocument.load(in);
            int pageNumber = document.getNumberOfPages();
            FileOutputStream fos = new FileOutputStream(file);
            Writer writer = new OutputStreamWriter(fos, "UTF-8");
            PDFTextStripper stripper = new PDFTextStripper();
            // 排序
            stripper.setSortByPosition(true);
            // 设置转换的开始页
            stripper.setStartPage(1);
            // 设置转换的结束页
            stripper.setEndPage(pageNumber);
            stripper.writeText(document, writer);

            writer.close();
            document.close();
            in.close();
            System.out.println("pdf转换word成功！");
        } catch (IOException e) {
            System.out.println("文件转换失败:" + e.getMessage());
            throw new ToolException("文件转换失败", e);
        }
        return outFilePath;
    }

    public static void main(String[] args) {
        String path = "D:\\test\\pdf\\in\\合作签约操作指引(1).pdf";
        File file = new File(path);
        String out = "D:" + File.separator + "test" + File.separator + "pdf" + File.separator + "out" + File.separator;
        String outDir = out + DateUtils.format(new Date(), DateUtils.format1) + File.separator;
        String s = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            String fileName = file.getName().split("\\.")[0];
            s = pdf2word(inputStream, outDir, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }
}
