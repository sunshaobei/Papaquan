package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.alibaba.wxlib.util.IWxCallback;

import java.util.ArrayList;

/**
 * Created by sunshaobei on 2017/3/31.
 */

public class RequestpermissionUtil {
    private static final String TAG = "RequestPermissionUtil";
    public static final int REQUEST_PERMISSIONS = 1;
    public static final int MANAGER_OVERLAY_CODE = 2;
    private static IWxCallback mCallback;

    public RequestpermissionUtil() {
    }

    public static void requestSDCardAndRecordPermission(Context activity, IWxCallback callback) {
        setCallback(callback);
        if (Build.VERSION.SDK_INT >= 23) {
            int writeSDCardPermission = activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            int recordPermission = activity.checkSelfPermission("android.permission.RECORD_AUDIO");
            ArrayList permissions = new ArrayList();
            if (writeSDCardPermission != 0) {
                permissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }

            if (recordPermission != 0) {
                permissions.add("android.permission.RECORD_AUDIO");
            }

            if (permissions.size() > 0) {
                String[] params = (String[]) permissions.toArray(new String[permissions.size()]);
                ActivityCompat.requestPermissions((Activity) activity, params, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    public static void requestSDCardCameraAndRecordPermission(Activity activity, IWxCallback callback) {
        setCallback(callback);
        if (Build.VERSION.SDK_INT >= 23) {
            int writeSDCardPermission = activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            int recordPermission = activity.checkSelfPermission("android.permission.RECORD_AUDIO");
            int cameraPermission = activity.checkSelfPermission("android.permission.CAMERA");
            ArrayList permissions = new ArrayList();
            if (writeSDCardPermission != 0) {
                permissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }

            if (recordPermission != 0) {
                permissions.add("android.permission.RECORD_AUDIO");
            }

            if (cameraPermission != 0) {
                permissions.add("android.permission.CAMERA");
            }

            if (permissions.size() > 0) {
                String[] params = (String[]) permissions.toArray(new String[permissions.size()]);
                activity.requestPermissions(params, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    public static void requestCameraPermission(Fragment fragment, IWxCallback callback) {
        setCallback(callback);
        if (Build.VERSION.SDK_INT >= 23) {
            int cameraPermission = fragment.getActivity().checkSelfPermission("android.permission.CAMERA");
            if (cameraPermission != 0) {
                fragment.requestPermissions(new String[]{"android.permission.CAMERA"}, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }
    public static void requestCameraPermission(Activity activity, IWxCallback callback) {
        setCallback(callback);
        if (Build.VERSION.SDK_INT >= 23) {
            int cameraPermission = activity.checkSelfPermission("android.permission.CAMERA");
            if (cameraPermission != 0) {
                activity.requestPermissions(new String[]{"android.permission.CAMERA"}, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    public static void requestReadSdCardPermission(Activity activity, IWxCallback callback) {
        setCallback(callback);
        if (Build.VERSION.SDK_INT >= 23) {
            int readSdCardPermission = activity.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
            if (readSdCardPermission != 0) {
                activity.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    public static void requestWriteSdCardPermission(Activity activity, IWxCallback callback) {
        setCallback(callback);
        if (Build.VERSION.SDK_INT >= 23) {
            int readSdCardPermission = activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            if (readSdCardPermission != 0) {
                activity.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }

    public static void requestPermissions(Fragment fragment, IWxCallback callback) {
        setCallback(callback);
        if (Build.VERSION.SDK_INT >= 23) {
            byte permissionGranted = 0;
            int writeSDCardPermission = fragment.getActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
            int readPhoneStatePermission = fragment.getActivity().checkSelfPermission("android.permission.READ_PHONE_STATE");
            ArrayList permissions = new ArrayList();
            if (writeSDCardPermission != permissionGranted) {
                permissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }

            if (readPhoneStatePermission != permissionGranted) {
                permissions.add("android.permission.READ_PHONE_STATE");
            }

            if (permissions.size() > 0) {
                String[] params = (String[]) permissions.toArray(new String[permissions.size()]);
                fragment.requestPermissions(params, 1);
            } else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }
    public static void requestGPSPermissions(Activity activity, IWxCallback callback) {
        setCallback(callback);
        if (Build.VERSION.SDK_INT >= 23) {
            int requestGPSPermissions = activity.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION");
            if (requestGPSPermissions != 0) {
                activity.requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"},1);
            }else {
                handleSuccess();
            }
        } else {
            handleSuccess();
        }

    }
    public static boolean requestGPSPermissions(Application application) {
        if (Build.VERSION.SDK_INT >= 23) {
            int requestGPSPermissions = application.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION");
            if (requestGPSPermissions != 0) {
                return false;
            }else {
                return true;
            }
        } else {
            return true;
        }

    }

    private static void setCallback(IWxCallback callback) {
        mCallback = callback;
    }

    private static void handleSuccess() {
        IWxCallback callback = getAndClearCallback();
        if (callback != null) {
            callback.onSuccess(new Object[0]);
        }

    }

    public static IWxCallback getAndClearCallback() {
        IWxCallback tempCallback = mCallback;
        mCallback = null;
        return tempCallback;
    }
}
