package com.aba.core.robolectric;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.aba.core.R;

import org.mockito.InjectMocks;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.robolectric.Shadows.shadowOf;


@SuppressWarnings("unchecked")
public abstract class ActivityTestBase<ACTIVITY extends Activity> extends RobolectricTestBase {

    @InjectMocks
    public ACTIVITY activity;

    public ShadowActivity shadowActivity;

    public ActivityController<ACTIVITY> controller;

    protected void createActivity() {
        createActivityWithIntentAndSavedState(null, null);
    }

    protected void createActivityWithIntent(Intent intent) {
        createActivityWithIntentAndSavedState(intent, null);
    }

    protected void createActivityWithIntentAndSavedState(Intent startIntent, Bundle savedState) {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Class classType = (Class<ACTIVITY>) type;
        controller = Robolectric.buildActivity(classType, startIntent);
        activity = controller.get();
        shadowActivity = shadowOf(activity);
        initMocks(this);
        controller.create(savedState);
//        activity.setTheme(R.style.Theme_AppCompat);
    }

    protected void whenIsClicked(View view) {
        view.performClick();
    }

}
