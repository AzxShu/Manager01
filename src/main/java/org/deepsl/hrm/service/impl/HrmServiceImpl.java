package org.deepsl.hrm.service.impl;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.deepsl.hrm.dao.UserDao;
import org.deepsl.hrm.domain.User;
import org.deepsl.hrm.service.HrmService;
import org.deepsl.hrm.util.tag.PageModel;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**   
 * @Description: 人事管理系统服务层接口实现类 
 * @version V1.0   
 */
@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService {
	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	UserDao userDao;

	/*****************用户服务接口实现*************************************/
	/**
	 * HrmServiceImpl接口login方法实现
	 *  @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		System.out.println("loginname:"+loginname);
		HashMap<String, String> hashMap= new HashMap<>();
		hashMap.put("loginname",  loginname );
		hashMap.put("password",  password );
		User user = userDao.selectByLoginnameAndPassword(hashMap);
		System.out.println("user:"+user);
		return user;
	}

	/**
	 * HrmServiceImpl接口findUser方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser() {
		List<User> users = userDao.findUser();
		return users;
	}
	
	/**
	 * HrmServiceImpl接口findUserById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeUserById(Integer id) {
 		userDao.deleteById(id);
	}
	
	/**
	 * HrmServiceImpl接口addUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyUser(User user) {
		userDao.update(user);
	}
	
	/**
	 * HrmServiceImpl接口modifyUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public boolean addUser(User user) {
		System.out.println("imp user:"+user);
		return userDao.addUser(user);
	}

	@Override
	public List<User> findUserByuserNameAndStatus(String username, Integer status) {
		return userDao.findUserByuserNameAndStatus(username,status);
	}

	@Override
	public int queryCountOfUser() {
		return userDao.queryCountOfUser();
	}

    @Override
    public List<User> findCategoryByPage(int i, int limit) {
        return userDao.findCategoryByPage(i, limit);
    }

    @Override
    public int findUserCountByuserNameAndStatus(String username, Integer status) {
        return userDao.findUserCountByuserNameAndStatus(username,status);
    }

    @Override
    public List<User> findUserByuserNameAndStatusOfPage(int i, int i1, String username, int i2) {
        return userDao.findUserByuserNameAndStatusOfPage(i,i1,username,i2);
    }

    /*****************部门服务接口实现*************************************/



 
	/*****************员工服务接口实现*************************************/


 
	
	/*****************职位接口实现*************************************/



 
	
	/*****************公告接口实现*************************************/


	 

	/*****************文件接口实现*************************************/

 
 
 
	
}
