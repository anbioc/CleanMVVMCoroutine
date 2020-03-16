package com.aba.core.robolectric;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import org.robolectric.Robolectric;

import dagger.android.AndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Utilities for creating Fragments for testing. This is basically a copy of SupportFragmentTestUtil
 * from Robolectric but with support for AppCompatActivity which SupportFragmentTestUtil does not
 * have for some reason. So If we update Robolectric, we may need to ensure this file is updated too.
 */
public final class AppCompatSupportFragmentTestUtil {

    private AppCompatSupportFragmentTestUtil() {

    }

    public static void startFragment(Fragment fragment) {
        buildSupportFragmentManager(FragmentUtilActivity.class)
                .beginTransaction().add(fragment, null).commit();
    }

    public static void startFragment(Fragment fragment, Class<? extends FragmentActivity> fragmentActivityClass) {
        buildSupportFragmentManager(fragmentActivityClass)
                .beginTransaction().add(fragment, null).commit();
    }

    public static void startVisibleFragment(Fragment fragment) {
        buildSupportFragmentManager(FragmentUtilActivity.class)
                .beginTransaction().add(1, fragment, null).commit();
    }

    public static void startVisibleFragment(Fragment fragment, Class<? extends FragmentActivity> fragmentActivityClass, int containerViewId) {
        buildSupportFragmentManager(fragmentActivityClass)
                .beginTransaction().add(containerViewId, fragment, null).commit();
    }

    private static FragmentManager buildSupportFragmentManager(Class<? extends FragmentActivity> fragmentActivityClass) {
        FragmentActivity activity = Robolectric.setupActivity(fragmentActivityClass);
        return activity.getSupportFragmentManager();
    }

    private static class FragmentUtilActivity extends AppCompatActivity implements HasSupportFragmentInjector {

        private AndroidInjector<Fragment> fakeFragmentInjector = instance -> {
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            LinearLayout view = new LinearLayout(this);
            view.setId(1);
            setContentView(view);
        }

        @Override
        public AndroidInjector<Fragment> supportFragmentInjector() {
            return fakeFragmentInjector;
        }
    }
}
