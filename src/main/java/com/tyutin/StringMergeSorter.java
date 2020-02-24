package com.tyutin;

import java.io.*;

public class StringMergeSorter extends AbstractMergeSorter {

    /**
     * Метод для сортировки слиянием пары файлов, содержащих строковые значения
     * @param firstFileName имя первого файла из сортируемой пары
     * @param secondFileName имя второго файла из сортируемой пары
     * @param outputFileName имя файла для записи результата сортировки
     * @param sortIsAscending параметр, определяющий порядок сортировки
     */

    @Override
    public void mergeSortForCouples(String firstFileName, String secondFileName,
                                    String outputFileName, Boolean sortIsAscending) {

        try (BufferedReader reader1 = new BufferedReader(new FileReader(firstFileName));
             BufferedReader reader2 = new BufferedReader(new FileReader(secondFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))){


            String firstPrevLine = null;
            String secondPrevLine = null;
            String firstComparedLine = reader1.readLine();
            String secondComparedLine = reader2.readLine();


            while (firstComparedLine != null || secondComparedLine != null) {

                /*
                В ходе сортировки из каждого файла поочередно считываются значения.
                Каждое считанное строковое значение проверяется на соответствие порядку сортировки и отсутствие
                пробельных символов.
                Если значение не проходит проверку, то считывается следующее.
                 */
                while (firstComparedLine != null && ((firstPrevLine != null &&
                        ((firstComparedLine.compareTo(firstPrevLine) < 0 && sortIsAscending)
                                || (firstComparedLine.compareTo(firstPrevLine) > 0 && !sortIsAscending)))
                        || (firstComparedLine.contains(" ")))) {

                    firstComparedLine = reader1.readLine();
                }
                while (secondComparedLine != null && ((secondPrevLine != null &&
                        ((secondComparedLine.compareTo(secondPrevLine) < 0 && sortIsAscending)
                                || (secondComparedLine.compareTo(secondPrevLine) > 0 && !sortIsAscending)))
                        || (secondComparedLine.contains(" ")))) {

                    secondComparedLine = reader2.readLine();
                }

                /*
                Алгоритм сортировки слиянием двух файлов
                 */

                if (firstComparedLine == null && secondComparedLine == null) {
                    break;
                } else if (firstComparedLine == null) {
                    writer.write(secondComparedLine + System.lineSeparator());
                    secondPrevLine = secondComparedLine;
                    secondComparedLine = reader2.readLine();
                } else if (secondComparedLine == null) {
                    writer.write(firstComparedLine + System.lineSeparator());
                    firstPrevLine = firstComparedLine;
                    firstComparedLine = reader1.readLine();
                } else if (((firstComparedLine.compareTo(secondComparedLine)) <= 0 && sortIsAscending)
                        || ((firstComparedLine.compareTo(secondComparedLine)) >= 0 && !sortIsAscending)) {
                    writer.write(firstComparedLine + System.lineSeparator());
                    firstPrevLine = firstComparedLine;
                    firstComparedLine = reader1.readLine();
                } else {
                    writer.write(secondComparedLine + System.lineSeparator());
                    secondPrevLine = secondComparedLine;
                    secondComparedLine = reader2.readLine();
                }


            }

            writer.flush();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ошибка чтения/записи файлов!");
            //e.printStackTrace();
        }

    }
}
