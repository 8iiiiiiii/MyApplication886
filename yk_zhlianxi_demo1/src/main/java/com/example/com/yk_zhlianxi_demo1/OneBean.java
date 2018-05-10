package com.example.com.yk_zhlianxi_demo1;

import java.util.List;

/**
 * Created by 老赵的拯救者 on 2018/4/27.
 */

public class OneBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2018-04-27 17:00","title":"索尼发布2017财报，实现历史最高7349亿日元利润","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/bc41744a09384895945d1690163973b420180427170053.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/17/DGDPNBML00097U7T.html"},{"ctime":"2018-04-27 15:36","title":"安徽成功生产出0.12毫米厚电子触控玻璃，创世界","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/e738a33cc3114f14bd1229e14f219e3220180427153617.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/15/DGDKT50H00097U7T.html"},{"ctime":"2018-04-27 13:41","title":"汉王将一项海外商标转让给苹果，将获800万美元","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/9af4239910d04a52a5d053e99fc5241020180427134138.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/13/DGDEAJBA00097U7T.html"},{"ctime":"2018-04-27 10:27","title":"辍学生盖茨回哈佛与学生交流:如果再年轻我去做A","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/e8ba860b7ef44e2f909640c2ae08632120180427102703.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/10/DGD36E0E00097U7T.html"},{"ctime":"2018-04-27 10:46","title":"董明珠：不在意别人评价，保持初心干自己坚持的","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/c8d6ea15aa2449e8a9c8bd463d8f985d20180427104623.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/10/DGD49LD600097U7T.html"},{"ctime":"2018-04-27 10:52","title":"贾跃亭FF91已到中国海关，将于5月在国内亮相","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/fe823faed7cf4a1ab06a5956b1038dd920180427105236.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/10/DGD4L3TE00097U7T.html"},{"ctime":"2018-04-27 09:01","title":"中国工程院院士倪光南:北斗都能突破 何况芯片？","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/ff29efd39d3e4a47b8a2a79a84846d6a20180427074054.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/09/DGCU96J200097U7T.html"},{"ctime":"2018-04-27 07:02","title":"这个双足机器人不仅能穿越火场，还能使用滑板车","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/2a998640595f4e32883cecc3592ade0620180426224057.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/07/DGCNFNDO00097U7T.html"},{"ctime":"2018-04-27 07:02","title":"业内给苹果提议让它再次改变世界 有人说功能学","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/175adec0073549c7b40698dce775d8ad20180426164656.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/07/DGCNG59800097U7T.html"},{"ctime":"2018-04-27 07:03","title":"马斯克洛杉矶地下隧道存诸多漏洞 遭MIT专家质疑","description":"网易IT","picUrl":"http://cms-bucket.nosdn.127.net/ac3632bcbaa74285bea9525ddac7171f20180426163130.png?imageView&thumbnail=200y140","url":"http://tech.163.com/18/0427/07/DGCNGTGS00097U7T.html"}]
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
         * ctime : 2018-04-27 17:00
         * title : 索尼发布2017财报，实现历史最高7349亿日元利润
         * description : 网易IT
         * picUrl : http://cms-bucket.nosdn.127.net/bc41744a09384895945d1690163973b420180427170053.png?imageView&thumbnail=200y140
         * url : http://tech.163.com/18/0427/17/DGDPNBML00097U7T.html
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
