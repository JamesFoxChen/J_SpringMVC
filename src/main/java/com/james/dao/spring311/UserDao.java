package com.james.dao.spring311;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.james.domain.spring311.User;

@Repository
public class UserDao extends BaseDaoHibernate<User> {
    /**
     * 根据用户名查询User对象
     * @param userName 用户名
     * @return 对应userName的User对象，如果不存在，返回null。
     */
    public User getUserByUserName(String userName){
        List<User> users = (List<User>)find("from User u where u.userName = ?",userName);
        if (users.size() == 0) {
            return null;
        }else{
            return users.get(0);
        }
    }

    /**
     * 根据用户名为模糊查询条件，查询出所有前缀匹配的User对象
     * @param userName 用户名查询条件
     * @return 用户名前缀匹配的所有User对象
     */
    public List<User> queryUserByUserName(String userName){
        return (List<User>)find("from User u where u.userName like ?",userName+"%");
    }
}
