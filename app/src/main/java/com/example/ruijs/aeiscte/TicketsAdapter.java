package com.example.ruijs.aeiscte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TicketsAdapter extends BaseAdapter {
    public static ArrayList<Card> cardsList;
    private ViewHolder holder;
    private LayoutInflater mInflater;

    public TicketsAdapter(Context context, ArrayList<Card> cards)
    {
        cardsList = cards;
        mInflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return cardsList.size();
    }

    @Override
    public Object getItem(int position) {
        return cardsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = mInflater.inflate(R.layout.card_layout, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.news_name);
            holder.category = (TextView) convertView.findViewById(R.id.news_category);
            holder.date = (TextView) convertView.findViewById(R.id.news_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(cardsList.get(position).getName());
        holder.category.setText(cardsList.get(position).getCategory());
        holder.date.setText(cardsList.get(position).getDate());

        return convertView;
    }

    static class ViewHolder
    {
        TextView name;
        TextView category;
        TextView date;
    }
}
