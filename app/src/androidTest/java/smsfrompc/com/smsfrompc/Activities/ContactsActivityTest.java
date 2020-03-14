package smsfrompc.com.smsfrompc.Activities;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import smsfrompc.com.smsfrompc.Entities.Classes.Contact;
import smsfrompc.com.smsfrompc.Managers.PermissionManager;
import smsfrompc.com.smsfrompc.R;

import static org.junit.Assert.*;

public class ContactsActivityTest {

    @Rule
    public ActivityTestRule<ContactsActivity> contactsActivityTestRule = new ActivityTestRule<ContactsActivity>(ContactsActivity.class);

    private ContactsActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        PermissionManager.permissionsGranted = false;
        mActivity = contactsActivityTestRule.getActivity();
    }

    @Test
    public void testContactsActivity() {
        // Test will be executed without granting permissions, so it is expected for him to be redirected to not_granted_permissions_layout
        View view = mActivity.findViewById(R.id.notGrantedPermissionsBodyTextView);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.notGrantedPermissionsBodyTextView);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}