package PhoneBook.Classes;

import PhoneBook.Classes.Core.PhoneBookRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TestFileModel extends FileModel {

    FileWriter lastFileWriter;

    public FileWriter getLastFileWriter() {
        return lastFileWriter;
    }

    @Override
    FileWriter makeFileWriter(String path) throws IOException {
        lastFileWriter = super.makeFileWriter(path);
        return lastFileWriter;
    }
}

class FileModelTest {
    PhoneBookRecord r;

    @BeforeEach
    void setUp() {
        r = new PhoneBookRecord("Ivanov", "Ivan", "Ivanovich", 'm',
                "01.01.1990", 89997773322L);
    }

    @Test
    void saveRecord() throws IOException {
        FileModel model = new FileModel("ac/dc/%s.txt");
        assertThrows(IOException.class, () -> model.saveRecord(r));
        TestFileModel model1 = new TestFileModel();
        File file = new File(String.format("%s.txt", r.getSurname()));

        try {
            model1.saveRecord(r);
            model1.saveRecord(r);
            BufferedReader bufferreader = new BufferedReader(new FileReader(file));
            assertEquals(r.toString(), bufferreader.readLine());
            assertThrows(IOException.class, () -> model1.getLastFileWriter().flush());

            file.setWritable(false);
            assertThrows(IOException.class, () -> model.saveRecord(r));

            file.setWritable(true);
            model1.saveRecord(r);
        } finally {
            file.delete();
        }
    }
}