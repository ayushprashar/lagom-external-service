package edu.knoldus.crud;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import edu.knoldus.crud.utiliy.DataResponse;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;
import static com.lightbend.lagom.javadsl.api.transport.Method.GET;

public interface ExternalService extends Service {

    ServiceCall<NotUsed, DataResponse> getDataResponse();

    @Override
    default Descriptor descriptor() {
        return named("external").withCalls(
                restCall(GET, "/api/users/2", this::getDataResponse)
        ).withAutoAcl(true);
    }


}

