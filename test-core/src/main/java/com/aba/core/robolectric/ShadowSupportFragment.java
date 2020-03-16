package com.aba.core.robolectric;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Based on code from Robolectric ShadowActivity class.
 * https://github.com/robolectric/robolectric/blob/master/robolectric-shadows/shadows-core/src/main/resources/org/robolectric/shadows/ShadowActivity.java.vm
 */
@Implements(Fragment.class)
public class ShadowSupportFragment {

    private List<ShadowActivity.IntentForResult> startedActivitiesForResults = new ArrayList<>();
    private Map<Intent.FilterComparison, Integer> intentRequestCodeMap = new HashMap<>();

    @Implementation
    public void startActivityForResult(Intent intent, int requestCode) {
        intentRequestCodeMap.put(new Intent.FilterComparison(intent), requestCode);
        startedActivitiesForResults.add(new ShadowActivity.IntentForResult(intent, requestCode));
        // FIXME: `startActivity` is removed on Robolectric 3.8 and causing failures for a couple of
        //  tests which are Ignored as `@Ignore("Robolectric 3.8 issue")`
        //  ShadowApplication.getInstance().startActivity(intent);
    }

    public ShadowActivity.IntentForResult getNextStartedActivityForResult() {
        if (startedActivitiesForResults.isEmpty()) {
            return null;
        } else {
            return startedActivitiesForResults.remove(0);
        }
    }

    public ShadowActivity.IntentForResult peekNextStartedActivityForResult() {
        if (startedActivitiesForResults.isEmpty()) {
            return null;
        } else {
            return startedActivitiesForResults.get(0);
        }
    }
}
