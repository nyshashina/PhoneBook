package PhoneBook.Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class TextUIViewTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void readPhoneBookRecord() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        String inString = String.format("      %n bar%n%n");
        ByteArrayInputStream in = new ByteArrayInputStream(inString.getBytes(StandardCharsets.UTF_8));
//        TextUIView ttuiv1 = new TextUIView(in, out);
//        assertEquals("", ttuiv1.nextLine());
//        assertEquals("bar", ttuiv1.nextLine());

        TextUIView ttuiv = new TextUIView(in, out);
        String phbr = ttuiv.readPhoneBookRecord();
        assertEquals("bar", phbr);
    }

    @Test
    void println() {
    }

    @Test
    void nextLine() {
    }
}