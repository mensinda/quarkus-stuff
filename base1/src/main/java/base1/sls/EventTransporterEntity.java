package base1.sls;

import base1.tv.Transporter;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "EventTransporter")
@Table(name = "EVENTTRANSPORTER")
@DiscriminatorValue("ET")
public class EventTransporterEntity extends Transporter {

}
