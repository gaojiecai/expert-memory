package cn.com.hiserivice.card.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.hiserivice.card.R;
import cn.com.hiserivice.card.vo.CardData;

/**
 * Created by gaojicai1 on 2016/10/10.
 */

public class CardAdapter extends BaseAdapter {

    private Context context;
    private List<CardData.CardListBean> cardList;
    private LayoutInflater mInflater = null;

    public CardAdapter(Context context, List<CardData.CardListBean> cardList) {
        this.context = context;
        this.cardList = cardList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cardList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (view == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            view = mInflater.inflate(R.layout.card_item, null);
            holder.img = (ImageView) view.findViewById(R.id.iv_card_type);
            holder.title = (TextView) view.findViewById(R.id.item_card_type);
            holder.info = (TextView) view.findViewById(R.id.item_card_content);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //holder.img.setBackgroundResource((Integer)cardList.get(i).getName());
        holder.title.setText(cardList.get(i).getName());
        holder.info.setText(cardList.get(i).getDesc());

        return view;
    }

    //ViewHolder静态类
    static class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView info;
    }
}
