package com.example.wechat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wechat.Beans.UserBeans;
import com.example.wechat.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private List<UserBeans> userBeansList;
    private LayoutInflater inflater;

    public UserAdapter(Context context,List<UserBeans> userBeans) {
        this.userBeansList = userBeans;
        this.inflater = LayoutInflater.from(context.getApplicationContext());
    }

    @Override
    public int getCount() {
        return userBeansList.size();
    }

    @Override
    public Object getItem(int position) {
        return userBeansList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.user,parent,false);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.user = convertView.findViewById(R.id.user);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(userBeansList.get(position).getName());
        viewHolder.user.setText(userBeansList.get(position).getUser());
        return convertView;
    }
    class ViewHolder {
        TextView name,user;
    }
}
