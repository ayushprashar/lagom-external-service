package edu.knoldus.crud;

import akka.NotUsed;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.NotFound;
import edu.knoldus.crud.utiliy.DataResponse;
import edu.knoldus.crud.utiliy.Employee;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class EmployeeImpl implements EmployeeService {

    ExternalService externalService;

    @Inject
    EmployeeImpl(ExternalService externalService) {
        this.externalService = externalService;
    }

    private Map<Integer, Employee> empDB = new HashMap<>();

    @Override
    public ServiceCall<NotUsed, Employee> getEmp(String id) {

        return request -> CompletableFuture.completedFuture(
                Optional.of(empDB.get(Integer.parseInt(id))).<NotFound>orElseThrow(() -> {
                    throw new NotFound("User with "
                            + "id" + id + "not found.");
                })
        );
    }

    @Override
    public ServiceCall<NotUsed, Map<Integer, Employee>> getAllEmp() {
        return request -> CompletableFuture.completedFuture(empDB);
    }


    @Override
    public ServiceCall<NotUsed, String> deleteEmp(String id) {
        return emp -> {
            if (empDB.containsKey(Integer.parseInt(id))) {
                empDB.remove(Integer.parseInt(id));
                return CompletableFuture.completedFuture("Employee with ID " + id + " is removed");
            } else {
                return CompletableFuture.completedFuture("Employee with ID " + id + " doesn't exist");
            }
        };

    }

    @Override
    public ServiceCall<Employee, String> postEmp() {
        return emp -> {
            if (empDB.containsKey(Integer.parseInt(emp.getId()))) {
                return CompletableFuture.completedFuture("User" + emp.getName() + " already exists");
            } else {
                empDB.put(Integer.parseInt(emp.getId()), Employee.builder()
                        .name(emp.getName())
                        .age(emp.getAge())
                        .city(emp.getCity())
                        .id(emp.getId())
                        .build());
                return CompletableFuture.completedFuture("User" + emp.getName() + " added");
            }
        };
    }

    @Override
    public ServiceCall<Employee, String> updateEmp(String id) {
        return emp -> {
            if (empDB.containsKey(Integer.parseInt(id))) {
                empDB.put(Integer.parseInt(id), Employee.builder()
                        .name(emp.getName())
                        .age(emp.getAge())
                        .city(emp.getCity())
                        .id(emp.getCity())
                        .build());
                return CompletableFuture.completedFuture("User " + emp.getName() + " added");
            } else {
                return CompletableFuture.completedFuture("User not found");
            }
        };
    }

    @Override
    public ServiceCall<NotUsed, DataResponse> externalServiceMethod() {
        return request -> externalService.getDataResponse().invoke().thenApply(dataResponse -> dataResponse);
    }
}
