package Calendar;

import java.util.ArrayList;
import java.util.List;

public class TeilnehmerList {

    private List<Teilnehmer> teilnehmerList;

    public TeilnehmerList() {
        this.teilnehmerList = new ArrayList<>();
    }

    public void addTeilnehmer(Teilnehmer teilnehmer) {
        this.teilnehmerList.add(teilnehmer);
    }

    public void removeTeilnehmer(Teilnehmer teilnehmer) {
        this.teilnehmerList.remove(teilnehmer);
    }

    public List<Teilnehmer> getTeilnehmerList() {
        return this.teilnehmerList;
    }
}
