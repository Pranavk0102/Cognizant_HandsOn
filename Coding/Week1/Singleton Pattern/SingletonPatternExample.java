package Coding;
public class SingletonPatternExample {
  public static void main(String[] args) {
    Logger log1=Logger.getinstance();
    Logger log2=Logger.getinstance();

    log1.log("First");
    log2.log("Second");


    if(log1==log2){
      System.out.println("Singleton Pattern Success");
    }
    else{
      System.out.println("Failure");
    }
  }
}
