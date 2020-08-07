//package com.gs.pdf;
//
//import com.gs.Constants;
//import com.gs.exception.ToolException;
//import com.gs.model.PdfEntity;
//import com.gs.model.PdfPage;
//import com.gs.utils.DateUtils;
//import com.gs.utils.DirUtils;
//import com.gs.word.WordUtils;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPageTree;
//import org.apache.poi.xwpf.usermodel.*;
//import org.dom4j.Attribute;
//import org.dom4j.Element;
//import org.dom4j.Text;
//import org.dom4j.io.DOMReader;
//import org.dom4j.io.SAXReader;
//import org.dom4j.tree.DefaultText;
//import org.fit.pdfdom.PDFDomTree;
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.*;
//import java.util.*;
//
///**
// * ...
// *
// * @author GaoSheng
// * @version 1.0
// * @blame GaoSheng
// * @since 2020/08/03 15:28
// **/
//public class Pdf2Xml {
//    public static String pdf2xml(String filePath, String outPath, String fileName) {
//        long start = System.currentTimeMillis();
//        String outFile = outPath + fileName + "." + Constants.XML;
//        String tempPath = outPath + UUID.randomUUID().toString() + File.separator;
//        //创建输出文件夹
//        DirUtils.makeDir(tempPath);
//        List<PdfPage> pdfPages = new ArrayList<>();
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); PDDocument pdocument = PdfReader.pdfRead(filePath); BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"))) {
//            PDFDomTree pdfDomTree = new PDFDomTree();
//            pdfDomTree.createDOM(pdocument);
//            Document doc = pdfDomTree.getDocument();
//
//            DOMReader domReader = new DOMReader();
//            org.dom4j.Document document = domReader.read(doc);
//            Element rootElement = document.getRootElement();
//            List<Element> contents = rootElement.content();
//            Element body = contents.get(1);
//            List<Element> pages = body.content();
//            for (Element page : pages) {
//                List<PdfEntity> entities = new ArrayList();
//                PdfPage pdfPage = new PdfPage();
//                List<Element> rows = page.content();
//                for (Element row : rows) {
//                    PdfEntity entity = new PdfEntity();
//                    System.out.println(row);
//                    List<Attribute> attributes = row.attributes();
//                    for(Attribute a : attributes){
//                        if(a.getQualifiedName().equals("class")){
//                            entity.setClazz(a.getValue());
//                            if(a.getValue().equals("p")){
//                                List<Node> texts = row.content();
//                                if(texts.size() == 1 && texts.get(0) instanceof Text){
//                                    entity.setText(((Text) texts.get(0)).getText());
//                                }
//                            }
//                        }else if(a.getQualifiedName().equals("id")){
//                            entity.setId(a.getValue());
//                        }else if(a.getQualifiedName().equals("style")){
//                            String[] styles = a.getValue().split(";");
//                            Map<String,String> styleMap = new HashMap<>(8);
//                            for (int i = 0; i < styles.length; i++) {
//                                String[] kv = styles[i].split(":");
//                                if(kv.length > 1) {
//                                    styleMap.put(kv[0],kv[1]);
//                                }
//                            }
//                            entity.setStyle(styleMap);
//                        }
//                    }
//                    entities.add(entity);
//                }
//                pdfPage.setRows(entities);
//                pdfPages.add(pdfPage);
//            }
//
//
//            System.out.println();
//            XWPFDocument word = new XWPFDocument();
//            XWPFParagraph paragraph = word.createParagraph();
//            paragraph.setAlignment(ParagraphAlignment.LEFT);//设置段落内容靠
//            paragraph.setIndentationRight(500);//末尾缩进300
//            for (int i = 0; i < pdfPages.size(); i++) {
//                PdfPage pdfPage = pdfPages.get(i);
//                List<PdfEntity> rows = pdfPage.getRows();
//                XWPFRun run = paragraph.createRun();
//                for(PdfEntity entity :rows){
//                    Map<String, String> style = entity.getStyle();
//                    if(!style.containsKey("letter-spacing")){
//                        run.addBreak();
//                        run = paragraph.createRun();
//                    }
//                    run.setText(entity.getText());
//                }
//                run.addBreak(BreakType.PAGE);//添加一个回车空行pdfPages
//            }
//
//            WordUtils.outWord("D:" + File.separator + "test" + File.separator + "word" + File.separator + "out" + File.separator,"testword.",word);
//
//        } catch (IOException e) {
//            System.out.println("文件转换失败:" + e.getMessage());
//            throw new ToolException("文件转换失败", e);
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
//
//
//
//        long end = System.currentTimeMillis();
//        System.out.println("文件转换耗时:" + (end - start));
//        return outFile;
//    }
//
//
//
//
//    public static void main(String[] args) {
//        String path = "D:\\test\\pdf\\in\\测试.pdf";
//        String out = "D:" + File.separator + "test" + File.separator + "pdf" + File.separator + "out" + File.separator;
//        String outDir = out + DateUtils.format(new Date(), DateUtils.format1) + File.separator;
//
//        String s = pdf2xml(path, outDir, "3.1.9-202008");
//        System.out.println(s);
//    }
//}
