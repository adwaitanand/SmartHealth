package com.anew.firebasedemo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adwaitanand on 09-02-2018.
 */

public class PersonList extends ArrayAdapter {
    Activity context;
    List<Person> personList;
    public PersonList(Activity context,List<Person> personList) {
        super(context, R.layout.list_layout,personList);
        this.context=context;
        this.personList=personList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View list=inflater.inflate(R.layout.list_layout,null,true);
        TextView persName=(TextView)list.findViewById(R.id.persName);
        TextView persCity=(TextView)list.findViewById(R.id.persCity);
        Person person=personList.get(position);
        persName.setText(person.getPname());
//        persCity.setText(person.getPcity());
        return super.getView(position, convertView, parent);

    }
}
