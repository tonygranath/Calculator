package se.lexicon.tonygranath;

import java.util.LinkedList;
import java.util.Scanner;

public class Calculator {

    //Print welcome message
    private static void greeting() {
        System.out.println("Welcome to this calculator!");
        System.out.println("Enter numbers like \"6+12\" and I will tell you the result.");
        System.out.println("Enter q/Q/exit/quit to quit.\n");
    }

    //Get numbers from a string
    private static LinkedList<Integer> splitNumbers(String operation) {
        String[] split = operation.split("\\+|-|\\*|/"); //split string at operators
        LinkedList<Integer> list = new LinkedList<>();

        for(String str : split) {
            list.add(Integer.parseInt(str));
        }

        return list;
    }

    //Get mathematical operators +, -, * and / from a string
    private static LinkedList<String> splitOperators(String operation) {
        String[] split = operation.split("\\d"); //split string at digits
        LinkedList<String> list = new LinkedList<>();

        for(String str : split) {
            if (!str.isEmpty()) {
                list.add(str);
            }
        }

        return list;
    }

    //Perform mathematical operation (addition, subtraction, multiplication and division)
    private static int calc(LinkedList<Integer> numbers, LinkedList<String> operators) {
        if (!operators.isEmpty()) { // && operators.size()!=0) {

            //loop through operators looking for * and / and do these calculations first
            for (int i = 0; i < operators.size(); i++) {
                switch (operators.get(i)) {
                    case "*":
                        numbers.set(i, numbers.get(i) * numbers.get(i + 1));
                        operators.remove(i);
                        numbers.remove(i + 1);
                        i--;
                        break;
                    case "/":
                        numbers.set(i, numbers.get(i) / numbers.get(i + 1));
                        operators.remove(i);
                        numbers.remove(i + 1);
                        i--;
                        break;
                    default:
                        break;
                }
            }

            //loop through operators looking for + and - and calculate these operations
            for (int i = 0; i < operators.size(); i++) {
                switch (operators.get(i)) {
                    case "+":
                        numbers.set(i, numbers.get(i) + numbers.get(i + 1));
                        operators.remove(i);
                        numbers.remove(i + 1);
                        i--;
                        break;
                    case "-":
                        numbers.set(i, numbers.get(i) - numbers.get(i + 1));
                        operators.remove(i);
                        numbers.remove(i + 1);
                        i--;
                        break;
                    default:
                        break;
                }
            }
        }

        return numbers.get(0);
    }

 /*   First, I wrote this method because recursion is sexy. But I rewrote it to the above method because
      it didn't calculate in the correct order and it would need to sort the operations...

    private static int calc(LinkedList<String> numbers, LinkedList<String> operators) {
        int result = Integer.parseInt(numbers.get(0));
        if (numbers.size() == 1)
            return Integer.parseInt(numbers.get(0));

        try {
                if (!operators.isEmpty() && operators.size()!=0) {
                    switch(String.valueOf(operators.get(0))) {
                        case "+":
                            result = Integer.parseInt(numbers.get(0)) + Integer.parseInt(numbers.get(1));
                            break;
                        case "-":
                            result = Integer.parseInt(numbers.get(0)) - Integer.parseInt(numbers.get(1));
                            break;
                        case "*":
                            result = Integer.parseInt(numbers.get(0)) * Integer.parseInt(numbers.get(1));
                            break;
                        case "/":
                            result = Integer.parseInt(numbers.get(0)) / Integer.parseInt(numbers.get(1));
                            break;
                        default:
                            return result;
                    }

                    numbers.removeFirst();
                    numbers.set(0, String.valueOf(result));
                    operators.removeFirst();
                    if (operators.size()!=0 || !operators.isEmpty()) {
                        calc(numbers, operators);
                    } else {
                        return result;
                    }
                }
                else {
                    return Integer.parseInt(numbers.get(0));
                }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(numbers.get(0));
    } */

    //Get user input from console
    private static boolean userInput() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input: ");
        String operation = scan.next();

        switch(operation) {
            case "q":
            case "Q":
            case "exit":
            case "quit":
                return false;
            default:
                LinkedList<Integer> numbers; // = new LinkedList<>();
                LinkedList<String> operators; // = new LinkedList<>();
                numbers = splitNumbers(operation);
                operators = splitOperators(operation);
                System.out.println(operation + " = " + calc(numbers, operators));
        }

        return true;
    }

    public static void main(String[] args) {
        greeting();
        boolean running = true;

        //main loop
        while(running) {
            try {
                running = userInput();
            } catch(Exception e) {
                System.out.println("Invalid input.");
            }
        }

        System.out.println("Good bye!");
    }
}
