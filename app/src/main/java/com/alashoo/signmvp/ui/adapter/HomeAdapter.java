package com.alashoo.signmvp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alashoo.signmvp.R;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.utils.EaseCommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_FRIENDS = 1;
    private static final int ITEM_TYPE_CONTACT = 2;
    private static final int ITEM_TYPE_CONVERSATION = 3;

    private List<EMConversation> mConversationList;

    public HomeAdapter() {
    }

    public void setList(List<EMConversation> list) {
        if (mConversationList == null) {
            mConversationList = new ArrayList<>();
        } else {
            mConversationList.clear();
        }

        mConversationList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_CONVERSATION) {
            return ConversationHolder.create(parent);
        } else if (viewType == ITEM_TYPE_FRIENDS) {
            return ContactHolder.create(parent);
        } else {
            return ContactHolder.create(parent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ConversationHolder) {
            ((ConversationHolder) holder).bindView(getItem(position - 2));
        } else {
            ((ContactHolder) holder).bindView(getItemViewType(position));
        }
    }

    @Override
    public int getItemCount() {
        return 2 + getListCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_FRIENDS;
        } else if (position == 1) {
            return ITEM_TYPE_CONTACT;
        } else {
            return ITEM_TYPE_CONVERSATION;
        }
    }

    private EMConversation getItem(int position) {
        return mConversationList.get(position);
    }

    private int getListCount() {
        return mConversationList == null ? 0 : mConversationList.size();
    }

    public static class ContactHolder extends RecyclerView.ViewHolder {
        static ContactHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_home_contact, parent, false);
            return new ContactHolder(view);
        }

        @BindView(R.id.item_icon)
        ImageView icon;

        @BindView(R.id.item_title)
        TextView title;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int type) {
            if (type == ITEM_TYPE_CONTACT) {
                icon.setImageResource(R.mipmap.ic_item_contacts);
                title.setText("阿拉秀通讯录");
            } else {
                icon.setImageResource(R.mipmap.ic_item_new_friends);
                title.setText("新朋友");
            }
        }
    }

    public static class ConversationHolder extends RecyclerView.ViewHolder {

        public static ConversationHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_home_contact, parent, false);
            return new ConversationHolder(view);
        }

        @BindView(R.id.item_icon)
        ImageView avatar;

        @BindView(R.id.item_title)
        TextView title;

        public ConversationHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(EMConversation conversation) {
            EMMessage message = conversation.getLastMessage();
            // load avatar
            // set message
            title.setText(EaseCommonUtils.getMessageDigest(message, itemView.getContext()));
        }
    }
}
