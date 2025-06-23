package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    public abstract void insert(Department obj);
    public abstract void update(Department obj);
    public abstract void delete(Integer id);
    public abstract List<Department> findAll();
    public abstract Department findById(Integer id);
}
