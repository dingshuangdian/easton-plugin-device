package cminfo;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * 作者：隔壁骆叔叔 on 2017/6/12.
 * 邮箱：android_luochen@163.com
 */
public class MobilePhoneType {


    public MobilePhoneType(){

    }

    public static final String SYS_EMUI = "sys_emui";    //华为
    public static final String SYS_MIUI = "sys_miui";    //小米
    public static final String SYS_OTHER = "sys_other";  //其他
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";

    public static String getSystem(){
        String SYS = null;
        try {
            Properties prop= new Properties();
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            if(prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null){
                SYS = SYS_MIUI;//小米
            }else if(prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                    ||prop.getProperty(KEY_EMUI_VERSION, null) != null
                    ||prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null){
                SYS = SYS_EMUI; //华为
            }else {
                SYS = SYS_OTHER;
            }
        } catch (IOException e){
            e.printStackTrace();
            return SYS;
        }
        return SYS;
    }








}
