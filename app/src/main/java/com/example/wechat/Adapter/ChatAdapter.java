package com.example.wechat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wechat.Beans.ChatBeans;
import com.example.wechat.R;

import java.util.List;

public class ChatAdapter extends BaseAdapter {
    private List<ChatBeans> chatBeansList;
    private String name;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<ChatBeans> chatBeansList, String name, Context context) {
        this.chatBeansList = chatBeansList;
        this.name = name;
        this.context = context;
        this.inflater = LayoutInflater.from(context.getApplicationContext());
    }

    public void CheckData(List<ChatBeans> chatBeans) {
        this.chatBeansList = chatBeans;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return chatBeansList.size();
    }

    @Override
    public Object getItem(int position) {
        return chatBeansList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(name.equals(chatBeansList.get(position).getName()) ? R.layout.chat_right : R.layout.chat_left,parent,false);
            viewHolder = new ViewHolder(convertView);
        } else if (!name.equals(chatBeansList.get(position).getName())) {
            convertView = inflater.inflate(name.equals(chatBeansList.get(position).getName()) ? R.layout.chat_right : R.layout.chat_left,parent,false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.chat.setText(chatBeansList.get(position).getChat());
        viewHolder.name.setText(chatBeansList.get(position).getName());
        viewHolder.time.setText(chatBeansList.get(position).getTime());
        return convertView;
    }
    class ViewHolder {
        TextView chat,name,time;
        public ViewHolder(View convertView) {
            chat = convertView.findViewById(R.id.chat);
            name = convertView.findViewById(R.id.name);
            time = convertView.findViewById(R.id.data);
            convertView.setTag(this);
        }
    }
}
