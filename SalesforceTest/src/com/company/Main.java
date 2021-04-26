package com.company;

import java.io.*;
import java.util.*;

public class Main{

    static String separator = File.separator;
    static String pathOutput = "src" + separator + "OutputFiles" + separator + "output.txt";
    static String pathInput = "src" + separator + "InputFiles" + separator + "input.txt";

    public static void main(String[] args) {
        First_Thread first_thread = new First_Thread();
        Second_Thread second_thread = new Second_Thread();

        new Thread(first_thread).start();
        new Thread(second_thread).start();

    }

    public static void fibwritemethod(int [] numArr,String line,String pathOutput) throws IOException, InterruptedException {
        String stringIntegers = extractDigits(line);
        numArr = Arrays.stream(stringIntegers.split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < numArr.length; i++) {

            if (isFibonacci(numArr[i])) {
                String s = Integer.toString(numArr[i])+"\n";
                FileWriter fileWriter = new FileWriter(pathOutput,true);

                fileWriter.write(s);
                fileWriter.close();
            }
        }
    }
    public static boolean isPerfectSquare(int x) {
        int s = (int) Math.sqrt(x);
        return (s*s == x);
    }

    public static boolean isFibonacci(int n) {
        return isPerfectSquare(5*n*n + 4) || isPerfectSquare(5*n*n - 4);
    }

    public static String extractDigits(String src) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < src.length(); i++) {

            char c = src.charAt(i);

            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }
    public static String reverseWithCharArray(String inputString) throws InterruptedException {

        char[] charArray = inputString.toCharArray();
        String resultString = "";
        for (int i = charArray.length - 1; i >= 0; i--) {

            resultString += charArray[i];
        }
        Thread.sleep(1000);
        return resultString;
    }


    static class First_Thread implements Runnable {
        @Override
        public void run() {


            String line;
            int[] numArr = new int[0];
            try (BufferedReader in = new BufferedReader(new FileReader(pathInput))) {
                line = in.readLine();
                while (line != null) {
                    fibwritemethod(numArr,line,pathOutput);
                    line = in.readLine();

                }
                Thread.sleep(1000);
            } catch (IOException | NumberFormatException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Second_Thread implements Runnable {
        @Override
        public void run() {


            String line;
            try (BufferedReader in = new BufferedReader(new FileReader(pathInput))) {
                line = in.readLine();
                while (line != null) {
                    FileWriter fileWriter = new FileWriter(pathOutput, true);
                    fileWriter.write(reverseWithCharArray(line + "\n"));

                    fileWriter.close();
                    line = in.readLine();

                }
                Thread.sleep(1000);
            } catch (IOException | NumberFormatException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}