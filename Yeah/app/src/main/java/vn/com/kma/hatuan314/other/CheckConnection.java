package vn.com.kma.hatuan314.other;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class CheckConnection {
    public static boolean haveNetworkConnection(Context context){
        boolean haveConnectWifi = false;
        boolean haveConnectMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni: netInfo){
            if (ni.getTypeName().equalsIgnoreCase("WIFI")){
                if(ni.isConnected()){
                    haveConnectWifi = true;
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE")){
                if(ni.isConnected()){
                    haveConnectMobile = true;
                }
            }
        }
        return haveConnectWifi || haveConnectMobile;
    }

    public static void ShowToast_Short(Context context, String thongbao){
        Toasty.error(context, thongbao, Toast.LENGTH_SHORT).show();
    }
}
