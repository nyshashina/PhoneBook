package PhoneBook.Classes;

import PhoneBook.Classes.Core.PhoneBookRecord;
import PhoneBook.Interfaces.IPhoneBookModel;

import java.io.FileWriter;
import java.io.IOException;

public class FileModel implements IPhoneBookModel {

    String filePathTemplate = "%s.txt";

    public FileModel(String filePathTemplate) {
        this.filePathTemplate = filePathTemplate;
    }

    public FileModel() {

    }

    public void saveRecord(PhoneBookRecord record) throws IOException {
        FileWriter fw = makeFileWriter(String.format(filePathTemplate, record.getSurname()));
        try {
            fw.append(record.toString());
            fw.append('\n');
        } finally {
            fw.close();
        }
    }

    FileWriter makeFileWriter(String path) throws IOException {
        return new FileWriter(path, true);
    }
}
