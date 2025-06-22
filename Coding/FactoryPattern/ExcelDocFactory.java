package Coding.FactoryPattern;

public class ExcelDocFactory extends DocumentFactory{
  public Document createDoc(){
    return new ExcelDocument();
  }
}
