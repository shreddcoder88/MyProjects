package com.jebi.jvhor_000.campusmap;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jvhor_000 on 5/8/2017.
 */

public class FloorPlan extends Fragment{
    String building;
    private  View view;
    ImageSwitcher is;


    public FloorPlan(){}

    public FloorPlan newInstance(String building){
        FloorPlan f = new FloorPlan();

        Bundle args = new Bundle();
        args.putString("name", building);
        f.setArguments(args);

        return f;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        building = getArguments() != null ? getArguments().getString("name") : null;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.floor_plan, container, false);
        if (building != null)
        setpage();

        return view;

    }

    void setpage() {

        final ArrayList numFloors = new ArrayList<>();
        TextView tv = (TextView) view.findViewById(R.id.page_text);
        is = (ImageSwitcher) view.findViewById(R.id.image_floorplan);
        final ListView floorButton = (ListView) view.findViewById(R.id.floor_button);

        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView image = new ImageView(getContext());
                final ImageMatrixTouchHandler mImageZoom = new ImageMatrixTouchHandler(getContext());
                mImageZoom.setDragOnPinchEnabled(true);
                image.setAdjustViewBounds(true);
                image.setOnTouchListener(mImageZoom);
                return image;
            }
        });

        switch (building){

            case "Sweeney":
                tv.setText(R.string.sweeney);
                numFloors.addAll(Arrays.asList(R.drawable.sweeney_03, R.drawable.sweeney_02, R.drawable.sweeney_01));
                floorButton.setAdapter( new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.sweeney_floors)));
                is.setImageResource((int) numFloors.get(0));
                floorButton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        is.setImageResource((int) numFloors.get(position));

                       // click(position, numFloors);
                    }
                });
                break;

            case "Macrury":
                tv.setText(R.string.macrury);
                numFloors.addAll(Arrays.asList(R.drawable.macrury_02, R.drawable.macrury_01_north, R.drawable.macrury_south_01, R.drawable.macrury_01));
                floorButton.setAdapter( new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.macrury_floors)));
                is.setImageResource((int) numFloors.get(0));
                floorButton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        click(position, numFloors);
                    }
                });
                break;

            case "Farnum":
                tv.setText(R.string.farnum);
                numFloors.add(R.drawable.farnum);
                is.setImageResource((int) numFloors.get(0));
                floorButton.setAdapter( new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.farnum_floor)));
                floorButton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        click(position, numFloors);
                    }
                });
                break;

            case "Little Hall":
                tv.setText(R.string.littleHall);
                numFloors.addAll(Arrays.asList(R.drawable.little_hall_02, R.drawable.littlehall));
                floorButton.setAdapter( new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.littlehall_floors)));
                is.setImageResource((int) numFloors.get(0));
                floorButton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        click(position, numFloors);
                    }
                });
                break;

            case "Library":
                tv.setText(R.string.library);
                numFloors.add(R.drawable.library_floor);
                is.setImageResource((int) numFloors.get(0));
                floorButton.setAdapter( new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.library_floors)));
                floorButton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        click(position, numFloors);
                    }
                });
                break;
            default:
                break;



        }
    }

    void click(int floor, ArrayList<Integer> draw){

        is.setImageResource(draw.get(floor));

    }


}
