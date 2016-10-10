package cn.com.hiserivice.card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import cn.com.hiserivice.card.adapter.CardAdapter;
import cn.com.hiserivice.card.vo.CardData;
import cn.com.hiserivice.hiservice.base.BaseFragment;
import cn.com.hiserivice.hiservice.request.APIs;
import cn.com.hiserivice.hiservice.request.IRequest;
import cn.com.hiserivice.hiservice.request.ReqModule;
import cn.com.hiserivice.hiservice.security.MD5Util;

/**
 * Created by gaojicai1 on 2016/9/26.
 */
public class CardFragment extends BaseFragment {

    private View mRootView;
    private ListView mCardList;

    private CardAdapter mCardAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getCustomNav() != null) {
            getCustomNav().getmMiddleTitleView().setText("卡券");
        }
        mRootView = inflater.inflate(R.layout.fragment_card, container, false);
        mCardList = (ListView)mRootView.findViewById(R.id.lv_card_list);

        getData();

        return mRootView;
    }

    private void getData() {

        String time = System.currentTimeMillis() + "";
        String name = "cqI4mAv2orQr3fJtTJgmaGbA3ifi8eR0" + time + "cqI4mAv2orQr3fJtTJgmaGbA3ifi8eR0";
        String sign = MD5Util.getMD5String(name);

        ReqModule.newRequest()
                .tag(this)
                .api(APIs.card.api)
                .args("appid", "hiandroid")
                .args("timestamp", time)
                .args("sign", sign)
                .args("version", "3.3.1")
                .args("action", "cardList")
                .targetDataType(CardData.class)
                .listener(new IRequest.IListener<CardData>() {

                    @Override
                    public void onReceive(CardData obj) {
                        Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
                        mCardAdapter = new CardAdapter(getActivity(),obj.getCardList());
                        mCardList.setAdapter(mCardAdapter);
                    }

                    @Override
                    public void onFailed(String errorCode, String msg) {
                        Toast.makeText(getActivity(), "失败", Toast.LENGTH_LONG).show();
                    }
                }).send();

    }
}
