package edu.knoldus.crud;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

public class EmpModule extends AbstractModule implements ServiceGuiceSupport{

    @Override
    protected void configure() {
        bindService(EmployeeService.class,EmployeeImpl.class);
        bindClient(ExternalService.class);
    }
}