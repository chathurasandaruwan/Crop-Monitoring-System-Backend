package lk.ijse.CropMonitoringSystemBackend.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateFieldCode() {
        return "FIELD-"+ UUID.randomUUID();
    }
    public static String generateCropCode() {
        return "CROP-"+ UUID.randomUUID();
    }
    public static String generateStaffId() {
        return "STAFF-"+ UUID.randomUUID();
    }
    public static String imageToBase64(byte[] image){
        return Base64.getEncoder().encodeToString(image);
    }
}
