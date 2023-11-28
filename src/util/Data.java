package util;

import java.time.LocalDate;

public class Data {

    public Data() {
    }

    public static String getData() {
        return String.valueOf(LocalDate.now());
    }
}
