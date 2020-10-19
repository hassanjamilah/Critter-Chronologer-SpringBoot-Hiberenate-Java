package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entitiy.*;
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

    /**
     * Create a new customer
     * @param customerDTO the customer data from the body request
     * @return the same data with the ID of the new customer
     */
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        Long id = customerService.saveCustomer(customer);
        customerDTO.setId(id);
        return customerDTO;
    }

    /**
     * Get all the the customer
     * @return List of the csutomers with the view format
     */
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

    /**
     * Get a specified customer of a specified Pet ID
     * @param petId the pet id
     * @return The founded customer is the view format
     * @throws NotFoundException If the pet is not existing
     */
    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) throws NotFoundException {

        Customer customer = customerService.getOwnerByPet(petId);
        CustomerDTO customerDTO = convertCustomerToCustomerDTO(customer);
        return customerDTO;
    }

    /**
     * Create new Employee
     * @param employeeDTO the employee data as the view format
     * @return the same data with the ID of the new employee
     */
    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        Long id = employeeService.saveEmployee(employee);
        employeeDTO.setId(id);
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) throws NotFoundException {
        Employee employee = employeeService.getEmployeeByID(employeeId);
        return  convertEmployeeToEmployeeDTO(employee);
    }

    /**
     * Update method to update the available dates of the employee
     * @param daysAvailable the new days the employee will be available
     * @param employeeId the employee ID
     * @throws NotFoundException If the the employee was not found
     */
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


    /**
     * Find the available employees in a specified date with a specified skill required
     * @param employeeDTO the skills and the specified date, will be inflated from the request
     * @return the list of employees that matches the query
     * @throws ParseException
     */
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

    /**
     * Convert view object to entitiy object
     * @param customerDTO the view object
     * @return the entitiy object
     */
    public Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    /**
     * Convert customer entity object to customer view object
     * @param customer  the customer enitity object
     * @return the customer view object
     */
    public CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setPetIds(new ArrayList<>());
        BeanUtils.copyProperties(customer, customerDTO);
        for (Pet pet:
             customer.getPets()) {
            customerDTO.getPetIds().add(pet.getId());
        }
        return customerDTO;
    }

    /**
     * Convert the employee view object to employee entity object
     * @param employeeDTO the employee view object
     * @return the return employee entity object
     */
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

        if (days != null && days.size() > 0){
            for (DayOfWeek day:
                    days) {
                WeekDays newDay = new WeekDays();
                newDay.setId((long) (day.ordinal()+1));
                employee.getWorkingDays().add(newDay);
            }
        }
        BeanUtils.copyProperties(employeeDTO, employee,"skills", "workingDays");
        return employee;
    }

    /**
     * Convert a list of employee entity objects to a list of employee view objects
     * @param employees the list of the list of employee entity objects
     * @return the list of the employee view objects
     */
    public List<EmployeeDTO> convertListEmployeesToListEmployeesDTOs(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee emp:
             employees) {
            EmployeeDTO eDtos = convertEmployeeToEmployeeDTO(emp);
            employeeDTOS.add(eDtos);
        }
        return employeeDTOS;
    }

    /**
     * Convert employee entitiy object to an employee view object
     * @param employee the employee entity object
     * @return the returned employee view object
     */
    public EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();


        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

}
