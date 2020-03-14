package smsfrompc.com.smsfrompc.Activities;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import smsfrompc.com.smsfrompc.R;

import static org.junit.Assert.*;

public class ScheduleSmsActivityTest {

    @Rule
    public ActivityTestRule<ScheduleSmsActivity> scheduleSmsActivityTestRule = new ActivityTestRule<ScheduleSmsActivity>(ScheduleSmsActivity.class);

    private ScheduleSmsActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = scheduleSmsActivityTestRule.getActivity();
    }

    @Test
    public void testScheduleSmsActivity() {
        View view = mActivity.findViewById(R.id.nameTextView);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.numberTextView);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.scheduleSmsBtn);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.scheduledTime);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.messageText);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}