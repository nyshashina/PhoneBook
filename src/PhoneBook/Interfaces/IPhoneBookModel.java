package PhoneBook.Interfaces;

import PhoneBook.Classes.Core.PhoneBookRecord;

import java.io.IOException;

public interface IPhoneBookModel {
    void saveRecord(PhoneBookRecord record) throws IOException;
}
