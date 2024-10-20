package common.utils;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CreatePdf {
    public static final String pdfReportPath = System.getProperty("user.dir")+ File.separator+"PdfReports";
    public static void createConsolidatedPDF(String path,String testCaseName){
        try {
            File directory = new File(path);
            List<File> dirFiles = Arrays.asList(directory.listFiles());
            dirFiles.sort(LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
            PDDocument document = new PDDocument();
            for(File image:dirFiles) {
                PDPage my_page = new PDPage();
                PDImageXObject pdImage = PDImageXObject.createFromFileByContent(image,document);
                PDPageContentStream contents = new PDPageContentStream(document, my_page);
                contents.drawImage(pdImage, 10, 100,650,250);
                contents.close();
                document.addPage(my_page);
                image.delete();
            }
            document.save(new File(pdfReportPath+File.separator+testCaseName+".pdf"));
            document.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
