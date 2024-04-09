package base3.sls;

import base2.api.TransporterReplica;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "EventTransporterReplica")
@Table(name = "EVENTTRANSPORTERREPLICA")
@DiscriminatorValue("ET")
public class EventTransporterReplicaEntity extends TransporterReplica {

}
