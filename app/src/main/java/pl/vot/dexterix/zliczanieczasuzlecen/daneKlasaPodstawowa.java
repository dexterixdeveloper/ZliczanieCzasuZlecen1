package pl.vot.dexterix.zliczanieczasuzlecen;

public class daneKlasaPodstawowa {
    protected Integer id;
    protected String uwagi;
    protected Integer poprzedni_rekord_id;
    protected String poprzedni_rekord_data_usuniecia;
    protected String poprzedni_rekord_powod_usuniecia;
    protected Integer czy_widoczny;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public Integer getPoprzedni_rekord_id() {
        return poprzedni_rekord_id;
    }

    public void setPoprzedni_rekord_id(Integer poprzedni_rekord_id) {
        this.poprzedni_rekord_id = poprzedni_rekord_id;
    }

    public String getPoprzedni_rekord_data_usuniecia() {
        return poprzedni_rekord_data_usuniecia;
    }

    public void setPoprzedni_rekord_data_usuniecia(String poprzedni_rekord_data_usuniecia) {
        this.poprzedni_rekord_data_usuniecia = poprzedni_rekord_data_usuniecia;
    }

    public String getPoprzedni_rekord_powod_usuniecia() {
        return poprzedni_rekord_powod_usuniecia;
    }

    public void setPoprzedni_rekord_powod_usuniecia(String poprzedni_rekord_powod_usuniecia) {
        this.poprzedni_rekord_powod_usuniecia = poprzedni_rekord_powod_usuniecia;
    }

    public Integer getCzy_widoczny() {
        return czy_widoczny;
    }

    public void setCzy_widoczny(Integer czy_widoczny) {
        this.czy_widoczny = czy_widoczny;
    }
}
