package pl.polsl.aei.pum2.keyboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Paweł Kucia & Grzegorz Kubicki on 26-11-2017.
 */

public class SwipeAdapter extends FragmentStatePagerAdapter {
    private String[] charArray = {"","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T","U", "V", "W", "X", "Y", "Z",""};
    public SwipeAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        String element = getCharacter(position);
        bundle.putString("Character",element);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return charArray.length;
    }

    public String getCharacter(int position){
        return charArray[position];
    }
}
