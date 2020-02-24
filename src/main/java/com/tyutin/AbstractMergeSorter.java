package com.tyutin;

import java.io.File;
import java.io.IOException;

public abstract class AbstractMergeSorter {

    /**
     * метод позволяет осуществлять сотрировку методом слияния нескольких предварительно отсортированных файлов
     * содержащих целочисленные либо строковые значения, с помощью поочередного вызова метода для сортировки слиянием
     * двух файлов из соответствующего подкласса
     * @param sortIsAscending определяет порядок сортировки
     * @param files представляет из cебя массив строк,содержащих название файлов. Сначала записываются сортируемые
     *              файлы, а в качестве последнего элемента массива записыается результирующий файл
     */

    public void mergeSort(Boolean sortIsAscending, String[] files){
        try {
            File rez = new File(files[files.length - 1]);

            File intermediateRez = new File("0intermediaterezult.txt");
            intermediateRez.createNewFile();

            for (int i = 0; i < (files.length - 1); i++) {

                mergeSortForCouples(files[i], intermediateRez.getAbsolutePath(),
                        (i + 1) + "intermediaterezult.txt",
                        sortIsAscending);
                intermediateRez.delete();
                intermediateRez = new File( (i + 1) + "intermediaterezult.txt");

            }

            intermediateRez.renameTo(rez);

        } catch (IOException e) {
            System.out.println("Ошибка при сортировке! Убедитесь, что возможна запись " +
                    "в заданный для результирующего файла каталог.");
        }


    };


    /**
     * Абстрактный метод для сортировки пары файлов слиянием.
     */
    public abstract void mergeSortForCouples(String firstFileName, String secondFileName,
                                             String outputFileName, Boolean sortIsAscending);
}
