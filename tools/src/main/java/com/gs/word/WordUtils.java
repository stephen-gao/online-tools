package com.gs.word;

import com.gs.Constants;
import com.gs.utils.DirUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;

import java.io.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/08/03 11:45
 **/
public class WordUtils {

    public static XWPFDocument wordRead(InputStream inputStream) throws IOException {
        return new XWPFDocument(inputStream);
    }

    public static void outWord(String outPath, String fileName, XWPFDocument document) {
        DirUtils.makeDir(outPath);
        try (FileOutputStream outputStream = new FileOutputStream(outPath + fileName + Constants.DOCX)) {
            document.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();

        paragraph.setAlignment(ParagraphAlignment.RIGHT);//设置段落内容靠右
        paragraph.setIndentationRight(500);//末尾缩进300

        XWPFRun run = paragraph.createRun();
        run.setText("测试段落   样式设置");
        run.setBold(true); //加粗
        run.setFontSize(12);//字体大小
        run.setFontFamily("华文中宋");
        run.addBreak(BreakType.PAGE);//添加一个回车空行

        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun run1 = paragraph1.createRun();
        run1.setText("测试段落 ");
        run1.setBold(true); //加粗
        run1.setFontSize(12);//字体大小
        run1.setFontFamily("华文中宋");

        run1.addBreak();//添加一个回车空行

        outWord("D:" + File.separator + "test" + File.separator + "word" + File.separator + "out" + File.separator,"testword.",document);
    }

//    public static void main(String[] args) {
//        String path = "D:\\test\\pdf\\in\\测试.pdf";
//        try (InputStream inputStream = new FileInputStream(path); XWPFDocument document = wordRead(inputStream)) {
//            List<XWPFParagraph> paragraphs = document.getParagraphs();
//            for (XWPFParagraph paragraph: paragraphs                 ) {
//                System.out.println(paragraph.toString());
//            }
////            List<IBodyElement> bodyElements = document.getBodyElements();
////            for (IBodyElement element : bodyElements) {
////                System.out.println(element);
////            }
//
//            System.out.println(1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
