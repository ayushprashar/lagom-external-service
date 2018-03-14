package edu.knoldus.crud;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import edu.knoldus.crud.utiliy.DataResponse;
import edu.knoldus.crud.utiliy.Employee;

import java.util.List;
import java.util.Map;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;
import static com.lightbend.lagom.javadsl.api.transport.Method.DELETE;
import static com.lightbend.lagom.javadsl.api.transport.Method.GET;
import static com.lightbend.lagom.javadsl.api.transport.Method.POST;
import static com.lightbend.lagom.javadsl.api.transport.Method.PUT;


public interface EmployeeService extends Service {
    ServiceCall<NotUsed, Employee> getEmp(String id);
    ServiceCall<NotUsed,Map<Integer,Employee>> getAllEmp();
    ServiceCall<NotUsed,String> deleteEmp(String id);
    ServiceCall<Employee, String> postEmp();
    ServiceCall<Employee,String> updateEmp(String id);
    ServiceCall<NotUsed, DataResponse> externalServiceMethod();
    @Override
    default Descriptor descriptor() {
        return named("emp").withCalls(
                restCall(GET, "/get/:id", this::getEmp),
                restCall(POST, "/post", this::postEmp),
                restCall(DELETE,"/delete/:id",this::deleteEmp),
                restCall(PUT,"/put/:id",this::updateEmp),
                restCall(GET,"/getallemployees",this::getAllEmp),
                restCall(GET, "/api/external", this::externalServiceMethod)
        ).withAutoAcl(true);
    }


}
