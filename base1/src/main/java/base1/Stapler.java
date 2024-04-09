package base1;

import jakarta.persistence.Entity;

@Entity
public class Stapler extends Transporter {
    protected String staplerTyp;

    public Stapler(String id, String beschreibung, String staplerTyp) {
        super("STA" + id);
        this.id = id;
        this.beschreibung = beschreibung;
        this.staplerTyp = staplerTyp;
    }

    protected Stapler() {
    }

    public void beschreibungAendern(String neueBeschreibung) {
        this.beschreibung = neueBeschreibung;
    }
}
