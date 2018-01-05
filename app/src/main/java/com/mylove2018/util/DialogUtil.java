package com.mylove2018.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.mylove2018.R;


/**
 * Created by ying on 17-11-15.
 */

public class DialogUtil {

    private final String TAG = "DialogUtil";

    private Activity context;


    AlertDialog dialog;




    public interface DialogItemClickListener {
        void delete();

        void editor();
    }

    public interface SelectListener {
        void url();

        void file();
    }

    NiftyDialogBuilder dialogBuilder;

    public  void showDialog(Context context, String title, final OnSelectListener onSelectListener) {
        dialogBuilder = NiftyDialogBuilder.getInstance(context);

        dialogBuilder
                .withTitle("警告")                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage(title)                     //.withMessage(null)  no Msg
//                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#1976d2")                               //def  | withDialogColor(int resid)
                .withIcon(context.getResources().getDrawable(R.mipmap.ic_launcher))
                .withDuration(300)                                          //def
                //def Effectstype.Slidetop
                .withButton1Text("取消")                                      //def gone
                .withButton2Text("确定")                                  //def gone
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectListener.cancel();
                        dialogBuilder.dismiss();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectListener.confirm();
                        dialogBuilder.dismiss();
                    }

                })
                .show();
    }

    public interface OnSelectListener {
        void confirm();

        void cancel();
    }

    public void showTimePicker(Activity context, String title, final OnSelectListener onSelectListener) {

    }
}
