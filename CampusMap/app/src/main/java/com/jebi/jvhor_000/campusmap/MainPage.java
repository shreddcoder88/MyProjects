package com.jebi.jvhor_000.campusmap;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MainPage extends Fragment{

    private  String page;
    private  View view;
    listListener listCallBack;

    interface listListener{
        void polygonMove(String name);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listCallBack = (listListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement ListListener");
        }
    }

    public MainPage(){}


    public MainPage newInstance(String page ) {
        MainPage f = new MainPage();

        Bundle args = new Bundle();
        args.putString("page", page);
        f.setArguments(args);

        return f;

    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        page = getArguments() != null ? getArguments().getString("page") : "blah";
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        view = inflater.inflate(R.layout.main_page, container, false);
        //view.findViewById(R.id.building_image).setVisibility(View.GONE);
        setPage();

        return view;

    }


    void setPage(){

        TextView textView = (TextView) view.findViewById(R.id.page_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.building_image);
        imageView.setAdjustViewBounds(true);
        ListView listView = (ListView) view.findViewById(R.id.list_item);

        switch (page){
            case "Sweeney":
                textView.setText(R.string.sweeney);
                imageView.setImageResource(R.drawable.sweeney);
                listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.sweeney)));
                break;
            case "Grappone":
                textView.setText(R.string.grappone);
                imageView.setImageResource(R.drawable.grappone);

                break;
            case "Little Hall":
                textView.setText(R.string.littleHall);
                imageView.setImageResource(R.drawable.little_hall);
                listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.littleHall)));

                break;
            case "Farnum":
                textView.setText(R.string.farnum);
                imageView.setImageResource(R.drawable.farnum_hall);
                listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.farnum)));

                break;
            case "Macrury":
                textView.setText(R.string.macrury);
                imageView.setImageResource(R.drawable.macrury);
                listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.macrury)));
                break;
            case "Mcauliffe":
                textView.setText(R.string.mcauliffe);
                listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.mcauliffe)));
                // no picture
                break;
            case "Library":
                textView.setText(R.string.library);
                imageView.setImageResource(R.drawable.library);
                listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.library)));
                break;
            default:
                //TODO Add to backstack
                textView.setText(R.string.directory);
                listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.listview, getResources().getStringArray(R.array.directory)));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView tv = (TextView) view;
                        listCallBack.polygonMove(tv.getText().toString());

                    }
                });
                view.findViewById(R.id.building_image).setVisibility(View.GONE);
                break;




        }

    }




}
