package Coding.FactoryPattern;

public class FactoryPatternExample{
  public static void main(String[] args) {
    DocumentFactory wordFactory=new WordDocFactory();
    Document wd=wordFactory.createDoc();
    wd.create();

    DocumentFactory pdfFactory=new PDFDocFactory();
    Document pf=pdfFactory.createDoc();
    pf.create();

    DocumentFactory excelFactory=new ExcelDocFactory();
    Document ef=excelFactory.createDoc();
    ef.create();
  }
}