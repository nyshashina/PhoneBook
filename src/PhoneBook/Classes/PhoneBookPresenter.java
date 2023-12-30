package PhoneBook.Classes;

import PhoneBook.Classes.Core.PhoneBookRecord;
import PhoneBook.Interfaces.IPhoneBookModel;
import PhoneBook.Interfaces.IPhoneBookPresenter;
import PhoneBook.Interfaces.IPhoneBookView;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class PhoneBookPresenter implements IPhoneBookPresenter {
    IPhoneBookModel model;
    IPhoneBookView view;
    public PhoneBookPresenter(IPhoneBookModel model, IPhoneBookView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        while (true) {
            String s = view.readPhoneBookRecord();
            if (s.equals("exit") || s.equals("выход")) {
                return;
            }
            PhoneBookRecord r = null;
            try {
                r = PhoneBookRecord.fromString(s);
            } catch (Exception e) {
                view.displayMessage(e.getMessage());
            }
            if (r != null) {
                try {
                    model.saveRecord(r);
                    view.displayMessage("Данные сохранены");
                } catch (IOException e) {
                    view.displayMessage(getStackTrace(e));
                }
            }
        }
    }

    static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
