package Homework.Controller.JSON_Test;

import java.util.List;

/**
 * Package: Homework.Controller.JSON_Test
 * Author: caowei
 * Date: 17:51 2018/12/27
 * CopyRight: NanChang University Software Project School caowei
 * Description:
 */
public class MusicPageBean {
    private String allPages;
    private String allNum;
    private String maxResult;
    private String ret_code;
    private String currentPage;
    private String notice;
    private List<MusicContentList> contentlist;

    public String getAllPages() {
        return allPages;
    }

    public void setAllPages(String allPages) {
        this.allPages = allPages;
    }

    public String getAllNum() {
        return allNum;
    }

    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }

    public String getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(String maxResult) {
        this.maxResult = maxResult;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public List<MusicContentList> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<MusicContentList> contentlist) {
        this.contentlist = contentlist;
    }

    @Override
    public String toString() {
        return "MusicPageBean{" +
                "allPages='" + allPages + '\'' +
                ", allNum='" + allNum + '\'' +
                ", maxResult='" + maxResult + '\'' +
                ", ret_code='" + ret_code + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", notice='" + notice + '\'' +
                ", contentlist=" + contentlist +
                '}';
    }
}
