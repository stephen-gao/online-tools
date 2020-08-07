package com.gs.pdf;

import com.gs.Constants;
import com.gs.exception.ToolException;
import com.gs.model.ImageRow;
import com.gs.model.Row;
import com.gs.utils.DateUtils;
import com.gs.utils.DirUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.fit.pdfdom.PDFDomTree;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 提取PDF中文字
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/09 15:55
 **/
public class Pdf2Word {

    private static final String r1 = "<html";

    public static String pdf2word(InputStream in, String outPath, String fileName) {
        DirUtils.makeDir(outPath);
        String outFilePath = outPath + fileName + "." + Constants.DOC;
        File file = new File(outFilePath);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();PDDocument document = PDDocument.load(in);BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"))) {

            PDFDomTree pdfDomTree = new PDFDomTree();
            pdfDomTree.writeText(document, out);

            String content = outputStream.toString();
            int index = StringUtils.indexOfIgnoreCase(content, r1);
            //截取<html></html>包围的
            content = StringUtils.substring(content, index);
            System.out.println(content);
//            System.out.println(content);
//            content.indexOf()
//            String s = StringUtils.("<img", "/>");
//            System.out.println(s);
            convertImg(content);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes("UTF-8"));

            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            directory.createDocument("WordDocument", inputStream);
            FileOutputStream ostream = new FileOutputStream(file);
            poifs.writeFilesystem(ostream);
            ostream.close();
            poifs.close();

            System.out.println("pdf转换word成功！");
        } catch (IOException e) {
            System.out.println("文件转换失败:" + e.getMessage());
            throw new ToolException("文件转换失败", e);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return outFilePath;
    }


    public static void convertImg(String content) {
//        int start = 0;
//        int count = 0;
//        Map<Integer, Row> map = new HashMap<>(16);
//        while (start >= 0) {
//            start = StringUtils.lastIndexOf(content, "<img");
//            if (start < 0) {
//                continue;
//            }
//            String tail = StringUtils.substring(content, start);
//            String imageStr = StringUtils.substringBefore(tail, "/>") + "/>";
//            content = StringUtils.replace(content, imageStr, "$$IMAGE_INDEX" + count);
//
//            int imageStart = StringUtils.lastIndexOf(tail, "data:image");
//            imageStr = StringUtils.substring(imageStr, imageStart);
//            String image = StringUtils.substringBefore(imageStr, "\"");
//            String target = doCconvertImg(image);
////            map.put(count, new R(count, image, target));
//            ImageRow imageRow = new ImageRow();
//            imageRow.setType(1);
////            imageRow.setImage();
//            map.put(count,)
//            System.out.println(image);
//            System.out.println(target);
//            count++;
//        }
    }

    //红色部分是处理图片的代码，不然图片不会正常显示
    public static BufferedImage doCconvertImg(String imgFile) {
        BufferedImage bi1 = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] data = decoder.decodeBuffer(imgFile);
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            bi1 = ImageIO.read(bais);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bi1;
    }

    public static void main(String[] args) {
        String path = "D:\\test\\pdf\\in\\测试.pdf";
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
