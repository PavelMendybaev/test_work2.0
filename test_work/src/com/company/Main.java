package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static  String[] rim = {"I" , "IV" , "V" , "IX" ,"X" ,"XC" , "C" };
    static char[] oper = {'+' , '-' ,'/' , '*'};


    public static void main(String[] args) {
        String[] nums = {"",""};

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        char operate = ' ';
        for(int i = 0 , l = 0  ; i < input.length() ; i++){
            if(l >= 2){
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System.exit(0);
            }
            if(input.charAt(i) != ' '){
                boolean ch_oper = false;
                for(int j =0 ; j < oper.length ; j ++){
                    if(input.charAt(i) == oper[j]) {
                        l++;
                        operate = oper[j];
                        ch_oper = true;
                    }
                }
                if(!ch_oper){
                    nums[l] += input.charAt(i);
                }
            }
        }
        if(nums[1] == ""){
            System.out.println("throws Exception //т.к. строка не является математической операцией");

            System.exit(0);
        }

        boolean rim_ch = false;
        boolean rim_ch2 = false;
        for (int i =0 ; i < rim.length ; i++ ){
            if(nums[0].charAt(0) == rim[i].charAt(0)){
                rim_ch = true;
            }
            if(nums[1].charAt(0) == rim[i].charAt(0)){
                rim_ch2 = true;
            }
        }
        if(rim_ch != rim_ch2){
            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления\n");
            System.exit(0);
        }

        if(!rim_ch){
            switch (operate){
                case '+':
                    System.out.println(Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]));
                    break;
                case '-':
                    System.out.println(Integer.parseInt(nums[0]) - Integer.parseInt(nums[1]));
                    break;
                case '*':
                    System.out.println(Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]));
                    break;
                case '/':
                    System.out.println(Integer.parseInt(nums[0]) / Integer.parseInt(nums[1]));
                    break;
            }

        }
        else {
            System.out.println(sum_rim(Convert_rim(nums[0]) , Convert_rim(nums[1]) , operate));
        }

    }
    static public int Convert_rim(String s_rim) {
        int sum = 0;
        String ch = "";
        for (int i = 0; i < s_rim.length(); i++) {

            switch (s_rim.charAt(i)) {
                case 'I':
                    i++;
                    if(i < s_rim.length()){
                        switch (s_rim.charAt(i)){
                            case 'V':
                                sum += 4;
                                break;
                            case 'X':
                                sum += 9;
                                break;
                            case 'I':
                                i--;
                                sum += 1;
                                break;
                        }
                    }
                    else{
                        sum += 1;
                    }
                    break;
                case 'V':
                    sum += 5;
                    break;
                case 'X':
                    sum += 10;
                    break;
            }
        }
        return sum;
    }
    static public String sum_rim(int num1 , int num2 , char oper)
    {
        int summ = 0;
        String exit = "";
        switch (oper)
        {
            case '+':
                summ = num1 + num2;
                break;
            case '-':
                summ = num1- num2;
                break;
            case '/':
                summ = num1/num2;
                break;
            case '*':
                summ = num1*num2;
                break;



        }
        if(summ <= 0 ){
            System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
            System.exit(0);
        }
        while (summ != 0){
            if(summ >= 100){
                exit += "C";
                summ -= 100;
            }
            else if(summ >= 90){
                exit += "XC";
                summ-= 90;
            }
            else if(summ >= 50){
                exit += "L";
                summ -= 50;
            }
            else if (summ >= 40){
                exit+="XL";
                summ -= 40;
            }
            else if(summ >= 10){
                exit += "X";
                summ -= 10;
            }
            else if(summ >= 9){
                exit += "IX";
                summ -= 9;
            }
            else if(summ >= 5){
                exit += "V";
                summ -= 5;
            }
            else if(summ >= 4){
                exit +="IV";
                summ -= 4;
            }
            else if(summ >= 1){
                exit += "I";
                summ -=1;
            }
        }
        return exit;
    }
}
