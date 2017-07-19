//http://dangoya.jp/?p=106
package com.example.komoritakeshi.myapp1;

import java.util.ArrayList;
import java.util.List;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChatListFragment extends android.support.v4.app.ListFragment {

    private List<ListItem> list;
    private ArrayAdapter<ListItem> adapter;
    private static final String ARG_PARAM = "page";
    private MyListener3 myListener3;

    public interface MyListener3 {
        public void onClickButton3();
    }

    public ChatListFragment() {
    }

    public static ChatListFragment newInstance(int page) {
        ChatListFragment fragment = new ChatListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = new ArrayList<ListItem>();
        list.add(new ListItem("リスト1"));
        list.add(new ListItem("リスト2"));
        list.add(new ListItem("リスト3"));
        list.add(new ListItem("リスト4"));
        list.add(new ListItem("リスト5"));
        list.add(new ListItem("リスト6"));
        list.add(new ListItem("リスト7"));
        list.add(new ListItem("リスト8"));
        list.add(new ListItem("リスト9"));
        list.add(new ListItem("リスト10"));

        adapter = new ListAdapter(getActivity(), list);
        setListAdapter(adapter);
    }

    private class ListAdapter extends ArrayAdapter<ListItem> {
        private LayoutInflater mInflater;

        public ListAdapter(Context context, List<ListItem> objects) {
            super(context, 0, objects);
            mInflater = (LayoutInflater)context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (context instanceof MyListener3) {
                myListener3 = (MyListener3) context;
            }

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.fragment_chat_list, parent, false);
                holder = new ViewHolder();
                holder.tvListText = (TextView)convertView.findViewById(R.id.list_text);
                holder.btListButton = (Button)convertView.findViewById(R.id.list_button);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            final ListItem item = getItem(position);
            holder.tvListText.setText(item.getListText());
            holder.btListButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (myListener3 != null) {
                        myListener3.onClickButton3();
                    }
                }
            });
            return convertView;
        }
    }

    private class ListItem {
        private String listText = "";

        public ListItem() {
        }

        public ListItem(String listText) {
            this.listText = listText;
        }

        public void setListText(String listText) {
            this.listText = listText;
        }

        public String getListText() {
            return this.listText;
        }
    }

    private class ViewHolder {
        TextView tvListText;
        Button btListButton;
    }
}
