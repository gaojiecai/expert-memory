package cn.com.hiserivice.hiservice.navi;

/**
 * Created by gaojicai1 on 2016/9/23.
 */
public class Modules {

    public static class HOME {
        public static final String HomeActivity = "cn.com.hiservice.HomeActivity";
        public static final String ARG_SUB_MODULE = "sub_module";

        public static class SubModules {
            public static final String HOME_PAGE = "home";
            public static final String CARD_PAGE = "card";
            public static final String FIND_PAGE = "find";
            public static final String MIME_PAGE = "mime";
        }
    }
}
