package com.gs.pdf;

import com.gs.Constants;
import com.gs.exception.ToolException;
import com.gs.utils.DateUtils;
import com.gs.utils.DirUtils;
import com.gs.utils.ZipUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/08 15:26
 **/
public class Pdf2Png {

    public static String pdf2Png(InputStream in, String outPath, String fileName) {
        long start = System.currentTimeMillis();
        String outZip = outPath + fileName + "." + Constants.ZIP;
        String tempPath = outPath + UUID.randomUUID().toString() + File.separator;
        //创建输出文件夹
        DirUtils.makeDir(tempPath);
        try {
            PDDocument document = PDDocument.load(in);
            int pages = document.getNumberOfPages();
            PDFRenderer renderer = new PDFRenderer(document);
            for (int i = 0; i < pages; i++) {
                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, 300, ImageType.RGB);
                String outName = tempPath + fileName + Constants.SHORT_LINE + i + "." + Constants.PNG;
                System.out.println(outName);
                File image = new File(outName);
                ImageIO.write(bufferedImage, Constants.PNG, image);
            }
            document.close();
            in.close();
        } catch (IOException e) {
            System.out.println("文件转换失败:" + e.getMessage());
            throw new ToolException("文件转换失败", e);
        }
        ZipUtils.toZip(tempPath, outZip);
        long end = System.currentTimeMillis();
        System.out.println("文件转换耗时:" + (end - start));
        return outZip;
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
            s = pdf2Png(inputStream, outDir, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }
}
