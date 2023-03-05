package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a path of file: ");
        String strPathFile = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(strPathFile))) {

            String line = br.readLine();

            List<Employee> employees = new ArrayList<>();

            while (line != null) {
                String[] employee = line.split(",");

                String name = employee[0];
                String email = employee[1];
                Double salary = Double.parseDouble(employee[2]);

                employees.add(new Employee(name, email, salary));

                line = br.readLine();
            }

            System.out.print("Enter a salary: ");
            Double searchSalary = sc.nextDouble();

            List<String> emails = employees.stream().filter(employee -> employee.getSalary() > searchSalary)
                    .map(employee -> employee.getEmail()).sorted((str1, str2) -> str1.toUpperCase().compareTo(str2.toUpperCase()))
                    .toList();

            System.out.println("Email of peoples whose salary is more than" + String.format("%.2f", searchSalary) + ":");
            emails.forEach(System.out::println);

            System.out.print("Enter with a first word of employee:  ");
            char firstWordName = sc.next().charAt(0);

            double sumSalary = employees.stream().filter(employee -> employee.getName().charAt(0) == firstWordName)
                    .mapToDouble(value -> value.getSalary()).sum();

            System.out.println("Sum of salary of people whose name starts with '" + firstWordName + "': "
                    + String.format("%.2f", sumSalary));

        } catch (IOException e) {

        }
    }
}