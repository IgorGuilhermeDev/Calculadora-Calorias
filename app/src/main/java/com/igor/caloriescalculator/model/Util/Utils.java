package com.igor.caloriescalculator.model.Util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.igor.caloriescalculator.R;

public class Utils {

    /**
     * Mostra uma mensagem customizada para o usuário
     * @param message Mensagem a ser mostrada pelo Toast
     * @param type indica o tipo do toast 1 para erro 2 para aviso
     */
    public static void showCustomizedToast(Context context, int type, String message, View toastStyle){
        Toast toast = new Toast(context);
        ImageView toastImg = toastStyle.findViewById(R.id.iv_toast);
        TextView toastText = toastStyle.findViewById(R.id.tv_toast);
        toastText.setText(message);
        modifyToast(context, type, toastStyle.findViewById(R.id.container_toast), toastImg);
        toast.setView(toastStyle);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Modifica a estrutura de um toast
     * @param type Indica o tipo do toast, 1 para erro 2 para aviso
     * @param toastContainer referencia para o layout do toast
     * @param toastImg referencia para a imagem do toast
     */
    private static void modifyToast(Context context, int type, View toastContainer, ImageView toastImg){
        switch (type){
            case 1:
                toastContainer.setBackground(context.getDrawable(R.drawable.toast_error));
                toastImg.setImageResource(R.drawable.ic_baseline_error_24);
                break;
            case 2:
                toastContainer.setBackground(context.getDrawable(R.drawable.toast_warning));
                toastImg.setImageResource(R.drawable.ic_baseline_warning_24);
                break;
        }
    }
}
