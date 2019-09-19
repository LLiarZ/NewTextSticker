package com.example.colorpalette;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import com.example.colorpalette.core.ColorPalette;

public class ColorSelectDialog {

    private AlertDialog dialog;
    private ColorPalette colorPalette;
    private OnColorSelectListener onColorSelectListener;
    private int color=-1;

    public ColorSelectDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        colorPalette = new ColorPalette(context);
        colorPalette.setLastColor(Color.BLUE);

        builder.setView(colorPalette);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(onColorSelectListener != null){
                    color = colorPalette.getSelectColor();
                    onColorSelectListener.onSelectFinish(color);
                }
            }
        });
        builder.setNegativeButton("取消",null);
        dialog = builder.create();

    }

    public void show(){
        if(color==-1){
            color = Color.BLACK;
        }
        colorPalette.setLastColor(color);
        colorPalette.setCurrColor(color);
        dialog.show();
    }

    public static interface OnColorSelectListener{
        public void onSelectFinish(int color);
    }

    public OnColorSelectListener getOnColorSelectListener(){
        return onColorSelectListener;
    }

    public void setOnColorSelectListener(OnColorSelectListener onColorSelectListener){
        this.onColorSelectListener = onColorSelectListener;
    }

    public void setLastColor(int color){
        colorPalette.setLastColor(color);
    }
}
