package org.example;

import org.example.entity.Department;
import org.example.entity.Duty;
import org.example.entity.Employee;
import org.example.repository.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate\\hibernate.cfg.xml");

       SessionFactory sessionFactory = configuration.buildSessionFactory();

        EntityManager entityManager = sessionFactory.createEntityManager();

        DepartmentRepository departmentRepository = new DepartmentRepositoryImpl(entityManager);
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(entityManager);
        DutyRepository dutyRepository = new DutyRepositoryImpl(entityManager);

        Department department1 = Department.builder()
                .name("IT")
                .build();

        Department department2 =Department.builder()
                                .name("Test")
                                .build();
        Department department3 =Department.builder()
                .name("protect")
                .build();

        departmentRepository.save(department1);
        departmentRepository.save(department2);
        departmentRepository.save(department3);


        Employee masha = Employee.builder()
                .firstName("Masha")
                .lastName("Morozova")
                .department(department1)
                .build();

        Employee sasha = Employee.builder()
                .firstName("Sasha")
                .lastName("Kim")
                .department(department2)
                .build();

        Employee misha = Employee.builder()
                .firstName("Misha")
                .lastName("Karakozov")
                .department(department3)
                .build();

        employeeRepository.save(masha);
        employeeRepository.save(sasha);
        employeeRepository.save(misha);


        Duty test1 = Duty.builder()
                .name("test API")
                .employee(sasha)
                .build();

        Duty test2 = Duty.builder()
                .name("test aplication")
                .employee(sasha)
                .build();
        Duty development = Duty.builder()
                .name("development aplication")
                .employee(masha)
                .build();
        Duty debugigng  = Duty.builder()
                .name("debug soft")
                .employee(masha)
                .build();
        Duty deploy = Duty.builder()
                .name("deploy soft")
                .employee(masha)
                .build();

        Duty protect = Duty.builder()
                .name("protect soft")
                .employee(misha)
                .build();

        dutyRepository.save(test1);
        dutyRepository.save(test2);
        dutyRepository.save(development);
        dutyRepository.save(deploy);
        dutyRepository.save(protect);
        dutyRepository.save(debugigng);
        List<Employee> employeeList = employeeRepository.findAll();
        for(Employee employee : employeeList){
            System.out.println(employee.getLastName());
        }

        List<Employee> empList = employeeRepository.findEntityByName("Karakozov");
        for(Employee employee : empList){
            System.out.println(employee.getFirstName() + " " + employee.getLastName());
        }

        List<Duty> dutyList1 = dutyRepository.findAll();
        List<Duty> dutyList2 = dutyRepository.findEntityByName("debug soft");

        for(Duty duty : dutyList1){
            System.out.println("name of duty: " + duty.getName());
        }

        System.out.println("___________________");

        for(Duty duty : dutyList2){
            System.out.println("name of duty: " + duty.getName());
        }


    }
}
