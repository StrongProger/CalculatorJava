import java.io.IOException;
import java.util.Scanner;

class CalculatorJava {

    public static void main(String[] args) throws IOException {

        System.out.println("Введите числа от 1 до 10, либо римские от I до X, поддерживаемые операции: +,-,*,/");
        String input = new Scanner(System.in).nextLine();
        System.out.println(calc(input));

    }


    public static String calc(String input) throws IOException {

        int a;
        int b;
        int preResult;
        String sign;
        String result;
        boolean roman;
        String[] nums = input.toUpperCase().replaceAll(" ","").split("[*/+\\-]");
        if (nums.length != 2) {
            throw new IOException("Должно быть 2 числа (не меньше 1(I) и не больше 10(X), поддерживаются только следующие операции: *,/,+,-");
        }

        sign = searchSign(input);
        if (Roman.roman(nums[0]) && Roman.roman(nums[1])) {
            a = Roman.convertationArabian(nums[0]);
            b = Roman.convertationArabian(nums[1]);
            roman = true;
        } else if (!Roman.roman(nums[0]) && !Roman.roman(nums[1])) {
            a = Integer.parseInt(nums[0]);
            b = Integer.parseInt(nums[1]);
            roman = false;
        } else {
            throw new IOException("Оба числа должны быть римскими или арабскими");
        }
        if (a > 10 || b > 10) {
            throw new IOException("Числа должны быть не больше 10");
        }
        preResult = calculation(a, b, sign);
        if (roman) {
            if (preResult <= 0) {
                throw new IOException("Римские числа не могут быть меньше I");
            }
            result = Roman.convertationRoman(preResult);
        }
        else {
            result = String.valueOf(preResult);
        }
        return result;
    }
    static String searchSign (String input){

        if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        return input;
    }

    static int calculation ( int a, int b, String sign){

        return switch (sign) {
            case "*" -> a * b;
            case "/" -> a / b;
            case "+" -> a + b;
            default -> a - b;
        };



    }

}

class Roman{

    static String[] romanArr = {"0","I","II","III","IV","V","VI","VII","VIII","IX","X",
            "XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX",
            "XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII","XXIX","XXX",
            "XXXI","XXXII","XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL",
            "XLI","XLII","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L",
            "LI","LII","LIII","LIV","LV","LVI","LVII","LVIII","LIX","LX",
            "LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX",
            "LXXI","LXXII","LXXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXXIX","LXXX",
            "LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXVIII","LXXXIX","XC",
            "XCI","XII","XIII","XCIV","XCV","XCVI","XVII","XCVIII","ХХIХ","C"};  //массив римских чисел 1-100
    static boolean roman(String number){

        for (String i : romanArr) {
            if (number.equals(i)) {
                return true;
            }
        }
        return false;
    }

    static int convertationArabian(String roman){

        for (int i = 0; i < romanArr.length;i++){
            if (roman.equals(romanArr[i])){
                return i;
            }
        }
        return -1;
    }

    static String convertationRoman(int arabian){

        return romanArr[arabian];
    }

}