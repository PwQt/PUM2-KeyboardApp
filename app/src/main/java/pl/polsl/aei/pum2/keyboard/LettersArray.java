package pl.polsl.aei.pum2.keyboard;

import android.app.Application;

/**
 * Created by pawel on 19-12-2017.
 */

public class LettersArray extends Application {
    public static String[] charArray = {"","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T","U", "V", "W", "X", "Y", "Z",""};
    private static LettersArray singleton;

    public static LettersArray getInstance(){
        return singleton;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        singleton = this;
    }
}
