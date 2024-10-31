package lk.ijse.CropMonitoringSystemBackend.util;

import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean userIdMatcher(String userId) {
        String regexForUserID = "^USER-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(userId).matches();
    }
}
