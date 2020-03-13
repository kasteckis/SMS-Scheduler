package smsfrompc.com.smsfrompc;

import android.view.View;
import android.view.ViewGroup;

import smsfrompc.com.smsfrompc.Activities.MainActivity;

public class PermissionManager {
    public static boolean permissionsGranted = false;

    public static boolean didUserGrantPermissions()
    {
        if(permissionsGranted)
            return true;
        return false;
    }
}
