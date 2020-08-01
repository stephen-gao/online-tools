package com.gs.pdf;

import com.gs.Constants;
import com.gs.exception.ToolException;
import com.gs.utils.DateUtils;
import com.gs.utils.DirUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Date;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/11 09:41
 **/
public class Pdf2Html {

    public static String pdf2Html(InputStream in, String outPath, String fileName) {
        long start = System.currentTimeMillis();
        DirUtils.makeDir(outPath);
        String outFilePath = outPath + fileName + "." + Constants.HTML;
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outFilePath)), "UTF-8"))){
            PDDocument document = PDDocument.load(in);
            PDFDomTree domTree = new PDFDomTree();
            domTree.writeText(document,out);
            document.close();
            in.close();
        } catch (IOException e) {
            System.out.println("文件转换失败:" + e.getMessage());
            throw new ToolException("文件转换失败", e);
        } catch (ParserConfigurationException e) {
            System.out.println("文件转换失败:" + e.getMessage());
            throw new ToolException("文件转换失败", e);
        }
        long end = System.currentTimeMillis();
        System.out.println("文件转换耗时:" + (end - start));
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
            s = pdf2Html(inputStream, outDir, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }

}
