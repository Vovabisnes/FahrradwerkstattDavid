public class Auftrag {
    private int eingangsZeitPunkt;
    private int auftragsDauer;
    private int warteZeit;
    private int fertigStellung;
    private boolean isErledigt;

    public Auftrag(int eingangsZeitPunkt, int auftragsDauer, boolean isErledigt) {
        this.eingangsZeitPunkt = eingangsZeitPunkt;
        this.auftragsDauer = auftragsDauer;
        this.isErledigt = isErledigt;
        warteZeit = 0;
    }

    public int getEingangsZeitPunkt() {
        return eingangsZeitPunkt;
    }

    public int getAuftragsDauer() {
        return auftragsDauer;
    }

    public int getWarteZeit() {
        return warteZeit;
    }

    public int getFertigStellung() {
        return fertigStellung;
    }

    public void setWarteZeit(int warteZeit) {
        this.warteZeit = warteZeit;
    }

    public void setFertigStellung(int fertigStellung) {
        this.fertigStellung = fertigStellung;
    }

    public boolean isErledigt() {
        return isErledigt;
    }

    public void setErledigt(boolean erledigt) {
        isErledigt = erledigt;
    }

    @Override
    public String toString() {
        return eingangsZeitPunkt + " " + auftragsDauer + " " + warteZeit + " " + fertigStellung + " " + isErledigt;
    }
}
