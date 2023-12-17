import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GHW1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные пользователя (Фамилия Имя Отчество датарождения номертелефона пол):");
        String input = scanner.nextLine();

        String[] data = input.split(" ");

        if (data.length != 6) {
            System.out.println("Введено неверное количество данных. Ожидается 6 параметров: Фамилия Имя Отчество датарождения номертелефона пол");
            return;
        }

        String lastName = data[0];
        String firstName = data[1];
        String patronymic = data[2];

        String dateOfBirth = data[3];
        try {
            validateDateOfBirth(dateOfBirth);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String phoneNumber = data[4];
        try {
            validatePhoneNumber(phoneNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String gender = data[5];
        try {
            validateGender(gender);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String filename = lastName.toLowerCase() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(lastName + firstName + patronymic + " " + dateOfBirth + " " + phoneNumber + gender);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Данные успешно записаны в файл: " + filename);
    }

    private static void validateDateOfBirth(String dateOfBirth) {
        String[] parts = dateOfBirth.split("\\.");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается формат yyyy.mm.dd");
        }

        try {
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            // Проверка допустимых значений для года, месяца и дня
            // Например, проверяем, что год больше 1900 и меньше текущего года
            // А также, что месяц находится в диапазоне от 1 до 12, а день - от 1 до 31

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается формат yyyy.mm.dd");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        try {
            long number = Long.parseLong(phoneNumber);

            // Проверка, что номер телефона состоит из 10 цифр, например

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат номера телефона. Ожидается целое беззнаковое число без форматирования");
        }
    }

    private static void validateGender(String gender) {
        if (!gender.equalsIgnoreCase("f") && !gender.equalsIgnoreCase("m")) {
            throw new IllegalArgumentException("Неверный формат пола. Ожидается символ латиницей f или m");
        }
    }
}