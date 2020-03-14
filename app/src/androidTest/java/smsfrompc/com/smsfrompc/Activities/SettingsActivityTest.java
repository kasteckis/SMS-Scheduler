package smsfrompc.com.smsfrompc.Activities;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import smsfrompc.com.smsfrompc.R;

import static org.junit.Assert.*;

public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> settingsActivityTestRule = new ActivityTestRule<SettingsActivity>(SettingsActivity.class);

    private SettingsActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = settingsActivityTestRule.getActivity();
    }

    @Test
    public void testMainActivityButtons()
    {
        View view = mActivity.findViewById(R.id.saveSettingsBtn);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.scheduleFormatSpinner);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.sheculeFormatSpinnerLabel);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}