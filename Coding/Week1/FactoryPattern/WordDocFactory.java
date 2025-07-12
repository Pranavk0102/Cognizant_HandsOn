package Coding.FactoryPattern;

public class WordDocFactory extends DocumentFactory{
  public Document createDoc(){
    return new WordDocument();
  }
}
