package com.gs.pdf;

import com.gs.Constants;
import com.gs.exception.ToolException;
import com.gs.utils.DateUtils;
import com.gs.utils.DirUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.fit.pdfdom.PDFDomTree;
import sun.misc.BASE64Encoder;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Date;
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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"))) {
            PDDocument document = PDDocument.load(in);
            PDFDomTree pdfDomTree = new PDFDomTree();
            pdfDomTree.writeText(document, out);

            String content = outputStream.toString();
            int index = StringUtils.indexOfIgnoreCase(content, r1);
            //截取<html></html>包围的
            content = StringUtils.substring(content, index);
//            System.out.println(content);
//            content.indexOf()
//            String s = StringUtils.("<img", "/>");
//            System.out.println(s);
            getImage(content);
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


    public static Map<String, String> getImage(String content) {
        int index = 0;
        int start = StringUtils.lastIndexOf(content, "<img");
        String head = StringUtils.substring(content, 0, start);
        String tail = StringUtils.substring(content, start, content.length());
//            System.out.println(tail);
        String s = StringUtils.substringBefore(tail, "/>");
        int shead = StringUtils.lastIndexOf(s, "data:image");
        String stail = StringUtils.substring(s, shead, s.length());
        String image = StringUtils.substringBefore(stail, "\"");
        System.out.println(image);
        content = head;

        return null;
    }

    //红色部分是处理图片的代码，不然图片不会正常显示
    public String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
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
