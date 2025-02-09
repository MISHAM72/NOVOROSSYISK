package LOOPS;
import java.util.Scanner;

public class CATA_Calculator {
  static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
     
      
        System.out.println("введите 2 числа: ");
        int num1 = getInt();
        int num2 = getInt();
        
        System.out.println("введите знак операции: ");
        char operation = getOperation();
        
        int value = calc(num1, num2, operation);
        System.out.println("Результат операции: " + value);
    
        System.out.println(" это ответ в римских числах -  ");
        String roman = Roman(value);
        System.out.println(roman);

    }
public static int getInt(){
        int num = scanner.nextInt();
      return num;
}
    public static char getOperation() {
        char operation = scanner.next().charAt(0);
        return operation;
    }
    public static int calc(int b1, int b2, char oper) {
        int result;
        switch (oper) {
            case '+': result = b1 + b2;
                break;
            case '-':
                result = b1 - b2;
                break;
            case '*':
                result = b1 * b2;
                break;
            case '/':
                result = b1 / b2;
                break;
            default:
                System.out.println("Операция не распознана. Повторите ввод.");
                result = calc(b1, b2, oper);//рекурсия
        }
                return result;
        }
    
        public static String Roman  (double  input) {
    
            String s = "";
            if (input < 1 || input > 999)
                System.out.println("negative roman numeral value ");
            while (input >= 100) {
                s += "C";
                input -= 100;
            }
            while (input >= 90) {
                s += "XC";
                input -= 90;
            }
            while (input >= 50) {
                s += "L";
                input -= 50;
            }
            while (input >= 40) {
                s += "XL";
                input -= 40;
            }
            while (input >= 10) {
                s += "X";
                input -= 10;
            }
            while (input >= 9) {
                s += "IX";
                input -= 9;
            }
            while (input >= 5) {
                s += "V";
                input -= 5;
            }
            while (input >= 4) {
                s += "IV";
                input -= 4;
            }
            while (input >= 1) {
                s += "I";
                input -= 1;
            }
            return  s;
    }
}
