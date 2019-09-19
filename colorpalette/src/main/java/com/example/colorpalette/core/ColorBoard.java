package com.example.colorpalette.core;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.colorpalette.R;

import java.util.jar.Attributes;

public class ColorBoard extends LinearLayout {

    public static final int widthSize=5;//色块横向数量
    public static final int heightSize=4;//色块纵向数量
    public static final int colorHeight=100;//色块最小高度
    public static final int colorWidth=100;//色块最小宽度
    private LayoutParams params,ppp;
    private LinearLayout[] layouts;
    private View[][] colors;//色块
    private int[][] colorValue;//色块的颜色
    private OnColorSelectListener onColorSelectListener;

    public ColorBoard(Context context){
        this(context,null);
    }

    public ColorBoard(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }

    public ColorBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        layouts = new LinearLayout[heightSize];
        ppp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);

        for (int i = 0;i < heightSize;i++){
            layouts[i] = new LinearLayout(context);
            layouts[i].setOrientation(HORIZONTAL);
            addView(layouts[i],ppp);
        }

        params=new LayoutParams(colorWidth,colorHeight,1);
        params.setMargins(5,5,5,5);

        colors = new View[heightSize][widthSize];
        colorValue = new int[heightSize][widthSize];

        for(int i = 0;i < heightSize;i++){
            for(int j = 0;j < widthSize;j++){
                colors[i][j] = new View(context);
                colors[i][j].setClickable(true);
                layouts[i].addView(colors[i][j],params);
                final int finalI = i;
                final int finalJ = j;
                colors[i][j].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onColorSelectListener != null){
                            onColorSelectListener.onColorSelect(colorValue[finalI][finalJ]);
                            Log.d("colorCoard",""+colorValue[finalI][finalJ]);
                        }
                    }
                });
            }
        }

        colorValue[0][0]=getResources().getColor(R.color.one_one);
        colorValue[0][1]=getResources().getColor(R.color.one_two);
        colorValue[0][2]=getResources().getColor(R.color.one_three);
        colorValue[0][3]=getResources().getColor(R.color.one_four);
        colorValue[0][4]=getResources().getColor(R.color.one_five);
        colorValue[1][0]=getResources().getColor(R.color.two_one);
        colorValue[1][1]=getResources().getColor(R.color.two_two);
        colorValue[1][2]=getResources().getColor(R.color.two_three);
        colorValue[1][3]=getResources().getColor(R.color.two_four);
        colorValue[1][4]=getResources().getColor(R.color.two_five);
        colorValue[2][0]=getResources().getColor(R.color.three_one);
        colorValue[2][1]=getResources().getColor(R.color.three_two);
        colorValue[2][2]=getResources().getColor(R.color.three_three);
        colorValue[2][3]=getResources().getColor(R.color.three_four);
        colorValue[2][4]=getResources().getColor(R.color.three_five);
        colorValue[3][0]=getResources().getColor(R.color.four_one);
        colorValue[3][1]=getResources().getColor(R.color.four_two);
        colorValue[3][2]=getResources().getColor(R.color.four_three);
        colorValue[3][3]=getResources().getColor(R.color.four_four);
        colorValue[3][4]=getResources().getColor(R.color.four_five);


        resetColor();
    }

    private void resetColor(){
        for(int i = 0;i < heightSize;i++){
            for(int j = 0;j < widthSize;j++){
                colors[i][j].setBackgroundColor(colorValue[i][j]);
            }
        }
    }

    //设置色块颜色
    public void setColors(int[][] cs){
        for(int i = 0;i < heightSize;i++){
            for(int j = 0;j < widthSize;j++){
                colorValue[i][j]=cs[i][j];
                colors[i][j].setBackgroundColor(cs[i][j]);
            }
        }
    }

    //设置某个色块的颜色
    public void setColor(int x,int y,int color){
        colorValue[x][y]=color;
        colors[x][y].setBackgroundColor(color);
    }

    //获取某个色块的颜色
    public int getColor(int x,int y){
        return colorValue[x][y];
    }

    public static interface OnColorSelectListener{
        public void onColorSelect(int color);
    }

    public OnColorSelectListener getOnColorSelectListener() {
        return onColorSelectListener;
    }

    public void setOnColorSelectListener(OnColorSelectListener onColorSelectListener){
        this.onColorSelectListener = onColorSelectListener;
    }
}
