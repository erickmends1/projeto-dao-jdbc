package model.dao;

import model.entities.Department;
import model.entities.Employee;

import java.util.List;

public interface EmployeeDao {
    public abstract void insert(Employee obj);
    public abstract void update(Employee obj);
    public abstract void delete(Integer id);
    public abstract List<Employee> findAll();
    public abstract Employee findById(Integer id);
    public abstract List<Employee> findByDepartment(Department department);
}
