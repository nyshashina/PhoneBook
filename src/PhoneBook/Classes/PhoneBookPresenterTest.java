package PhoneBook.Classes;

import PhoneBook.Classes.Core.PhoneBookRecord;
import PhoneBook.Interfaces.IPhoneBookModel;
import PhoneBook.Interfaces.IPhoneBookView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
class MockModelOk implements IPhoneBookModel {
    PhoneBookRecord record = null;
    @Override
    public void saveRecord(PhoneBookRecord record) throws IOException {
        this.record = record;
    }
}

class MockModelError implements IPhoneBookModel {

    @Override
    public void saveRecord(PhoneBookRecord record) throws IOException {
        throw new IOException("MockModelError IOException");
    }
}

class MockTextUIView extends TextUIView implements IPhoneBookView {

    String message = null;
    boolean r = true;
    public MockTextUIView(InputStream in, PrintStream out) {
        super(in, out);
    }

    public String readPhoneBookRecord() {
        if (r) {
            r = false;
            return "Ivanov Ivan Ivanovich 01.01.1990 89997773322 m";
        } else {
            return "exit";
        }
    }

    @Override
    public void displayMessage(String line) {
        message = line;
    }
}

class PhoneBookPresenterTest {

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    void run_printsStackTrace() {
        MockModelError mme = new MockModelError();
        MockTextUIView v = new MockTextUIView(System.in, System.out);
        PhoneBookPresenter pbp = new PhoneBookPresenter(mme, v);
        pbp.run();
        assertNotNull(v.message);
        assertTrue(v.message.contains("MockModelError IOException"));
        assertTrue(v.message.contains("at PhoneBook.Classes.MockModelError.saveRecord"));
        assertTrue(v.message.contains("at PhoneBook.Classes.PhoneBookPresenter.run"));
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    void run_savesRecord() {
        MockModelOk m = new MockModelOk();
        MockTextUIView v = new MockTextUIView(System.in, System.out);
        PhoneBookPresenter pbp = new PhoneBookPresenter(m, v);
        pbp.run();
        assertNotNull(m.record);
        assertEquals("Ivanov Ivan Ivanovich 01.01.1990 89997773322 m", m.record.toString());
        assertEquals("Данные сохранены", v.message);
    }

    @Test
    void getStackTrace() {
    }
}