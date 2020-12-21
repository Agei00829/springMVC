package com.lab.jpa.repository;

import com.lab.jpa.entities.Club;
import com.lab.jpa.entities.Department;
import com.lab.jpa.entities.Employee;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CompanyDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session session = null;
    
   
    
    //flag:true (提供給查詢), false:提供給新增修改刪除
    private Session getSession() {
      //  if(flag && session != null) {
      //      return session;
      // }
        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            session = sessionFactory.openSession();
        }
        return session;
    }
    
    // 查詢所有部門資料
    public List queryAllDepts() {
        List list = getSession().createQuery("from Department d").list();
        return list;
    }
    
    // 查詢單筆部門
    public Department getDept(Integer id) {
        Department dept = (Department)getSession().get(Department.class, id);
        return dept;
    }
    
    @Transactional
    public void saveDept(Department dept) {
        getSession().persist(dept);
    }
    
    // 查詢所有社團資料
    public List queryAllClubs() {
        List list = getSession().createQuery("from Club c").list();
        return list;
    }
    
    public Club getClub(Integer id) {
        Club club = (Club)getSession().get(Club.class, id);
        return club;
    }
    
    @Transactional
    public void saveClub(Club club) {
        getSession().persist(club);
    }
    
    // 查詢所有員工
    public List queryAllEmps() {
        List list = getSession().createQuery("from Employee e").list();
        return list;
    }
    
    //查詢單筆員工
    public Employee getEmp(Integer id) {
        Employee emp = (Employee)getSession().get(Employee.class, id);
        return emp;
    }
    
    //新增員工
    @Transactional
    public void saveEmp(Employee emp) {
        getSession().persist(emp);
    }
    
}
