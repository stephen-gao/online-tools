package com.gs.pdf;

import com.gs.exception.ToolException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.fit.pdfdom.PDFDomTree;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/08/03 14:02
 **/
public class PdfReader {

    public static PDDocument pdfRead(String filepath) {
        File file = new File(filepath);
        return pdfRead(file);
    }

    public static PDDocument pdfRead(File file) {
        PDDocument document = null;
        try {
            document = PDDocument.load(file);
        } catch (IOException e) {
            System.out.println("文件读取异常:" + e.getMessage());
            throw new ToolException("文件读取异常", e);
        }

        return document;
    }

    public static PDDocument pdfRead(InputStream in) {
        PDDocument document = null;
        try {
            document = PDDocument.load(in);
        } catch (IOException e) {
            System.out.println("文件读取异常:" + e.getMessage());
            throw new ToolException("文件读取异常", e);
        }

        return document;
    }


    public static void main(String[] args){
        String path = "D:\\test\\pdf\\in\\合作签约操作指引(1).pdf";
        File file = new File(path);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); PDDocument pdocument =pdfRead(file); BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"))) {

            PDFDomTree pdfDomTree = new PDFDomTree();
            pdfDomTree.writeText(pdocument, out);
            String content = outputStream.toString();

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(content);
            Element element = document.getRootElement();

            System.out.println(element);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }
}
