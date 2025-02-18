import java.util.Scanner;

public class TextModifier {
            public static void main(String[] args) {
                  textModifier();
            }
            
            public static void textModifier() {
                  Scanner scanner = new Scanner(System.in);
                  System.out.println(" Введите текст: ");
                  String text = scanner.nextLine();
                  
                 // Удаление лишних пробелов в тексте
                  StringBuilder modifiedText = new StringBuilder();
                  boolean spaceDetected = false;
                  for (int i = 0; i < text.length(); i++) {
                        if (text.charAt(i) == ' ') {
                              if (!spaceDetected) {
                                    modifiedText.append(text.charAt(i));
                              }
                              spaceDetected = true;
                        } else {
                              modifiedText.append(text.charAt(i));
                              spaceDetected = false;
                        }
                  }
                  
                  // Замена нами символов вокруг знака "-"
                  StringBuilder swappedText = new StringBuilder();
                  for (int i = 0; i < modifiedText.length(); i++) {
                        if (modifiedText.charAt(i) == '-') {
                               swappedText.append(modifiedText.charAt(i + 1));
                                swappedText.append(modifiedText.charAt(i - 1));
                              i++;
                        } else {
                              swappedText.append(modifiedText.charAt(i));
                        }
                  }
                  
                 // Заменим "+" на "!"
                  String replacedText = swappedText.toString().replace('+', '!');
                  
                  // Вычисление суммы цифр и их удаление
                  int sum = 0;
                  String finalText = "";
                  for (int i = 0; i < replacedText.length(); i++) {
                        char c = replacedText.charAt(i);
                        if (Character.isDigit(c)) {
                              sum += Character.getNumericValue(c);
                        } else {
                              finalText += c;
                        }
                  }
                  finalText += " " + sum;
                  
                  System.out.println(" Измененный текст: " + finalText);
                  
                  scanner.close();
            }
      }
