package Coding.Recursion;

public class RecursionExample {
  public static double Calculate(double amount,double rate,int years){
    if(years==0){
      return amount;
    }

    double newAmount=amount*(1+rate/100);
    return Calculate(newAmount,rate,years-1); 
  }

  public static void main(String[] args) {
    double amount=100500.0;
    double rate=5.9;
    int years=12;
    
    double result=Calculate(amount,rate,years);
    System.out.println("The future amount will be "+result);

  }
}
