package com.example.bukola_omotoso.quiz_app;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by bukola_omotoso on 01/03/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;
    public PagerAdapter(FragmentManager fm, int numOfTabs)  {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position)   {
        switch(position)    {
            case 0:
                QuizHomeFragment quizHomeFragment = new QuizHomeFragment();
                return quizHomeFragment;
            case 1:
                HistoryFragment historyFragment = new HistoryFragment();
                return historyFragment;
            case 2:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount()   {
        return numOfTabs;
    }
}
