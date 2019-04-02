package org.deepsl.hrm.service;

import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.util.tag.PageModel;

import java.util.List;

/**   
 * @Description: 人事管理服务层接口
 * @version V1.0   
 */
public interface HrmService {


	/**
	 * 用户登录
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String loginname, String password);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 * */
	User findUserById(Integer id);
	
	/**
	 * 获得所有用户
	 * @return User对象的List集合
	 * */
	/*List<User> findUser(User user, PageModel pageModel);*/
	List<User> findUser();
	
	/**
	 * 根据id删除用户
	 * @param id
	 * */
	void removeUserById(Integer id);
	
	//修改用户
	void modifyUser(User user);
	

	boolean addUser(User user);

	//根据查询条件，进行查询操作
	List<User> findUserByuserNameAndStatus(String username,Integer status);

    int queryCountOfUser();

	List<User> findCategoryByPage(int i, int i1);

	int findUserCountByuserNameAndStatus(String username, Integer status);

	List<User> findUserByuserNameAndStatusOfPage(int i, int i1, String username, int i2);
}
