import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//для теста: GGG NNN OOO 04.06.1984 89266911819 f
        String[] questions = new String[]{
                "0 фамилия, имя, отчество",
                "1 датарождения- строка формата dd.mm.yyyy",
                "2 номертелефона - целое беззнаковое число без форматирования",
                "3 пол - символ латиницей f или m."
        };
        System.out.println("Введите в одну строку такие данные: ");
        for (String question : questions) {
            System.out.println(question);
        }

        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        String[] dataArr = data.split("\\s+");
        System.out.println(Arrays.toString(dataArr));
//        1  - количество должно быть 6 шт
        if (!(dataArr.length == 6)) {
            StringBuilder e = new StringBuilder("количество должно быть ");
            e.append(dataArr.length > 6 ? "меньше" : "больше");
            throw new IllegalArgumentException(e.toString());
        }
//      2 - Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры
        //ФИО
        isLettersInString(dataArr[0], "фамилия");
        isLettersInString(dataArr[1], "имя");
        isLettersInString(dataArr[2], "отчество");
        //Дата рождения
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            formatter.parse(dataArr[3]);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Дата рождения должна быт в формате dd.MM.yyyyб а вы ввели " + dataArr[3]);
        }
        //номер телефона
        if (!dataArr[4].matches("[0-9]+")) {
            throw new IllegalArgumentException("Телефон должен содержать только цифры, а вы ввели: " + dataArr[4]);
        }
        ;
        //пол f/m

        if (!(dataArr[5].contains("f") || dataArr[5].contains("m"))) {
            throw new IllegalArgumentException("пол - символ латиницей f или m, а вы ввели " + dataArr[5]);
        }
//      Если всё введено и обработано верно, должен создаться файл с названием,
//      равным фамилии, в него в одну строку должны записаться полученные данные, вида
//      <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//      Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
        String fileName = dataArr[0] + ".txt";

        try( PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            writer.println(data);

        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public static void isLettersInString(String string, String msg) {
        if (!string.chars().allMatch(Character::isLetter)) {
            throw new IllegalArgumentException("В поле \"" + msg + "\" допускаются только буквы, а вы ввели " + string);
        }
    }
}