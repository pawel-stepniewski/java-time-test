package tech.stepniewski.jtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.UUID;

@Controller
public class SampleResourceController {

    @PersistenceContext private EntityManager entityManager;

    @Autowired private TransactionTemplate transactionTemplate;

    @PutMapping(value = "/sample-resource/{externalResourceId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PutResourceResponse putResource(@PathVariable String externalResourceId, @RequestBody PutResourceRequest request) {
        SampleResource sampleResource = new SampleResource();
        sampleResource.resourceId = UUID.randomUUID().toString();
        sampleResource.externalResourceId = externalResourceId;
        sampleResource.key = request.key;
        sampleResource.value = request.value;
        sampleResource.createdTimestamp = new Date();
        transactionTemplate.execute(t -> {
            entityManager.persist(sampleResource);
            return null;
        });
        return new PutResourceResponse(sampleResource.resourceId, sampleResource.externalResourceId);
    }

    @GetMapping(value = "/sample-resource/{externalResourceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetResourceResponse getResource(@PathVariable String externalResourceId) {
        return transactionTemplate.execute(t -> {
            SampleResource sr = entityManager.createQuery("select v from sample_resources v where v.externalResourceId = :externalResourceId", SampleResource.class)
                    .setParameter("externalResourceId", externalResourceId)
                    .getSingleResult();
            GetResourceResponse r = new GetResourceResponse();
            r.sampleResourceId = sr.resourceId;
            r.externalSampleResourceId = sr.externalResourceId;
            r.key = sr.key;
            r.value = sr.value;
            r.createdTimestamp = sr.createdTimestamp;
            return r;
        });
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

    public static class GetResourceResponse {
        public String sampleResourceId;
        public String externalSampleResourceId;
        public String key;
        public String value;
        public Date createdTimestamp;
    }
}
