package org.deepsl.hrm.dao;


import org.apache.ibatis.annotations.Param;
import org.deepsl.hrm.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description: UserMapper接口
 * @version V1.0   
 */

public interface UserDao {

	// 根据登录名和密码查询员工
	User selectByLoginnameAndPassword(HashMap map);
	
	// 根据id查询用户
	User selectById(@Param("id") Integer id);
	
	// 根据id删除用户
	void deleteById(@Param("id") Integer id);
		
	// 动态修改用户
	void update(@Param("user") User user);
		
	// 动态查询
	List<User> selectByPage(Map<String, Object> params);
	
	// 根据参数查询用户总数
	Integer count(Map<String, Object> params);
	
	// 动态插入用户
	void save(User user);

	List<User> findUser();

	List<User> findUserByuserNameAndStatus(@Param("username") String username,@Param("status") Integer status);

	boolean addUser(@Param("user") User user);

    int queryCountOfUser();

	List<User> findCategoryByPage(@Param("currentPage") int i, @Param("limit") int limit);

	int findUserCountByuserNameAndStatus(@Param("username") String username,@Param("status") Integer status);

	List<User> findUserByuserNameAndStatusOfPage(@Param("currentPage")int i,@Param("limit") int i1,@Param("username") String username,@Param("status") int i2);
}
