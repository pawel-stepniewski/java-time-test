package tech.stepniewski.jtime;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "sample_resources")
public class SampleResource {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ordering")
    public Long ordering;

    @Column(name = "resource_id")
    public String resourceId;

    @Column(name = "external_resource_id")
    public String externalResourceId;

    @Column(name = "key")
    public String key;

    @Column(name = "value")
    public String value;

    @Column(name = "created_timestamp")
    public Date createdTimestamp;
}
