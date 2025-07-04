package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.EmployeeDao;
import model.entities.Department;
import model.entities.Employee;

import java.net.IDN;
import java.sql.*;
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
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO employee (Name, Email, BaseSalary, DepartmentId) " +
                        "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDouble(3, obj.getBaseSalary());
            st.setInt(4, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }else {
                throw new DbException("Error! could not perform the incertion");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Employee obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE employee SET Name = ?, Email = ?, BaseSalary = ?, DepartmentId = ? " +
                            "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDouble(3, obj.getBaseSalary());
            st.setInt(4, obj.getDepartment().getId());
            st.setInt(5, obj.getId());

            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "DELETE FROM employee " +
                            "WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
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

