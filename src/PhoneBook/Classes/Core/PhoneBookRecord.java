package PhoneBook.Classes.Core;
import java.time.DateTimeException;
import java.time.Year;

public class PhoneBookRecord {
    String surname;
    String firstName;
    String patronymic;
    char gender;
    String birthDate;
    long phoneNumber;

    public PhoneBookRecord(String surname, String firstName, String patronymic, char gender, String birthDate,
                           long phoneNumber) {
        setSurname(surname); // TODO аннотация, документация, комментарии
        setFirstName(firstName);
        setPatronymic(patronymic);
        setBirthDate(birthDate);
        setGender(gender);
        setPhoneNumber(phoneNumber);
    }

    public String toString() {
        return String.format("%s %s %s %s %d %c",
                surname.replaceAll("[ \\t]", "_"),
                firstName.replaceAll("[ \\t]", "_"),
                patronymic.replaceAll("[ \\t]", "_"),
                birthDate, phoneNumber, gender);
    }

    public static PhoneBookRecord fromString(String s) {
        String[] ss = s.split("[ \\t]");
        if (ss.length == 6) {
            int snIndex = surNameIndex(ss);
            String sn = ss[snIndex];
            String fn = ss[snIndex + 1];
            String p = ss[snIndex + 2];

            String phn = ss[phoneNumberIndex(ss)];
            long pn = Long.parseLong(phn);

            String bd = ss[birthDateIndex(ss)];

            String g = ss[genderIndex(ss)];
            char gen = g.charAt(0);

            return new PhoneBookRecord(sn, fn, p, gen, bd, pn);

        } else if (ss.length > 6) {
            throw new IllegalArgumentException("Данных больше, чем необходимо");
        }
        throw new IllegalArgumentException("Данных меньше, чем необходимо");
    }

    static int surNameIndex(String[] ss) {
        for (int i = 0; i < ss.length - 2; i++) {
            try {
                checkNameSymbols(ss[i]);
                checkNameSymbols(ss[i + 1]);
                checkNameSymbols(ss[i + 2]);
            } catch (IllegalArgumentException e) {
                continue;
            }
            return i;
        }
        throw new IllegalArgumentException("Фамилия, имя или отчество введены неверно");
    }

    static int birthDateIndex(String[] ss) {
        int bdIndex = -1;
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].contains(".")) {
                checkBirthDate(ss[i]);
                if (bdIndex == -1) {
                    bdIndex = i;
                } else {
                    throw new IllegalArgumentException("Дата рождения введена дважды");
                }
            }
        }
        if (bdIndex == -1) {
            throw new IllegalArgumentException("Дата рождения не распознана");
        }
        return bdIndex;
    }

    static int phoneNumberIndex(String[] ss) {
        int pnIndex = -1;
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].matches("^\\d+$")) {
                long pn;
                try {
                    pn = Long.parseLong(ss[i]);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Номер телефона не распознан");
                }
                checkPhoneNumber(pn);
                if (pnIndex == -1) {
                    pnIndex = i;
                } else {
                    throw new IllegalArgumentException("Номер телефона введен дважды");
                }
            }
        }
        if (pnIndex == -1) {
            throw new IllegalArgumentException("Номер телефона не распознан");
        }
        return pnIndex;
    }

    static int genderIndex(String[] ss) {
        int gIndex = -1;
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].equals("m") || ss[i].equals("f")) {
                if (gIndex == -1) {
                    gIndex = i;
                } else {
                    throw new IllegalArgumentException("Пол введен дважды");
                }
            }
        }
        if (gIndex == -1) {
            throw new IllegalArgumentException("Пол не распознан");
        }
        return gIndex;
    }

    public void setSurname(String surname) {
        checkNull(surname);
        checkNameSymbols(surname);
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        checkNull(firstName);
        checkNameSymbols(firstName);
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        checkNull(patronymic);
        checkNameSymbols(patronymic);
        this.patronymic = patronymic;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        checkGender(gender);
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        checkPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setBirthDate(String birthDate) {
        checkNull(birthDate);
        checkBirthDate(birthDate);
        this.birthDate = birthDate;
    }

    static void checkNull(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Значение не может быть null");
        }
    }

    static void checkNameSymbols(String s) {
        if (!s.matches("^[A-Za-zА-Яа-я]+([ -][A-Za-zА-Яа-я]+)*$")) {
            throw new IllegalArgumentException("Значение содержит недопустимые символы");
        }
    }

    static void checkBirthDate(String s) {
        String[] ds = s.split("\\.");
        int day;
        int month;
        int year;
        if (ds.length == 3) {
            try {
                day = Integer.parseInt(ds[0]);
                month = Integer.parseInt(ds[1]);
                year = Integer.parseInt(ds[2]);
            } catch (Exception e) {
                throw new IllegalArgumentException("Неверно указана дата");
            }
        } else {
            throw new IllegalArgumentException("Неверно указана дата");
        }
        checkBirthDateValues(day, month, year);
    }

    static void checkBirthDateValues(int day, int month, int year) {
        if ((year < 1900) || (year > Year.now().getValue())) {
            throw new DateTimeException("Неверно указан год рождения");
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if((day < 1 || day > 31)) {
                    throw new DateTimeException("Неверно указан день рождения");
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if((day < 1 || day > 30)) {
                    throw new DateTimeException("Неверно указан день рождения");
                }
                break;
            case 2:
                if (Year.isLeap(year)) {
                    if((day < 1 || day > 29)) {
                        throw new DateTimeException("Неверно указан день рождения");
                    }
                } else {
                    if((day < 1 || day > 28)) {
                        throw new DateTimeException("Неверно указан день рождения");
                    }
                }
                break;
            default:
                throw new DateTimeException("Неверно указан месяц рождения");
        }
    }

    static void checkGender(char gender) {
        if (!((gender == 'f') || (gender == 'm'))){
            throw new IllegalArgumentException("Неверно указан пол");
        }
    }

    static void checkPhoneNumber(long phoneNumber) {
        long l = phoneNumber;
        int count = 0;
        while (l > 0) {
            l = l / 10;
            count++;
        }
        if ((count < 2) || (count > 18)) {
            throw new IllegalArgumentException("Неверно указан номер телефона");
        }
    }
}
