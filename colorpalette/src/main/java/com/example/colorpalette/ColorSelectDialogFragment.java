package com.example.colorpalette;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import com.example.colorpalette.core.ColorPalette;

public class ColorSelectDialogFragment extends DialogFragment {


    private ColorPalette colorPalette;
    private Button cancleBtn,confirmBtn;
    private int color;

    private OnColorSelectListener onColorSelectListener;


    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        return super.onCreateDialog(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout rootLayout=(LinearLayout)inflater.inflate(R.layout.zhou_fragment_color,container,false);
        colorPalette= (ColorPalette) rootLayout.findViewById(R.id.zhou_fragment_color);

        colorPalette.setLastColor(color);
        colorPalette.setCurrColor(color);

        cancleBtn = (Button) rootLayout.findViewById(R.id.zhou_cancel_btn);
        confirmBtn = (Button) rootLayout.findViewById(R.id.zhou_confim_btn);

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onColorSelectListener != null){
                    color = colorPalette.getSelectColor();
                    onColorSelectListener.onSelectFinish(color);
                }
                dismiss();
            }
        });

        return rootLayout;
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
        this.color = color;
    }
}
