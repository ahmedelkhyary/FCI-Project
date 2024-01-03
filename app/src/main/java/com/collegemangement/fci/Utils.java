package com.collegemangement.fci;

import android.app.AlertDialog;
import android.content.Context;

import com.collegemangement.fci.Api.FciApi;
import com.collegemangement.fci.Api.FciApiClient;


public class Utils {

    public static FciApi getApi() {
        return FciApiClient.getTravelClient().create(FciApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }


}
