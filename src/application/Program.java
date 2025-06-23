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

        
        System.out.println("\n=== TEST 4 : employee insert ===");
        Employee newEmployee = new Employee(null, "Erick Mendes", "erick@gmail.com", 3200.00,department);
        employeeDao.insert(newEmployee);
        System.out.println("Insert completed! new id = " + newEmployee.getId());

        System.out.println("\n=== TEST 4 : department insert ===");
        Department newDepartment = new Department(null,"TV");
        departmentDao.insert(newDepartment);
        System.out.println("Insert completed! new id = " + newDepartment.getId());

        System.out.println("\n=== TEST 5 : employee update ===");
        employee = employeeDao.findById(13);
        employee.setDepartment(departmentDao.findById(4));
        employee.setEmail("erickmendes@gmail.com");
        employeeDao.update(employee);
        System.out.println("Update completed!");

        System.out.println("\n=== TEST 5 : department update ===");
        department = departmentDao.findById(3);
        department.setName("FrontEnd");
        departmentDao.update(department);
        System.out.println("Update completed!");

        System.out.println("\n=== TEST 6 : employee delete ===");
        employeeDao.deleteById(7);
        System.out.println("Deleted completed!");

        System.out.println("\n=== TEST 6 : department delete ===");
        departmentDao.deleteById(5);
        System.out.println("Deleted completed!");

    }
}
