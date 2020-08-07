package com.gs.pdf;


import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.fit.pdfdom.PDFDomTree;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * ...
 *
 * @author GaoSheng
 * @version 1.0
 * @blame GaoSheng
 * @since 2020/08/03 14:02
 **/
public class PDFReader {

//    public static PdfDocument pdfRead(String filepath){
//        File file = new File(filepath);
//        return pdfRead(file);
//    }
//
//    public static PdfDocument pdfRead(File file){
//        PdfDocument document = null;
//        try {
//            PdfReader pdfReader = new PdfReader(file);
//            document = new PdfDocument(pdfReader);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return document;
//    }


    public static void main(String[] args) {
        String path = "D:\\test\\pdf\\in\\测试.pdf";
        String out = "D:\\test\\word\\out\\测试.html";
        try {
            FileOutputStream fos = new FileOutputStream(out);
            Writer writer = new OutputStreamWriter(fos);

            List<BufferedImage> list = new ArrayList<>();
            File fileIn = new File(path);
            PDDocument pdDocument = PDDocument.load(fileIn);
            int number = pdDocument.getNumberOfPages();

            PDFDomTree domTree = new PDFDomTree();
            domTree.createDOM(pdDocument);
            domTree.writeText(pdDocument,writer);


//            for (int i = 0; i < number; i++) {
//                PDPage page = pdDocument.getPage(i);
//                COSDictionary resources = page.getCOSObject();
//                System.out.println(resources.toString());
//                resources.entrySet().forEach(new Consumer<Map.Entry<COSName, COSBase>>() {
//                    @Override
//                    public void accept(Map.Entry<COSName, COSBase> cosNameCOSBaseEntry) {
//                        COSBase cosObject = cosNameCOSBaseEntry.getValue().getCOSObject();
//                        if(cosObject instanceof COSString){
//                            COSString cosString = (COSString) cosObject;
//                            System.out.println(cosString.getString());
//                        }
//
//                    }
//                });
//                PDResources resources = page.getResources();
//                Iterable<COSName> xObjectNames = resources.getXObjectNames();
//                xObjectNames.forEach(cosName -> {
//                    try {
//                        if (resources.isImageXObject(cosName)) {
//                            System.out.println("COSName " + cosName.getName() + " isImageXObject");
//                            PDXObject pdXObject = resources.getXObject(cosName);
//                            PDImageXObject pdImageXObject = (PDImageXObject) pdXObject;
//                            String suffix = pdImageXObject.getSuffix();
//                            System.out.println("Height:" + pdImageXObject.getHeight() + "Width:" + pdImageXObject.getWidth() + "Suffix:" + suffix);
//                            BufferedImage image = pdImageXObject.getImage();
//                            list.add(image);
//                        }
//                    } catch (Throwable e) {
//                        System.out.println("image err");
//                    }
//                });
//            }
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        } catch (ParserConfigurationException e1) {
//            e1.printStackTrace();
//        }

//
//            PDFTextStripper stripper = new PDFTextStripper();
//            stripper.setSortByPosition(true);// 排序
//            stripper.setStartPage(1);// 设置转换的开始页
//            stripper.setEndPage(pdDocument.getNumberOfPages());// 设置转换的结束页
//            stripper.writeText(pdDocument, writer);
            writer.flush();
            writer.close();
//            System.out.println(pageContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
