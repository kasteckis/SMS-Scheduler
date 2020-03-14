package smsfrompc.com.smsfrompc.Activities;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import smsfrompc.com.smsfrompc.R;

import static org.junit.Assert.*;

public class AboutActivityTest {

    @Rule
    public ActivityTestRule<AboutActivity> aboutActivityTestRule = new ActivityTestRule<AboutActivity>(AboutActivity.class);

    private AboutActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = aboutActivityTestRule.getActivity();
    }

    @Test
    public void testMainActivityButtons()
    {
        View view = mActivity.findViewById(R.id.aboutMainTextView);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.aboutBodyTextView);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}