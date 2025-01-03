package lk.ijse.CropMonitoringSystemBackend.util;

import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean FieldCodeMatcher(String userId) {
        String regexForUserID = "^FIELD-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(userId).matches();
    }

    public static boolean CropCodeMatcher(String cropCode) {
        String regexForCropCode = "^CROP-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropCode);
        return regexPattern.matcher(cropCode).matches();
    }

    public static boolean staffIdMatcher(String staffId) {
        String regexForCropCode = "^STAFF-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropCode);
        return regexPattern.matcher(staffId).matches();
    }

    public static boolean vehicleCodeMatcher(String vehicleCode) {
        String regexForCropCode = "^VEHICLE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropCode);
        return regexPattern.matcher(vehicleCode).matches();
    }

    public static boolean equipmentIdMatcher(String equipmentId) {
        String regexForCropCode = "^EQUIPMENT-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropCode);
        return regexPattern.matcher(equipmentId).matches();
    }

    public static boolean logCodeMatcher(String tempId) {
        String regexForCropCode = "^LOG-\\d{2}$";
        Pattern regexPattern = Pattern.compile(regexForCropCode);
        return regexPattern.matcher(tempId).matches();
    }
}
