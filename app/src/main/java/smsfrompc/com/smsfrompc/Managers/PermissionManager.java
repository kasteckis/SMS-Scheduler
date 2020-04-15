package smsfrompc.com.smsfrompc.Managers;

import android.view.View;
import android.view.ViewGroup;

import smsfrompc.com.smsfrompc.Activities.MainActivity;

public class PermissionManager {
    public static boolean permissionsGranted = false;

    public static boolean didUserGrantPermissions()
    {
        // TODO: If more permissions will appear, use this.

        return permissionsGranted;
    }
}
