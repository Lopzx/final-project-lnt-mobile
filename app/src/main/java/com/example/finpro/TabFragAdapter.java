package com.example.finpro;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabFragAdapter extends FragmentStateAdapter {
    public TabFragAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new ItemFragment(R.layout.fragment_counter);
        Log.w("Position", Integer.toString(position));

        switch (position) {
            case 0 :
                fragment = new counter();
                break;
            case 1 :
                fragment = new ItemFragment(R.layout.fragment_calc_luas);
                break;
            case 2 :
                fragment = new ItemFragment(R.layout.fragment_volume_calculator);
                break;
        }

        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(ItemFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
