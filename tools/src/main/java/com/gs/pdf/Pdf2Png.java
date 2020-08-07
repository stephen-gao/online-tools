//package com.gs.pdf;
//
//import com.gs.Constants;
//import com.gs.utils.DateUtils;
//import com.gs.utils.DirUtils;
//import com.gs.utils.ZipUtils;
//import com.itextpdf.kernel.pdf.PdfDocument;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Date;
//import java.util.UUID;
//
///**
// * ...
// *
// * @author GaoSheng
// * @version 1.0
// * @blame GaoSheng
// * @since 2020/07/08 15:26
// **/
//public class Pdf2Png {
//
//    public static String pdf2Png(String filepath, String outPath, String fileName) {
//        long start = System.currentTimeMillis();
//        String outZip = outPath + fileName + "." + Constants.ZIP;
//        String tempPath = outPath + UUID.randomUUID().toString() + File.separator;
//        //创建输出文件夹
//        DirUtils.makeDir(tempPath);
//        PdfDocument pdfDocument = PDFReader.pdfRead(filepath);
//        try{
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        try (PDDocument document = PDDocument.load(in)) {
////            int pages = document.getNumberOfPages();
////            PDFRenderer renderer = new PDFRenderer(document);
////            for (int i = 0; i < pages; i++) {
////                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, 300, ImageType.RGB);
////                String outName = tempPath + fileName + Constants.SHORT_LINE + i + "." + Constants.PNG;
////                System.out.println(outName);
////                File image = new File(outName);
////                ImageIO.write(bufferedImage, Constants.PNG, image);
////            }
////        } catch (IOException e) {
////            System.out.println("文件转换失败:" + e.getMessage());
////            throw new ToolException("文件转换失败", e);
////        }
//        ZipUtils.toZip(tempPath, outZip);
//        long end = System.currentTimeMillis();
//        System.out.println("文件转换耗时:" + (end - start));
//        return outZip;
//    }
//
//    public static void main(String[] args) {
//        String path = "D:\\test\\pdf\\in\\测试.pdf";
//        String out = "D:" + File.separator + "test" + File.separator + "pdf" + File.separator + "out" + File.separator;
//        String outDir = out + DateUtils.format(new Date(), DateUtils.format1) + File.separator;
//
//        String s = pdf2Png(path, outDir, "测试");
//        System.out.println(s);
//    }
//}
