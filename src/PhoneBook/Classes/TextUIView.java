package PhoneBook.Classes;

import PhoneBook.Interfaces.IPhoneBookView;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUIView implements IPhoneBookView {

    InputStream in = System.in;
    PrintStream out = System.out;
    Scanner scanner;

    public TextUIView(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
        scanner = new Scanner(this.in);
    }

    public String readPhoneBookRecord() {
        while (true) {
            displayMessage("Введите данные в произвольном порядке, разделенные пробелом: " +
                    "ФИО, дата рождения, номер телефона, пол. Выход - exit/выход");
            String s = nextLine();
            if (!s.isEmpty()) {
                return s;
            }
        }
    }

    public void displayMessage(String line) {
        out.println(line);
    }

    String nextLine() {
        return scanner.nextLine().trim();
    }
}
