package tech.stepniewski.jtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.UUID;

@Controller
public class SampleResourceController {

    @PersistenceContext private EntityManager entityManager;

    @Autowired private TransactionTemplate transactionTemplate;

    @PutMapping(value = "/sample-resource/{resourceId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PutResourceResponse helloWorld(@PathVariable String resourceId, @RequestBody PutResourceRequest request) {
        SampleResource sampleResource = new SampleResource();
        sampleResource.resourceId = UUID.randomUUID().toString();
        sampleResource.externalResourceId = resourceId;
        sampleResource.key = request.key;
        sampleResource.value = request.value;
        sampleResource.createdTimestamp = new Date();
        transactionTemplate.execute(t -> {
            entityManager.persist(sampleResource);
            return null;
        });
        return new PutResourceResponse(sampleResource.resourceId, sampleResource.externalResourceId);
    }

    public static class PutResourceRequest {
        public String key;
        public String value;
    }

    public static class PutResourceResponse {
        public final String sampleResourceId;
        public final String externalSampleResourceId;

        public PutResourceResponse(String sampleResourceId, String externalSampleResourceId) {
            this.sampleResourceId = sampleResourceId;
            this.externalSampleResourceId = externalSampleResourceId;
        }
    }
}
