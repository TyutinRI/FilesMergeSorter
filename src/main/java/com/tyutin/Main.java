package com.tyutin;

/**
 Программа для сортировки слиянием нескольких файлов, содержащих строковые либо целочисленные значения
 @author Roman Tyutin
 */

public class Main {

    public static void main(String[] args) {
        Boolean isAscending = true;
        Boolean isString = true;
        String[] inputOutputFilesName = null;

        if (args.length < 4) {
            System.out.println("Введено недостаточное число аргументов!");
            System.out.println("В качестве первого аргумента введите '-a' для сортировки по возрастанию, " +
                    "или '-d' для сортировки по убыванию");
            System.out.println("В качестве второго аргумента введите '-s' для сортировки строк, или '-i' для сортировки целых чисел");
            System.out.println("В качестве третьего аргумента введите имя файла, который будет содержать результат сортировки");
            System.out.println("Далее введите имена предварительно отсортированных, в соответствии с выбраным ранее порядком, ");
            System.out.println("файлов (не менее одного файла)");
            System.exit(0);
        }

        if (args[0].equals("-a")) {
            isAscending = true;
        } else if (args[0].equals("-d")) {
            isAscending = false;
        } else {
            System.out.println("Тип сортировки (первый аргумент) введен некорректно!");
            System.out.println("Введите '-a' для сортировки по возрастанию, или '-d' для сортировки по убыванию");
            System.exit(0);
        }

        if (args[1].equals("-s")) {
            isString = true;
        } else if (args[1].equals("-i")) {
            isString = false;
        } else {
            System.out.println("Тип данных введен некорректно!");
            System.out.println("Введите '-s' для сортировки строк, или '-i' для сортировки целых чисел");
            System.exit(0);
        }


        inputOutputFilesName = new String[args.length - 2];
        for (int i = 0; i < args.length - 3; i++) {
            inputOutputFilesName[i] = args[i + 3];
        }
        inputOutputFilesName[inputOutputFilesName.length - 1] = args[2];


        AbstractMergeSorter a = null;
        if (isString) {
            a = new StringMergeSorter();
        } else {
            a = new IntegerMergeSorter();
        }
        a.mergeSort(isAscending, inputOutputFilesName);

    }

}
