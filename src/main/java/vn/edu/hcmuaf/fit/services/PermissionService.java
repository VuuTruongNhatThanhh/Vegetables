package vn.edu.hcmuaf.fit.services;

import vn.edu.hcmuaf.fit.bean.Permission;
import vn.edu.hcmuaf.fit.database.JDBiConnector;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionService {
    private static PermissionService instance;

    private PermissionService(){

    }
    public static PermissionService getInstance(){
        if(instance==null){
            instance = new PermissionService();
        }
        return instance;
    }
    public int checkAccess(String rsname, String uId){
        List<Permission> list = JDBiConnector.me().withHandle(handle -> {
            return handle.createQuery("SELECT permission.id, resources.`name`, permission.u_id, permission.per FROM permission, resources\n" +
                    "WHERE permission.rs_id = resources.id AND permission.u_id = ?  AND resources.`name` = ? AND resources.`status`=1")
                    .bind(0,uId).bind(1, rsname)
                    .mapToBean((Permission.class)).stream().collect(Collectors.toList());
        });
        if(list==null) return 0;
        else {
            int max=0;
            for(Permission p: list){
                if (p.getId()>max) max=p.getPer();
            }
            return max;
        }
    }

    public static void main(String[] args) {

    }
}
