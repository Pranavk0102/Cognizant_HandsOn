package Coding.FactoryPattern;

public class PDFDocFactory extends DocumentFactory{
  public Document createDoc(){
    return new PDFDocument();
  }
}
