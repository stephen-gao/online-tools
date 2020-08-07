//package com.gs.pdf;
//
//import com.gs.Constants;
//import com.gs.exception.ToolException;
//import com.gs.utils.DateUtils;
//import com.gs.utils.DirUtils;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.fit.pdfdom.PDFDomTree;
//
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.*;
//import java.util.Date;
//
///**
// * ...
// *
// * @author GaoSheng
// * @version 1.0
// * @blame GaoSheng
// * @since 2020/07/11 09:41
// **/
//public class Pdf2Html {
//
//    public static String pdf2Html(String filePath, String outPath, String fileName) {
//        long start = System.currentTimeMillis();
//        DirUtils.makeDir(outPath);
//        String outFilePath = outPath + fileName + "." + Constants.HTML;
//        try (PDDocument document = PdfReader.pdfRead(filePath);Writer out = new PrintWriter(outFilePath, "utf-8")){
//            PDFDomTree domTree = new PDFDomTree();
//            domTree.writeText(document,out);
//
//        } catch (IOException e) {
//            System.out.println("文件转换失败:" + e.getMessage());
//            throw new ToolException("文件转换失败", e);
//        } catch (ParserConfigurationException e) {
//            System.out.println("文件转换失败:" + e.getMessage());
//            throw new ToolException("文件转换失败", e);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("文件转换耗时:" + (end - start));
//        return outFilePath;
//    }
//
//    public static void main(String[] args) {
//        String path = "D:\\test\\pdf\\in\\测试.pdf";
//        String out = "D:" + File.separator + "test" + File.separator + "pdf" + File.separator + "out" + File.separator;
//        String outDir = out + DateUtils.format(new Date(), DateUtils.format1) + File.separator;
//
//        String s = pdf2Html(path, outDir, "测试");
//        System.out.println(s);
//    }
//
//}
