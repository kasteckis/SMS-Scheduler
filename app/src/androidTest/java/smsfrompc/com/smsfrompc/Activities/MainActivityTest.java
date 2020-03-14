package smsfrompc.com.smsfrompc.Activities;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import smsfrompc.com.smsfrompc.Entities.Classes.Setting;
import smsfrompc.com.smsfrompc.R;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testMainActivityButtons() {
        View view = mActivity.findViewById(R.id.delaySmsBtn);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.historyBtn);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.settingsBtn);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.aboutBtn);
        assertNotNull(view);
        assertNotNull(Setting.ScheduleFormatSetting);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}