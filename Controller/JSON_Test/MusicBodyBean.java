package Homework.Controller.JSON_Test;

/**
 * Package: Homework.Controller.JSON_Test
 * Author: caowei
 * Date: 17:45 2018/12/27
 * CopyRight: NanChang University Software Project School caowei
 * Description:
 */
public class MusicBodyBean {
    private MusicPageBean pagebean;
    private String ret_code;

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public MusicPageBean getPagebean() {
        return pagebean;
    }

    public void setPagebean(MusicPageBean pagebean) {
        this.pagebean = pagebean;
    }

    @Override
    public String toString() {
        return "MusicBodyBean{" +
                "pagebean=" + pagebean +
                ", ret_code='" + ret_code + '\'' +
                '}';
    }
}
