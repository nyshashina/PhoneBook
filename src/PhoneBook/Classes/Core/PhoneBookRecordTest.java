package PhoneBook.Classes.Core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookRecordTest {

    PhoneBookRecord r;

    @BeforeEach
    void setUp() {
        r = new PhoneBookRecord("Ivanov", "Ivan", "Ivanovich", 'm',
                "01.01.1990", 89997773322L);
    }

    @Test
    void PhoneBookRecord() {
        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> new PhoneBookRecord("Ivan2ov", "Ivan", "Ivanovich", 'm',
                        "01.01.1990", 89997773322L)
        ).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> new PhoneBookRecord("Ivanov", "Iv2an", "Ivanovich", 'm',
                        "01.01.1990", 89997773322L)
        ).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> new PhoneBookRecord("Ivanov", "Ivan", "Ivano2vich", 'm',
                        "01.01.1990", 89997773322L)
        ).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> new PhoneBookRecord("Ivanov", "Ivan", "Ivanovich", '3',
                        "01.01.1990", 89997773322L)
        ).getMessage().contains("Неверно указан пол"));

        assertTrue(assertThrows(DateTimeException.class,
                () -> new PhoneBookRecord("Ivanov", "Ivan", "Ivanovich", 'm',
                        "32.01.1990", 89997773322L)
        ).getMessage().contains("Неверно указан день рождения"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> new PhoneBookRecord("Ivanov", "Ivan", "Ivanovich", 'm',
                        "1.01.1990", 1L)
        ).getMessage().contains("Неверно указан номер телефона"));

    }

    @Test
    void _toString() {
        assertEquals("Ivanov Ivan Ivanovich 01.01.1990 89997773322 m", r.toString());

        r.setSurname("Iv Anov");
        assertEquals("Iv_Anov Ivan Ivanovich 01.01.1990 89997773322 m", r.toString());

        r.setSurname("Iv An Ov");
        assertEquals("Iv_An_Ov Ivan Ivanovich 01.01.1990 89997773322 m", r.toString());

        r.setFirstName("I Van");
        assertEquals("Iv_An_Ov I_Van Ivanovich 01.01.1990 89997773322 m", r.toString());

        r.setFirstName("I v An");
        assertEquals("Iv_An_Ov I_v_An Ivanovich 01.01.1990 89997773322 m", r.toString());

        r.setPatronymic("Iv Anovich");
        assertEquals("Iv_An_Ov I_v_An Iv_Anovich 01.01.1990 89997773322 m", r.toString());

        r.setPatronymic("Iva no vich");
        assertEquals("Iv_An_Ov I_v_An Iva_no_vich 01.01.1990 89997773322 m", r.toString());
    }

    @Test
    void setSurname() {

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setSurname("123")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setSurname(null)).getMessage().contains("Значение не может быть null"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setSurname("Vene)v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setSurname("Vene--v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setSurname("Vene  v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setSurname("-Vene v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setSurname("Vene v-")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setSurname("Vene v ")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setSurname(" Vene v")).getMessage().contains("Значение содержит недопустимые символы"));
        
        r.setSurname("Petrov");
        assertEquals("Petrov", r.getSurname());

        r.setSurname("Pet-rov");
        assertEquals("Pet-rov", r.getSurname());

        r.setSurname("Pet rov");
        assertEquals("Pet rov", r.getSurname());
    }

    @Test
    void getFirstName() {
        assertEquals("Ivan", r.getFirstName());

        r.setFirstName("Victor");
        assertEquals("Victor", r.getFirstName());
    }

    @Test
    void setFirstName() {

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName("123")).getMessage().contains("Значение содержит недопустимые символы"));


        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName(null)).getMessage().contains("Значение не может быть null"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName("Vic%tor")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName("Vic%tor")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName("Vene--v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName("Vene  v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName("-Vene v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName("Vene v-")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName("Vene v ")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setFirstName(" Vene v")).getMessage().contains("Значение содержит недопустимые символы"));

        r.setFirstName("Petrov");
        assertEquals("Petrov", r.getFirstName());

        r.setFirstName("Pet-rov");
        assertEquals("Pet-rov", r.getFirstName());

        r.setFirstName("Pet rov");
        assertEquals("Pet rov", r.getFirstName());

        r.setFirstName("Victor");
        assertEquals("Victor", r.getFirstName());
    }

    @Test
    void getPatronymic() {
        assertEquals("Ivanovich", r.getPatronymic());

        r.setPatronymic("Victorovich");
        assertEquals("Victorovich", r.getPatronymic());
    }

    @Test
    void setPatronymic() {
        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPatronymic("123")).getMessage().contains("Значение содержит недопустимые символы"));
        
        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPatronymic(null)).getMessage().contains("Значение не может быть null"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPatronymic("Vic%torovich")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPatronymic("Vene--v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPatronymic("Vene  v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPatronymic("-Vene v")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPatronymic("Vene v-")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPatronymic("Vene v ")).getMessage().contains("Значение содержит недопустимые символы"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPatronymic(" Vene v")).getMessage().contains("Значение содержит недопустимые символы"));

        r.setPatronymic("Petrov");
        assertEquals("Petrov", r.getPatronymic());

        r.setPatronymic("Pet-rov");
        assertEquals("Pet-rov", r.getPatronymic());

        r.setPatronymic("Pet rov");
        assertEquals("Pet rov", r.getPatronymic());
       
        r.setPatronymic("Victorovich");
        assertEquals("Victorovich", r.getPatronymic());
    }

    @Test
    void getGender() {
        assertEquals('m', r.getGender());

        r.setGender('f');
        assertEquals('f', r.getGender());
    }

    @Test
    void setGender() {
        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setGender('2')).getMessage().contains("Неверно указан пол"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setGender('.')).getMessage().contains("Неверно указан пол"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setGender('a')).getMessage().contains("Неверно указан пол"));

        r.setGender('f');
        assertEquals('f', r.getGender());

        r.setGender('m');
        assertEquals('m', r.getGender());
    }

    @Test
    void getBirthDate() {
        assertEquals("01.01.1990", r.getBirthDate());

        r.setBirthDate("2.11.1987");
        assertEquals("2.11.1987", r.getBirthDate());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(89997773322L, r.getPhoneNumber());

        r.setPhoneNumber(88005553555L);
        assertEquals(88005553555L, r.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        r.setPhoneNumber(88005553555L);
        assertEquals(r.getPhoneNumber(), 88005553555L);

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPhoneNumber(1L)).getMessage().contains("Неверно указан номер телефона"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setPhoneNumber(1213141516171819101L)).getMessage().contains("Неверно указан номер телефона"));
    }

    @Test
    void getSurname() {
        assertEquals("Ivanov", r.getSurname());

        r.setSurname("Petrov");
        assertEquals("Petrov", r.getSurname());
    }

    @Test
    void setBirthDate() {
        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setBirthDate("123")).getMessage().contains("Неверно указана дата"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setBirthDate(null)).getMessage().contains("Значение не может быть null"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setBirthDate("Vic%torovich")).getMessage().contains("Неверно указана дата"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setBirthDate("10.10.10.10")).getMessage().contains("Неверно указана дата"));

        assertTrue(assertThrows(DateTimeException.class,
                () -> r.setBirthDate("32.10.1980")).getMessage().contains("Неверно указан день рождения"));

        assertTrue(assertThrows(DateTimeException.class,
                () -> r.setBirthDate("25.13.1980")).getMessage().contains("Неверно указан месяц рождения"));

        assertTrue(assertThrows(DateTimeException.class,
                () -> r.setBirthDate("25.03.666")).getMessage().contains("Неверно указан год рождения"));

        assertTrue(assertThrows(DateTimeException.class,
                () -> r.setBirthDate("29.02.1966")).getMessage().contains("Неверно указан день рождения"));

        assertTrue(assertThrows(IllegalArgumentException.class,
                () -> r.setBirthDate("ff.02.1966")).getMessage().contains("Неверно указана дата"));

        assertTrue(assertThrows(DateTimeException.class,
                () -> r.setBirthDate("30.02.1968")).getMessage().contains("Неверно указан день рождения"));

        assertTrue(assertThrows(DateTimeException.class,
                () -> r.setBirthDate("31.11.1968")).getMessage().contains("Неверно указан день рождения"));

        r.setBirthDate("2.11.1987");
        assertEquals("2.11.1987", r.getBirthDate());

        r.setBirthDate("29.02.2004");
        assertEquals("29.02.2004", r.getBirthDate());
    }
}