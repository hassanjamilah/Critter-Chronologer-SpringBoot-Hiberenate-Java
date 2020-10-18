package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entitiy.Activity;
import com.udacity.jdnd.course3.critter.entitiy.Customer;
import com.udacity.jdnd.course3.critter.entitiy.Employee;
import com.udacity.jdnd.course3.critter.entitiy.WeekDays;
import com.udacity.jdnd.course3.critter.entitiy.types.EmployeeSkill;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.view.CustomerDTO;
import com.udacity.jdnd.course3.critter.view.EmployeeDTO;
import com.udacity.jdnd.course3.critter.view.EmployeeRequestDTO;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = convertCustomerDTOToCustomer(customerDTO);

        customerService.saveCustomer(customer);
        customerDTO.setId(customer.getId());
        return customerDTO;

    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer:
             customers) {
            CustomerDTO customerDTO = convertCustomerToCustomerDTO(customer);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) throws NotFoundException {

        Customer customer = customerService.getOwnerByPet(petId);
        CustomerDTO customerDTO = convertCustomerToCustomerDTO(customer);
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        employeeService.saveEmployee(employee);
        employeeDTO.setId(employee.getId());
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) throws NotFoundException {
        Employee employee = employeeService.getEmployeeByID(employeeId);
        employee.setWorkingDays(new HashSet<>());
        for (DayOfWeek day:
                daysAvailable) {
            WeekDays newDay = new WeekDays();
            newDay.setId((long) (day.ordinal()+1));
            employee.getWorkingDays().add(newDay);
        }
        employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) throws ParseException {

        List<Activity> allSkills = new ArrayList<>();
        for (EmployeeSkill skill:
             employeeDTO.getSkills()) {
            allSkills.add(new Activity((long) (skill.ordinal()+1)));
        }

        List<Employee> allEmployees =  employeeService.checkAvailability(allSkills, employeeDTO.getDate());
        return  convertListEmployeesToListEmployeesDTOs(allEmployees);
    }

    public Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setSkills(new HashSet<>());
        employee.setWorkingDays(new HashSet<>());
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        Set<DayOfWeek> days = employeeDTO.getDaysAvailable();
        for (EmployeeSkill skill:
             skills) {
            Activity activity = new Activity();
            activity.setId((long) (skill.ordinal() + 1));
            employee.getSkills().add(activity);
        }

        for (DayOfWeek day:
             days) {
            WeekDays newDay = new WeekDays();
            newDay.setId((long) (day.ordinal()+1));
            employee.getWorkingDays().add(newDay);
        }



        BeanUtils.copyProperties(employeeDTO, employee,"skills", "workingDays");
        return employee;
    }

    public List<EmployeeDTO> convertListEmployeesToListEmployeesDTOs(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee emp:
             employees) {
            EmployeeDTO eDtos = convertEmployeeToEmployeeDTO(emp);
            employeeDTOS.add(eDtos);
        }
        return employeeDTOS;
    }

    public EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

}
