package com.example.com.yk_zhlianxi_demo1;

import java.util.List;

/**
 * Created by 老赵的拯救者 on 2018/4/28.
 */

public class FourBean {
    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-06-01 09:00","title":"美腿美女  Lynn","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-06/01/08/20170601085254761-2381913.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7830733-0-1.html"},{"ctime":"2017-06-01 17:00","title":"李多惠  室内写真","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-06/01/16/2017060116130561-5058976.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7831741-0-1.html"},{"ctime":"2017-06-01 18:00","title":"青春年华-21","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-06/01/07/20170601072856261-239867.gif","url":"http://bbs.voc.com.cn/mm/meinv-7830626-0-1.html"},{"ctime":"2017-06-01 18:00","title":"王熙然.小透明[07P][贴图]","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-05/31/22/201705312214174571-2497165.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7830368-0-1.html"},{"ctime":"2017-06-01 18:00","title":"成熟妩媚[10P][贴图]","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-05/31/22/20170531222940721-2497165.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7830384-0-1.html"},{"ctime":"2017-06-01 18:00","title":"韩国第一美少女化身守望先锋[10P][贴图]","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-05/31/22/201705312231217951-2497165.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7830388-0-1.html"},{"ctime":"2017-06-01 18:00","title":"18岁的天空不下雨","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-06/01/07/201706010733136581-239867.gif","url":"http://bbs.voc.com.cn/mm/meinv-7830632-0-1.html"},{"ctime":"2017-06-01 21:00","title":"星光花[09P]","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-05/28/22/201705282212431651-2497165.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7826657-0-1.html"},{"ctime":"2017-06-01 23:00","title":"青春年华-15","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-05/27/08/201705270847575941-239867.gif","url":"http://bbs.voc.com.cn/mm/meinv-7824761-0-1.html"},{"ctime":"2017-06-02 08:00","title":"青花元素","description":"华声美女","picUrl":"http://image.hnol.net/c/2017-05/26/21/201705262134459161-619336.jpg","url":"http://bbs.voc.com.cn/mm/meinv-7824425-0-1.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-06-01 09:00
         * title : 美腿美女  Lynn
         * description : 华声美女
         * picUrl : http://image.hnol.net/c/2017-06/01/08/20170601085254761-2381913.jpg
         * url : http://bbs.voc.com.cn/mm/meinv-7830733-0-1.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
