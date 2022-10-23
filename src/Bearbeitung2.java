import java.util.ArrayList;

public class Bearbeitung2 {
    private static CurrentTime currentTime = new CurrentTime();
    private static ArrayList<Auftrag> list = new ArrayList<>();
    private static int allgemeineFertigStellung = 0;
    private static int allgemeineWarteZeit = 0;
    private static int durchschnittlicheWartezeit = 0;
    private static int maximaleWartezeit = 0;
    private static int listIndex = 0;

    public static int getListIndex() {
        return listIndex;
    }
    public static void setListIndex(int listIndex) {
        Bearbeitung2.listIndex = listIndex;
    }
    public static ArrayList<Auftrag> getList() {
        return list;
    }
    public static int getAllgemeineFertigStellung() {
        return allgemeineFertigStellung;
    }
    public static int getMaximaleWartezeit() {
        return maximaleWartezeit;
    }
    public static int getDurschnittlicheWartezeit() {
        return durchschnittlicheWartezeit / list.size();
    }

    public static void setDurschnittlicheWartezeit(int durschnittlicheWartezeit) {
        Bearbeitung2.durchschnittlicheWartezeit = durschnittlicheWartezeit;
    }

    public static void startWork(Auftrag auftrag){
        findAllgemeineWarteZeit(auftrag);
        auftragErledigen(auftrag);

        if (maximaleWartezeit < auftrag.getWarteZeit()) maximaleWartezeit = auftrag.getWarteZeit();
        durchschnittlicheWartezeit +=auftrag.getWarteZeit();
    }

    public static void findAllgemeineWarteZeit(Auftrag auftrag){
        currentTime.setDays(auftrag.getEingangsZeitPunkt() / 1440);
        currentTime.setMinutes(auftrag.getEingangsZeitPunkt() - currentTime.getDays() * 1440);
        int atWork = auftrag.getAuftragsDauer();
        allgemeineWarteZeit = 0;
        while (atWork > 0) {
            if (currentTime.getMinutes() >= 540 && currentTime.getMinutes() <= 1020) {
                int temp = atWork;
                atWork = atWork - 1020 + currentTime.getMinutes();
                if (atWork < 0) {
                    currentTime.setMinutes(currentTime.getMinutes() + temp);
                    allgemeineWarteZeit += temp;
                } else {
                    allgemeineWarteZeit += 1020 - currentTime.getMinutes() + 960;
                    currentTime.setMinutes(540);
                    currentTime.setDays(currentTime.getDays() + 1);

                }
            } else {
                if (currentTime.getMinutes() > 1020 && currentTime.getMinutes() <= 1440) {
                    allgemeineWarteZeit += 1440 - currentTime.getMinutes() + 540;
                    currentTime.setMinutes(540);
                    currentTime.setDays(currentTime.getDays() + 1);
                } else {
                    allgemeineWarteZeit += 540 - currentTime.getMinutes();
                    currentTime.setMinutes(540);
                }
            }
        }
    }

    public static void auftragErledigen(Auftrag auftrag){
        if (auftrag.getEingangsZeitPunkt() > allgemeineFertigStellung){
            auftrag.setWarteZeit(allgemeineWarteZeit);
            auftrag.setFertigStellung(auftrag.getEingangsZeitPunkt() + allgemeineWarteZeit);
        } else {
            auftrag.setWarteZeit(allgemeineFertigStellung - auftrag.getEingangsZeitPunkt() + allgemeineWarteZeit);
            auftrag.setFertigStellung(auftrag.getWarteZeit() + auftrag.getEingangsZeitPunkt());
        }
        auftrag.setErledigt(true);
        allgemeineFertigStellung = auftrag.getFertigStellung();
    }

    public static int findListIndex() {
        for (int i = 0; i< list.size(); i++){
            if (!list.get(i).isErledigt()){
                return i;
            }
        }
        return -1;
    }
}
