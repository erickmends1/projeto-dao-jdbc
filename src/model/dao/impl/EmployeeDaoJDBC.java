package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.EmployeeDao;
import model.entities.Department;
import model.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoJDBC implements EmployeeDao {

    private Connection conn;

    public EmployeeDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Employee obj) {

    }

    @Override
    public void update(Employee obj) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Employee> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT employee.*, department.Name as DepName " +
                            "FROM employee INNER JOIN department " +
                            "ON employee.DepartmentId = department.Id " +
                            "ORDER BY Name");
            rs = st.executeQuery();
            List<Employee> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null){
                    dep =createDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Employee obj = createEmployee(rs, dep);
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Employee findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT employee.*, department.Name as DepName " +
                        "FROM employee INNER JOIN department " +
                        "ON employee.DepartmentId = department.Id " +
                        "WHERE employee.Id = ? " +
                        "ORDER BY Name");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){
                Department dep = createDepartment(rs);
                Employee obj = createEmployee(rs, dep);
                return obj;
            }
            return null;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Employee> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT employee.*, department.Name as DepName " +
                        "FROM employee INNER JOIN department " +
                        "ON employee.DepartmentId = department.Id " +
                        "WHERE DepartmentId = ? " +
                        "ORDER BY Name");
            st.setInt(1, department.getId());
            rs = st.executeQuery();
            List<Employee> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()){
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null){
                    dep = createDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Employee obj = createEmployee(rs, dep);
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Employee createEmployee(ResultSet rs, Department dep) throws SQLException{
        Employee obj = new Employee();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department createDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }
}

