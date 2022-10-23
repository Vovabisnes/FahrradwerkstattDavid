import java.util.ArrayList;

public class AuftragsQueue {
    private static Bearbeitung2 bearbeitung = new Bearbeitung2();
    private static ArrayList<Auftrag> auftragsQueue = new ArrayList<>();

    public static Bearbeitung2 getBearbeitung() {
        return bearbeitung;
    }

    public static void startQueue(){
        addAuftrag();
        queueBearbeiten();
    }

    public static void addAuftrag(){
        if (auftragsQueue.size() == 0 ){
            auftragsQueue.add(bearbeitung.getList().get(bearbeitung.getListIndex()));
            for (int i = bearbeitung.getListIndex() + 1; i< bearbeitung.getList().size(); i++){
                if (bearbeitung.getList().get(bearbeitung.getListIndex()).getEingangsZeitPunkt() == bearbeitung.getList().get(i).getEingangsZeitPunkt()){
                    auftragsQueue.add(bearbeitung.getList().get(i));
                } else {
                    break;
                }
            }
        }
    }

    public static void queueBearbeiten(){
        while (auftragsQueue.size() > 0) {
            bearbeitung.startWork(auftragsQueue.get(getMinimaleArbeitsZeit()));
            auftragsQueue.remove(auftragsQueue.get(getMinimaleArbeitsZeit()));
            for (int i = bearbeitung.getListIndex() + 1; i< bearbeitung.getList().size(); i++) {
                if (bearbeitung.getAllgemeineFertigStellung()  >= bearbeitung.getList().get(i).getEingangsZeitPunkt() && !bearbeitung.getList().get(i).isErledigt() && !auftragsQueue.contains(bearbeitung.getList().get(i))) {
                    auftragsQueue.add(bearbeitung.getList().get(i));
                } else if (bearbeitung.getAllgemeineFertigStellung()   < bearbeitung.getList().get(i).getEingangsZeitPunkt()) {
                    break;
                }
            }
        }
        bearbeitung.setListIndex(bearbeitung.findListIndex());
    }

    public static int getMinimaleArbeitsZeit(){
        int min = 0;
        for (int i = 1; i< auftragsQueue.size(); i++){
            if (auftragsQueue.get(min).getAuftragsDauer()> auftragsQueue.get(i).getAuftragsDauer()){
                min = i;
            }
        }
        return min;
    }
}