package Homework.Controller.JSON_Test;


import com.google.gson.Gson;
import com.show.api.ShowApiRequest;
import java.util.List;

/**
 * Package: Homework.Controller
 * Author: caowei
 * Date: 14:19 2018/12/27
 * CopyRight: NanChang University Software Project School caowei
 * Description:
 */
public class Internet_Search {
    private String SearchStr ="";

    public String getSearchStr() {
        return SearchStr;
    }

    public void setSearchStr(String searchStr) {
        SearchStr = searchStr;
    }

    public Internet_Search(String searchStr){
        this.SearchStr = searchStr;
    }

    /**
     * 传入用户输入的关键词并且通过api接收传回的JSON
     * @param str
     * @return
     */
    public String JsonAnalyze(String str){
        String res=new ShowApiRequest("http://route.showapi.com/213-1","83271","3f07ed4077a4414e974e22edf9d21227")
                .addTextPara("keyword",str)
                .addTextPara("page","1")
                .post();
        return res;
    }

    /**
     * 利用谷歌的Gson解析JSON数据并将其存入newLists
     * @param newLists
     * @param JsonData
     * @return
     */
    public List<MusicContentList> ParseJsonWithGson(List<MusicContentList> newLists, String JsonData){
        Gson gson = new Gson();
        HomeMusicBean homeMusicBean = gson.fromJson(JsonData,HomeMusicBean.class);
        if (homeMusicBean.getShowapi_res_body().getRet_code() == "-1"){
            System.out.println("网络异常!");
        }else {
            for (int i=0; i<homeMusicBean.getShowapi_res_body().getPagebean().getContentlist().size(); i++){
                newLists.add(homeMusicBean.getShowapi_res_body().getPagebean().getContentlist().get(i));
            }
        }
        return newLists;
    }
}
