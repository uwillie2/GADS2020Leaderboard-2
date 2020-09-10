package com.example.anew.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.anew.fragments.LearningLeadersFragment;
import com.example.anew.fragments.SkillIQLeadersFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int numberoftabs;


    public PageAdapter(@NonNull FragmentManager fm, int numberoftabs) {
        super(fm);
        this.numberoftabs = numberoftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LearningLeadersFragment();
            case 1:
                return new SkillIQLeadersFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberoftabs;
    }
}
