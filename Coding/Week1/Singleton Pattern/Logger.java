package Coding;
public class Logger{
  private static Logger instance;

  private Logger(){
     
  }
  public static Logger getinstance(){
    if(instance==null){
      instance=new Logger();
      System.out.println("New log created");
    }
    else{
      System.out.println("Instance already exists");
    }
    return instance;
  }
  public void log(String message){
    System.out.println(message);
  }
  
}