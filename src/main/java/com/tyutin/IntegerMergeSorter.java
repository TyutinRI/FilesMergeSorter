package com.tyutin;

import java.io.*;

public class IntegerMergeSorter extends AbstractMergeSorter {

    /**
     * Метод позволяет преобразовывать считанное из файла строковое значение в целочисленное
     * Если преобразование невозможно, считывается следующее значение и попытка повторяется
     * @param reader объект класса BufferedReader, с помощбю которого происходит считывание строк из файла
     * @return либо целочисленное значение, либо null в случае, если строки в файле закончились
     */
    private Integer chooseNextLine(BufferedReader reader){
        boolean isLegal = false;
        Integer line = null;
        do {
            try {
                String thisLine = reader.readLine();
                if(thisLine != null) {
                    line = Integer.valueOf(thisLine);
                    isLegal = true;
                }else {isLegal = true;}

            }catch (NumberFormatException e){
                isLegal = false;
            }catch (IOException e){
                System.out.println("Ошибка чтения/записи файлов!");
                //e.printStackTrace();
            }
        } while (!isLegal);
        return line;
    }

    /**
     * Метод для сортировки слиянием пары файлов, содержащих строковые значения
     * @param firstFileName имя первого файла из сортируемой пары
     * @param secondFileName имя второго файла из сортируемой пары
     * @param outputFileName имя файла для записи результат сортировки
     * @param sortIsAscending параметр, определяющий порядок сортировки
     */

    @Override
    public void mergeSortForCouples(String firstFileName, String secondFileName,
                                    String outputFileName, Boolean sortIsAscending) {

        try (BufferedReader reader1 = new BufferedReader(new FileReader(firstFileName));
             BufferedReader reader2 = new BufferedReader(new FileReader(secondFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))){


            Integer firstPrevLine;
            Integer secondPrevLine;

            if(sortIsAscending){
                firstPrevLine = Integer.MIN_VALUE;
                secondPrevLine = Integer.MIN_VALUE;
            } else {
                firstPrevLine = Integer.MAX_VALUE;
                secondPrevLine = Integer.MAX_VALUE;
            }

            Integer firstComparedLine = null;
            Integer secondComparedLine = null;

            firstComparedLine = chooseNextLine(reader1);
            secondComparedLine = chooseNextLine(reader2);



            while (firstComparedLine != null || secondComparedLine != null) {

                /*
                В ходе сортировки из каждого файла поочередно считываются значения.
                Каждое считанное строковое значение проверяется на соответствие порядку сортировки и отсутствие
                пробельных символов.
                Если значение не проходит проверку, то считывается следующее.
                 */

                while (firstComparedLine != null && firstPrevLine != null &&
                        ((firstComparedLine.compareTo(firstPrevLine) < 0 && sortIsAscending)
                                || (firstComparedLine.compareTo(firstPrevLine) > 0 && !sortIsAscending))){

                    firstComparedLine = chooseNextLine(reader1);

                }
                while (secondComparedLine != null && secondPrevLine != null &&
                        ((secondComparedLine.compareTo(secondPrevLine) < 0 && sortIsAscending)
                                || (secondComparedLine.compareTo(secondPrevLine) > 0 && !sortIsAscending))){

                    secondComparedLine = chooseNextLine(reader2);

                }

                /*
                Алгоритм сортировки слиянием двух файлов
                 */

                if (firstComparedLine == null && secondComparedLine == null) {
                    break;
                } else if (firstComparedLine == null) {
                    writer.write(secondComparedLine + System.lineSeparator());
                    secondPrevLine = secondComparedLine;

                    secondComparedLine = chooseNextLine(reader2);

                } else if (secondComparedLine == null) {
                    writer.write(firstComparedLine + System.lineSeparator());
                    firstPrevLine = firstComparedLine;

                    firstComparedLine = chooseNextLine(reader1);

                } else if (((firstComparedLine.compareTo(secondComparedLine)) <= 0 && sortIsAscending)
                        || ((firstComparedLine.compareTo(secondComparedLine)) >= 0 && !sortIsAscending)) {
                    writer.write(firstComparedLine + System.lineSeparator());
                    firstPrevLine = firstComparedLine;

                    firstComparedLine = chooseNextLine(reader1);

                } else {
                    writer.write(secondComparedLine + System.lineSeparator());
                    secondPrevLine = secondComparedLine;

                    secondComparedLine = chooseNextLine(reader2);

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
