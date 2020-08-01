package com.gs.word;

import com.gs.Constants;
import com.gs.utils.DateUtils;
import com.gs.utils.DirUtils;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

import java.io.*;
import java.util.Date;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/07/11 11:19
 **/
public class Word2Txt {


    public static String word2Txt(String path, String outPath, String fileName) {
        DirUtils.makeDir(outPath);
        String outFilePath = outPath + fileName + "." + Constants.TXT;
        File file = new File(outFilePath);
        String buffer = "";
        try {
            OPCPackage opcPackage = POIXMLDocument.openPackage(path);
            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
            buffer = extractor.getText();
            System.out.println(buffer);


            extractor.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
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
            s = word2Txt(path, outDir, fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }
}
