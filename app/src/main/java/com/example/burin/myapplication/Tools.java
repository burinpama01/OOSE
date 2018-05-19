package com.example.burin.myapplication;

import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;

public class Tools {

    public Point getDisplaySize(AppCompatActivity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        return point;
    }

    public ArrayList<Integer> getImageArray(){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(R.drawable.beach);
        arr.add(R.drawable.beach1);
        arr.add(R.drawable.beach2);
        arr.add(R.drawable.bridge);
        arr.add(R.drawable.crabs);
        arr.add(R.drawable.lake);
        arr.add(R.drawable.moutain);
        arr.add(R.drawable.moutain1);
        arr.add(R.drawable.mountain2);
        arr.add(R.drawable.ex);
        arr.add(R.drawable.sea);
        arr.add(R.drawable.sea1);
        arr.add(R.drawable.sakuraroadview);
        arr.add(R.drawable.beach3);
        arr.add(R.drawable.bridgebeach);
        arr.add(R.drawable.view);
        arr.add(R.drawable.waterfall);
        return arr;
    }

    public ArrayList<Integer> getImageArrayProfile(){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(R.drawable.beach);
        arr.add(R.drawable.beach1);
        arr.add(R.drawable.beach2);
        arr.add(R.drawable.bridge);
        arr.add(R.drawable.crabs);
        arr.add(R.drawable.lake);
        arr.add(R.drawable.moutain);
        arr.add(R.drawable.moutain1);
        arr.add(R.drawable.mountain2);
        arr.add(R.drawable.exprofile);
        arr.add(R.drawable.sea);
        arr.add(R.drawable.sea1);
        arr.add(R.drawable.sakuraroadview);
        arr.add(R.drawable.beach3);
        arr.add(R.drawable.bridgebeach);
        arr.add(R.drawable.view);
        arr.add(R.drawable.waterfall);
        return arr;
    }

    public double getHeightSizeFromView(View view){
        return view.getHeight();
    }

    public double getWeightSizeFromView(View view){
        return view.getHeight();
    }

    public double getViewSize(int size) {
        if (size > 200) {
            return size / 200;
        } else {
            return size;
        }
    }

}
