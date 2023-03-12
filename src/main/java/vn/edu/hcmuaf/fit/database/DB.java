package vn.edu.hcmuaf.fit.database;

import vn.edu.hcmuaf.fit.bean.AbBean;
import vn.edu.hcmuaf.fit.bean.Log;

public class DB {
    private static DB install;
    public static DB me(){
        if (install==null) install = new DB();
        return install;
    }
    private DB(){

    }
    public boolean insert(AbBean bean){
        return bean.insert(JDBiConnector.me());
    }
    public static void main(String[] args) {
//        Log log= new Log(Log.INFO,"TK1","LOGIN FALSE","User ABC",0);
        Log log2= new Log(Log.INFO,"TK1","dkfsk","loginfalse","íoijos",0);

        DB.me().insert(log2);
    }


}
