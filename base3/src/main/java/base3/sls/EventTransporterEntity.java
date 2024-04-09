package base3.sls;

import base2.api.Transporter;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "EventTransporter")
@Table(name = "EVENTTRANSPORTER")
@DiscriminatorValue("ET")
public class EventTransporterEntity extends Transporter {

}
