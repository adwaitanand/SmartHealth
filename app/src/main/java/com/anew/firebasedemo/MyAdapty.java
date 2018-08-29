package com.anew.firebasedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adwaitanand on 03-04-2018.
 */

public class MyAdapty extends RecyclerView.Adapter<MyAdapty.ViewHolder> {
    private List<Person>donorList;
    private Context context;

    public MyAdapty(List<Person> donorList, Context context) {
        this.donorList = donorList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    Person person=donorList.get(position);
        holder.nametv.setText(person.getPname());
        holder.agetv.setText("Age: "+person.getPage());
        holder.numtv.setText("Number: "+person.getPnumber());
    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nametv;
        public TextView agetv;
        public TextView numtv;

        public ViewHolder(View itemView) {
            super(itemView);
            nametv=(TextView)itemView.findViewById(R.id.DnrName);
            agetv=(TextView)itemView.findViewById(R.id.DnrAge);
            numtv=(TextView)itemView.findViewById(R.id.DnrNumber);

        }
    }
}
