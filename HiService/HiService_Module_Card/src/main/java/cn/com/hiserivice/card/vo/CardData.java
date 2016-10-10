package cn.com.hiserivice.card.vo;

import java.util.List;

/**
 * Created by gaojicai1 on 2016/10/10.
 */

public class CardData {

    /**
     * id : 2
     * name : 嗨修年卡
     * type : 1
     * url :
     * icon : http://static.haixiuauto.com/attachment/images/app330/card/xck.png?v=20160803
     * desc : 包含30次洗车，免费搭电，上门抢修等服务
     */

    private List<CardListBean> cardList;

    public List<CardListBean> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardListBean> cardList) {
        this.cardList = cardList;
    }

    public static class CardListBean {
        private int id;
        private String name;
        private int type;
        private String url;
        private String icon;
        private String desc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
