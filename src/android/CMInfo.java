package cminfo;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;

import org.apache.cordova.CallbackContext;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;

import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class echoes a string called from JavaScript.
 */
public class CMInfo extends CordovaPlugin {

  private static final String TAG = "CMInfo";

  private Activity mActivity;

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    this.mActivity = cordova.getActivity();
  }

  @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getCommonInfo")) {
          JSONObject r = new JSONObject();
          r.put("customername",preferences.getString("customerName",""));//获取本地客户名
          r.put("device", getModel());                        //手机类型
          r.put("factory", this.getDeviceBrand());             //厂家
          r.put("uniqueId", this.getUuid());                  //唯一标识
          r.put("sysVersion", this.getOSVersion());           //系统版本
          r.put("appVersion", this.getVersionCode(mActivity));//APP版本
		  r.put("appVersionName", this.getVersionName(mActivity));//app版本名称

          callbackContext.success(r);
        }
        else {
            return false;
        }
        return true;
    }




  //手机型号  m1 metal
  public String getModel() {
    String model = android.os.Build.MODEL;

    return model;
  }

  //系统版本 5.1
  public String getOSVersion() {
    String osversion = android.os.Build.VERSION.RELEASE;

    return osversion;
  }

  //获取手机唯一识别码 baced2dba082b6f8
  public String getUuid() {
    String uuid = Settings.Secure.getString(this.cordova.getActivity().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

    return uuid;
  }


  //手机厂商 meizu
  public  String getDeviceBrand() {
    String brand = android.os.Build.BRAND;
    return brand;
  }



  //SDK 版本
public String getSDKVersion() {
    @SuppressWarnings("deprecation")
    String sdkversion = android.os.Build.VERSION.SDK;
    return sdkversion;
}

//获取现在时间 @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
public String getStringDate() {
    Date currentTime = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateString = formatter.format(currentTime);
    return dateString;
}
//获取App版本名
public String getVersionName(Context context) {
	 {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
  //获取App版本号
  public  int getVersionCode(Context context) {
    int v = getPackageInfo(context).versionCode;
    Log.d(TAG,"v："+ v);
    return v;
  }
//获取App包名
public String getPackaName(Context context) {
    return getPackageInfo(context).packageName;
}

private PackageInfo getPackageInfo(Context context) {
    PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
    return pi;
}

}
