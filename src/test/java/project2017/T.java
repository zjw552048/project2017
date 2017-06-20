package project2017;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.z.dao.RolePermissionMapper;
import com.z.dao.SysUserMapper;
import com.z.dao.UserRoleMapper;



public class T {
	@Test
	public void test1(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		SysUserMapper mapper = ac.getBean("sysUserMapper",SysUserMapper.class);
		System.out.println(mapper.selectByPrimaryKey(1));
	}
	@Test
	public void test2(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		SysUserMapper mapper = ac.getBean("sysUserMapper",SysUserMapper.class);
		System.out.println(mapper.selectSysUserByUsername("admin"));
	}
	@Test
	public void test3(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		RolePermissionMapper dao = ac.getBean(RolePermissionMapper.class);
		List<String> roleIdList = new ArrayList<String>();
		roleIdList.add("1");
		System.out.println(dao.selectPermissionByRoleIdList(roleIdList));
	}
	@Test
	public void test4(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserRoleMapper dao1 = ac.getBean("userRoleMapper",UserRoleMapper.class);
		System.out.println(dao1.selectRoleIdByUserId("1"));
		RolePermissionMapper dao2 = ac.getBean("rolePermissionMapper",RolePermissionMapper.class);
		List<String> roleIdList = new ArrayList<String>();
		roleIdList.add("1");
		roleIdList.add("2");
		roleIdList.add("3");
		System.out.println(dao2.selectPermissionByRoleIdList(roleIdList));
	}
	@Test
	public void test5(){
		Date date = new Date();
		ObjectMapper om = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		om.setDateFormat(sdf);
		try {
			String data = om.writeValueAsString(date);
			System.out.println(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
}
