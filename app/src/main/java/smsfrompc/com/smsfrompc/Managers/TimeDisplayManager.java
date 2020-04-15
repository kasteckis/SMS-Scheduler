package smsfrompc.com.smsfrompc.Managers;

import smsfrompc.com.smsfrompc.Entities.Classes.Setting;
import smsfrompc.com.smsfrompc.Entities.Types.ScheduleFormatType;

public class TimeDisplayManager {
    public static String transformSecondsToUserSetFormat(String defaultTimeInMs) {
        String returnFormattedTime;

        int defaultTimeInSeconds = Integer.parseInt(defaultTimeInMs) / 1000;
        switch(Setting.ScheduleFormatSetting.getSettingValue()) {
            case ScheduleFormatType.SECONDS:
                returnFormattedTime = Integer.toString(defaultTimeInSeconds) + " s.";
                break;
            case ScheduleFormatType.MINUTES:
                double minutesInt = (double) defaultTimeInSeconds / 60d;
                returnFormattedTime = String.format("%.2f", minutesInt) + " m.";
                break;
            default:
                throw new RuntimeException("Undefined ScheduleFormatSetting flag");
        }
        return returnFormattedTime;
    }
}
