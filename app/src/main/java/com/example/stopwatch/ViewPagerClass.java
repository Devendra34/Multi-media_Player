package com.example.stopwatch;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
public class ViewPagerClass extends FragmentPagerAdapter {

private Context context;
    public ViewPagerClass(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Music";
            case  1:
                return "Photo";
            case 2:
                return "Vedio";
            default:
                return "Error";
        }
    }

    @Override
    public Fragment getItem(int position) {
        MyFrament myFrament = new MyFrament(context);
        Bundle bundle = new Bundle();
        switch (position){
            case 0:
                bundle.putString("message","Music");
                break;
            case  1:
                bundle.putString("message","Photo");
                break;
            case 2:
                bundle.putString("message","Vedio");
        }
        myFrament.setArguments(bundle);
        return myFrament;
    }
    @Override
    public int getCount() {
        return 3;
    }
}
