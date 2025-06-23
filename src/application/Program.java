package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.EmployeeDao;
import model.entities.Department;
import model.entities.Employee;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("=== TEST 1 : employee findbyDepartment ===");
        Department dep = new Department(2, null);
        List<Employee> listEmployee = employeeDao.findByDepartment(dep);
        listEmployee.forEach(System.out::println);

        System.out.println("\n=== TEST 2 : employee findbyId ===");
        Employee employee = employeeDao.findById(10);
        System.out.println(employee);

        System.out.println("\n=== TEST 2 : department findbyId ===");
        Department department = departmentDao.findById(2);
        System.out.println(department);

        System.out.println("\n=== TEST 3 : employee findAll ===");
        listEmployee = employeeDao.findAll();
        listEmployee.forEach(System.out::println);

        System.out.println("\n=== TEST 3 : department findAll ===");
        List<Department> listDepartment = departmentDao.findAll();
        listDepartment.forEach(System.out::println);
    }
}
