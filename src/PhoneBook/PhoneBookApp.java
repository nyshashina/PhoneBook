package PhoneBook;

import PhoneBook.Classes.FileModel;
import PhoneBook.Classes.PhoneBookPresenter;
import PhoneBook.Classes.TextUIView;
import PhoneBook.Interfaces.IPhoneBookModel;
import PhoneBook.Interfaces.IPhoneBookPresenter;
import PhoneBook.Interfaces.IPhoneBookView;

public class PhoneBookApp {
    public static void main (String[] args) {
        IPhoneBookModel model = args.length > 0 ? new FileModel(String.format("%s%%s.txt", args[0])) : new FileModel();
//        IPhoneBookModel model;
//        if (args.length > 0) {
//            model = new FileModel(String.format("%s%%s.txt", args[0]));
//        } else {
//            model = new FileModel();
//        }
        IPhoneBookView view = new TextUIView(System.in, System.out);
        IPhoneBookPresenter presenter = new PhoneBookPresenter(model,view);
        presenter.run();
    }
}
