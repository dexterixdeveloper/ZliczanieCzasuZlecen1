package pl.vot.dexterix.zliczanieczasuzlecen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dexterix on 2015-04-10.
 */
public class ObslugaSQL extends SQLiteOpenHelper {
    //private static final String TAG = SqliteExporter.class.getSimpleName();
    //klasa odpowiedzialna za zarządzanie bazą danych
    private static final int DATABASE_VERSION = 5;
    private static final String STALA_CZESC_ZAPYTANIA_WSZYSKIE =" ,_id, uwagi, poprzedni_rekord_id, poprzedni_rekord_data_usuniecia, poprzedni_rekord_powod_usuniecia, czy_widoczny";
    private static final String DATABASE_NAME = "BazaZliczanieCzasuZlecanByDex.db";
    //DATABASE_VERSION = 1;
    protected static final String DICTIONARY_TABLE_NAME_1 = "BZCZBD_Firmy";
    protected static final String DICTIONARY_TABLE_NAME_2 = "BZCZBD_Zlecenia";
    protected static final String DICTIONARY_TABLE_NAME_3 = "BZCZBD_Settings";
    protected static final String DICTIONARY_TABLE_NAME_4 = "BZCZBD_Kalendarze";

    /*protected static final String DICTIONARY_TABLE_NAME_3 = "ElektronicznaKsiazkaAutaByDexTypPaliwa";
    private static final String DICTIONARY_TABLE_NAME_4 = "ElektronicznaKsiazkaAutaByDexTankowania";
    protected static final String DICTIONARY_TABLE_NAME_5 = "ElektronicznaKsiazkaAutaByDexFirmy";
    protected static final String DICTIONARY_TABLE_NAME_6 = "ElektronicznaKsiazkaAutaByDexSieci";
    //DATABASE_VERSION = 2;
    protected static final String DICTIONARY_TABLE_NAME_7 = "ElektronicznaKsiazkaAutaByDexPrzegladyOkresowe";
    //protected static final String DICTIONARY_TABLE_NAME_8 = "ElektronicznaKsiazkaAutaByDexPrzegladyOkresoweStacjaKontroli";
    private static final String DICTIONARY_TABLE_NAME_9 = "ElektronicznaKsiazkaAutaByDexUbezpieczenieOC";
    //private static final String DICTIONARY_TABLE_NAME_10 = "ElektronicznaKsiazkaAutaByDexUbezpieczenieOCUbezpieczalnie";
    protected static final String DICTIONARY_TABLE_NAME_11 = "ElektronicznaKsiazkaAutaByDexEksploatacja";
    protected static final String DICTIONARY_TABLE_NAME_12 = "ElektronicznaKsiazkaAutaByDexEksploatacjaCzynnosci";
    protected static final String DICTIONARY_TABLE_NAME_13 = "ElektronicznaKsiazkaAutaByDexJednostki";
    protected static final String DICTIONARY_TABLE_NAME_14 = "ElektronicznaKsiazkaAutaByDexAutaCzesci";
    //DATABASE_VERSION = 3;
    private static final String DICTIONARY_TABLE_NAME_15 = "ElektronicznaKsiazkaAutaByDexPaliwaAuta";
    //DATABASE_VERSION = 5;
    protected static final String DICTIONARY_TABLE_NAME_16 = "ElektronicznaKsiazkaAutaByDexObslugaEksploatacyjna";
    //DATABASE_VERSION = 6
    //private static final String DICTIONARY_TABLE_NAME_17 = "ElektronicznaKsiazkaAutaByDexUbezpieczenieOCUbezpieczalnieSieci";
    //private static final String DICTIONARY_TABLE_NAME_18 = "ElektronicznaKsiazkaAutaByDexPrzegladyOkresoweStacjaKontroliSieci";
    //DATABASE_VERSION = 8
    //private static final String DICTIONARY_TABLE_NAME_19 = "ElektronicznaKsiazkaAutaByDexFirmy";
    //DATABASE_VERSION = 9
    private static final String DICTIONARY_TABLE_NAME_20 = "ElektronicznaKsiazkaAutaByDexKoszty";
    //DATABASE_VERSION = 11
    //private static final String DICTIONARY_TABLE_NAME_21= "ElektronicznaKsiazkaAutaByDexSieci";*/

    //ElektronicznaKsiazkaAutaByDexFirmy
    private static final String[][] DICTIONARY_TABLE_1_ROWS = {{"nazwa", "numer", "nr_telefonu", "ulica_nr", "miasto", "typ", "kalendarz_id"},
            {"text", "text", "text", "text", "text", "text", "Text"}};
    private static final String[][] DICTIONARY_TABLE_ROWS_1_FOREIGN = {{},
            {}};

    private static final String[][] DICTIONARY_TABLE_2_ROWS = {{"firma_id", "opis", "czas_rozpoczecia", "czas_zawieszenia", "czas_zakonczenia", "status", "rozliczona", "kalendarz_id", "kalendarz_zadanie_id"},
            {"integer", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT", "TEXT", "Integer", "TEXT"}};

    private static final String[][] DICTIONARY_TABLE_ROWS_2_FOREIGN = {{"firma_id", "kalendarz_id"},
            {"BZCZBD_Firmy(_id)", "BZCZBD_Settings(_id)"}};

    private static final String[][] DICTIONARY_TABLE_3_ROWS = {{"calendar_id", "accountName", "calendarDisplayName", "ownerAccount"},
            {"TEXT", "TEXT", "TEXT", "TEXT"}};
    private static final String[][] DICTIONARY_TABLE_ROWS_3_FOREIGN = {{},
            {}};

private static final String[][] DICTIONARY_TABLE_4_ROWS = {{"calendar_id", "accountName", "calendarDisplayName", "ownerAccount"},
            {"TEXT", "TEXT", "TEXT", "TEXT"}};
    private static final String[][] DICTIONARY_TABLE_ROWS_4_FOREIGN = {{},
            {}};


    private static final String[][] DICTIONARY_TABLES ={{},{}};

    public ObslugaSQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //onCreate zostanie uruchomiony automatycznie, gdy odwołujemy sie 1 raz do bazy i ona nie istnieje, później się nie wykona
        Log.d("DebugCSQL:", "tworzenie baz");
        //db.execSQL("PRAGMA foreign_keys = ON;");
        db.execSQL(tworzStringBazy(DICTIONARY_TABLE_2_ROWS, DICTIONARY_TABLE_ROWS_2_FOREIGN, DICTIONARY_TABLE_NAME_2));
        Log.d("DebugCSQL:", "tworzenie baz: " + DICTIONARY_TABLE_NAME_2);
        db.execSQL(tworzStringBazy(DICTIONARY_TABLE_1_ROWS, DICTIONARY_TABLE_ROWS_1_FOREIGN, DICTIONARY_TABLE_NAME_1));
        Log.d("DebugCSQL:", "tworzenie baz: " + DICTIONARY_TABLE_NAME_1);
        db.execSQL(tworzStringBazy(DICTIONARY_TABLE_3_ROWS, DICTIONARY_TABLE_ROWS_3_FOREIGN, DICTIONARY_TABLE_NAME_3));
        Log.d("DebugCSQL:", "tworzenie baz: " + DICTIONARY_TABLE_NAME_3);
        Log.d("DebugCSQL:", "po tworzenie baz");
        //Database 11
        //ustawieniePoczatkowychWartosci(db);
        //db.close();
    }

    private String tworzStringBazy(String[][] daneTablicy, String[][] daneForeign, String nazwaTablicy){

        String zapytanieSQL = "CREATE TABLE " + nazwaTablicy + " (" + "_id integer primary key autoincrement,";
        for (int kolumna = 0; kolumna < daneTablicy[0].length; kolumna++){
            for (int wiersz = 0; wiersz < 2; wiersz++){
                zapytanieSQL = zapytanieSQL + " " + daneTablicy[wiersz][kolumna];
            }
            zapytanieSQL = zapytanieSQL + ",";

        }
        zapytanieSQL = zapytanieSQL + " uwagi text, " +
                "poprzedni_rekord_id integer, " +
                "poprzedni_rekord_data_usuniecia text, " +
                "poprzedni_rekord_powod_usuniecia text, " +
                "czy_widoczny integer";

        for (int kolumna = 0; kolumna < daneForeign[0].length; kolumna++){
            for (int wiersz = 0; wiersz < 2; wiersz++){
                if (wiersz == 0) {
                    zapytanieSQL = zapytanieSQL + ", FOREIGN KEY (" + daneForeign[wiersz][kolumna];
                }else{
                    zapytanieSQL = zapytanieSQL + ") REFERENCES " + daneForeign[wiersz][kolumna];
                }
            }
        }
        zapytanieSQL = zapytanieSQL + "); ";

        Log.d("zapytanie", zapytanieSQL);
        Log.d("dlugosc: ", String.valueOf(daneTablicy.length));

         return zapytanieSQL;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //db.execSQL(DROP_TODO_TABLE);
        /*mozna też dodawać kolumny do tabel:
        private static final String DATABASE_ALTER_TEAM_1 = "ALTER TABLE "
        + TABLE_TEAM + " ADD COLUMN " + COLUMN_COACH + " string;";

    private static final String DATABASE_ALTER_TEAM_2 = "ALTER TABLE "
        + TABLE_TEAM + " ADD COLUMN " + COLUMN_STADIUM + " string;";
        Podpierdolone z https://thebhwgroup.com/blog/how-android-sqlite-onupgrade
         */
        //db = getWritableDatabase();
        Log.d("sqls:", String.valueOf(oldVersion));
        Log.d("sqln:", String.valueOf(newVersion));
        if (oldVersion < 2) {
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_3_ROWS, DICTIONARY_TABLE_ROWS_3_FOREIGN, DICTIONARY_TABLE_NAME_3));
            Log.d("DebugCSQL:", "tworzenie baz upgrade: " + tworzStringBazy(DICTIONARY_TABLE_3_ROWS, DICTIONARY_TABLE_ROWS_3_FOREIGN, DICTIONARY_TABLE_NAME_3));
        }
         /*   db.execSQL(tworzStringBazy(DICTIONARY_TABLE_9_ROWS, DICTIONARY_TABLE_ROWS_9_FOREIGN, DICTIONARY_TABLE_NAME_9));
            Log.d("DebugCSQL:", "tworzenie baz upgrade: " + tworzStringBazy(DICTIONARY_TABLE_9_ROWS, DICTIONARY_TABLE_ROWS_9_FOREIGN, DICTIONARY_TABLE_NAME_9));
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_11_ROWS, DICTIONARY_TABLE_ROWS_11_FOREIGN, DICTIONARY_TABLE_NAME_11));
            Log.d("DebugCSQL:", "tworzenie baz upgrade: " + tworzStringBazy(DICTIONARY_TABLE_11_ROWS, DICTIONARY_TABLE_ROWS_11_FOREIGN, DICTIONARY_TABLE_NAME_11));
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_12_ROWS, DICTIONARY_TABLE_ROWS_12_FOREIGN, DICTIONARY_TABLE_NAME_12));
            Log.d("DebugCSQL:", "tworzenie baz upgrade: " + tworzStringBazy(DICTIONARY_TABLE_12_ROWS, DICTIONARY_TABLE_ROWS_12_FOREIGN, DICTIONARY_TABLE_NAME_12));
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_13_ROWS, DICTIONARY_TABLE_ROWS_13_FOREIGN, DICTIONARY_TABLE_NAME_13));
            Log.d("DebugCSQL:", "tworzenie baz upgrade: " + tworzStringBazy(DICTIONARY_TABLE_13_ROWS, DICTIONARY_TABLE_ROWS_13_FOREIGN, DICTIONARY_TABLE_NAME_13));
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_14_ROWS, DICTIONARY_TABLE_ROWS_14_FOREIGN, DICTIONARY_TABLE_NAME_14));
            Log.d("DebugCSQL:", "tworzenie baz upgrade: " + tworzStringBazy(DICTIONARY_TABLE_14_ROWS, DICTIONARY_TABLE_ROWS_14_FOREIGN, DICTIONARY_TABLE_NAME_14));
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_15_ROWS, DICTIONARY_TABLE_ROWS_15_FOREIGN, DICTIONARY_TABLE_NAME_15));
            Log.d("DebugCSQL:", "tworzenie baz upgrade: " + tworzStringBazy(DICTIONARY_TABLE_15_ROWS, DICTIONARY_TABLE_ROWS_15_FOREIGN, DICTIONARY_TABLE_NAME_15));
            //Toast.makeText(super..context, "Creating backup took " + (endTime - starTime) + "ms.", Toast.LENGTH_SHORT).show();
        }//if (oldVersion < 2){*/
        if (oldVersion < 3){
            db.execSQL("ALTER TABLE " + DICTIONARY_TABLE_NAME_2 + " ADD COLUMN kalendarz_id INTEGER");
            db.execSQL("ALTER TABLE " + DICTIONARY_TABLE_NAME_2 + " ADD COLUMN kalendarz_zadanie_id TEXT");
        }//if (oldVersion < 3){
        if (oldVersion < 4){
            db.execSQL("ALTER TABLE " + DICTIONARY_TABLE_NAME_1 + " ADD COLUMN kalendarz_id INTEGER");
        }//if (oldVersion < 4){
        if (oldVersion < 5){
            //db.execSQL("ALTER TABLE " + DICTIONARY_TABLE_NAME_1 + " ADD COLUMN kalendarz_id INTEGER");
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_1_ROWS, DICTIONARY_TABLE_ROWS_1_FOREIGN, DICTIONARY_TABLE_NAME_1 + "_nowa"));
            Log.d("DebugCSQL:", "tworzenie baz: " + DICTIONARY_TABLE_NAME_1 + "_nowa");
            db.execSQL("INSERT INTO " + DICTIONARY_TABLE_NAME_1 + "_nowa SELECT * FROM " + DICTIONARY_TABLE_NAME_1);
            Log.d("DebugCSQL:", "INSERT INTO " + DICTIONARY_TABLE_NAME_1 + "_nowa SELECT * FROM " + DICTIONARY_TABLE_NAME_1);
            db.execSQL("ALTER TABLE " + DICTIONARY_TABLE_NAME_1 + " RENAME TO " + DICTIONARY_TABLE_NAME_1 + "_old");
            Log.d("DebugCSQL:", "ALTER TABLE " + DICTIONARY_TABLE_NAME_1 + " RENAME TO " + DICTIONARY_TABLE_NAME_1 + "_old");
                    db.execSQL("ALTER TABLE " + DICTIONARY_TABLE_NAME_1 + "_nowa RENAME TO " + DICTIONARY_TABLE_NAME_1);
            Log.d("DebugCSQL:", "ALTER TABLE " + DICTIONARY_TABLE_NAME_1 + "_nowa RENAME TO " + DICTIONARY_TABLE_NAME_1);
        }//if (oldVersion < 4){
        if (oldVersion < 6){
            db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME_1 + "_old");
            Log.d("DebugCSQL:", "DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME_1 + "_old");
        }//if (oldVersion < 4){
        /*if (oldVersion < 5){
            db.execSQL("ALTER TABLE " + DICTIONARY_TABLE_NAME_11 + " ADD COLUMN interwal_czas text");
            db.execSQL("ALTER TABLE " + DICTIONARY_TABLE_NAME_11 + " ADD COLUMN interwal_przebieg integer");
            db.execSQL("ALTER TABLE " + DICTIONARY_TABLE_NAME_11 + " ADD COLUMN powtarzalny integer");
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_16_ROWS, DICTIONARY_TABLE_ROWS_16_FOREIGN, DICTIONARY_TABLE_NAME_16));
            Log.d("DebugCSQL:", "tworzenie baz: " + DICTIONARY_TABLE_NAME_16);
            //String[] blabla = {"auto_id:integer", "eksploatacja_id:integer", "data:string", "przebieg:integer", "ilosc:float", "jednostka_id:integer"};
            //tablica(blabla);
        }//if (oldVersion < 5){
        if (oldVersion < 6){

        }//if (oldVersion < 6){
        if (oldVersion < 7) {

        }//if (oldVersion < 7) {
        if (oldVersion < 8) {

        }//if (oldVersion < 8) {
        if (oldVersion < 9) {
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_20_ROWS, DICTIONARY_TABLE_ROWS_20_FOREIGN, DICTIONARY_TABLE_NAME_20));
            Log.d("DebugCSQL:", "tworzenie baz: " + DICTIONARY_TABLE_NAME_20);
        }//if (oldVersion < 9) {
        if (oldVersion < 10) {
            db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME_12);
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_12_ROWS, DICTIONARY_TABLE_ROWS_12_FOREIGN, DICTIONARY_TABLE_NAME_12));
            Log.d("DebugCSQL:", "tworzenie baz upgrade: " + tworzStringBazy(DICTIONARY_TABLE_12_ROWS, DICTIONARY_TABLE_ROWS_12_FOREIGN, DICTIONARY_TABLE_NAME_12));
        }//if (oldVersion < 10) {
        if (oldVersion < 11) {

        }//if (oldVersion < 10) {
        if (oldVersion < 12) {
            db.execSQL("DROP TABLE IF EXISTS ElektronicznaKsiazkaAutaByDexSieci");
            Log.d("DebugCSQL:", "usuwanie tabeli: ElektronicznaKsiazkaAutaByDexSieci");
            db.execSQL("INSERT INTO " + DICTIONARY_TABLE_NAME_6 + "(nazwa) SELECT nazwa FROM ElektronicznaKsiazkaAutaByDexSieciTankowania");
            Log.d("DebugCSQL:", "przenoszenie danych do nowej tabeli");
            db.execSQL("DROP TABLE IF EXISTS ElektronicznaKsiazkaAutaByDexSieciTankowania");
            Log.d("DebugCSQL:", "usuwanie starej tabeli ElektronicznaKsiazkaAutaByDexSieciTankowania");
            db.execSQL("DROP TABLE IF EXISTS ElektronicznaKsiazkaAutaByDexFirmy");
            Log.d("DebugCSQL:", "usuwanie tabeli: ElektronicznaKsiazkaAutaByDexFirmy");
            db.execSQL(tworzStringBazy(DICTIONARY_TABLE_5_ROWS, DICTIONARY_TABLE_ROWS_5_FOREIGN, DICTIONARY_TABLE_NAME_5));
            Log.d("DebugCSQL:", "tworzenie baz upgrade: " + tworzStringBazy(DICTIONARY_TABLE_5_ROWS, DICTIONARY_TABLE_ROWS_5_FOREIGN, DICTIONARY_TABLE_NAME_5));
            db.execSQL("INSERT INTO " + DICTIONARY_TABLE_NAME_5 + "(nazwa, numer, ulica_nr, miasto, siec_id) SELECT nazwa_sieci_tankowania, numer_stacji, ulica_nr, miasto, siec_id FROM ElektronicznaKsiazkaAutaByDexMiejscaTankowania");
            Log.d("DebugCSQL:", "przenoszenie danych do nowej tabeli");
            db.execSQL("DROP TABLE IF EXISTS ElektronicznaKsiazkaAutaByDexMiejscaTankowania");
            Log.d("DebugCSQL:", "usuwanie starej tabeli ElektronicznaKsiazkaAutaByDexMiejscaTankowania");
            db.execSQL("DROP TABLE IF EXISTS ElektronicznaKsiazkaAutaByDexPrzegladyOkresoweStacjaKontroliSieci");
            Log.d("DebugCSQL:", "usuwanie starej tabeli ElektronicznaKsiazkaAutaByDexPrzegladyOkresoweStacjaKontroliSieci");
            db.execSQL("DROP TABLE IF EXISTS ElektronicznaKsiazkaAutaByDexUbezpieczenieOCUbezpieczalnieSieci");
            Log.d("DebugCSQL:", "usuwanie starej tabeli ElektronicznaKsiazkaAutaByDexUbezpieczenieOCUbezpieczalnieSieci");
            db.execSQL("DROP TABLE IF EXISTS ElektronicznaKsiazkaAutaByDexUbezpieczenieOCUbezpieczalnie");
            Log.d("DebugCSQL:", "usuwanie starej tabeli ElektronicznaKsiazkaAutaByDexUbezpieczenieOCUbezpieczalnie");
            db.execSQL("DROP TABLE IF EXISTS ElektronicznaKsiazkaAutaByDexPrzegladyOkresoweStacjaKontroli");
            Log.d("DebugCSQL:", "usuwanie starej tabeli ElektronicznaKsiazkaAutaByDexPrzegladyOkresoweStacjaKontroli");
        }//if (oldVersion < 10) {*/
        //db.close();
        Log.d("DebugCSQL:", "po upgrade tworzenie baz");
    }

    public void usunDaneZTabeli(String nazwa_tabeli){
        Log.d("DebugCSQL:", "Przygotowanie do usuniecia danych z tabeli" + nazwa_tabeli);
        SQLiteDatabase db = getWritableDatabase();
        try {
            //db.execSQL("delete from " + nazwa_tabeli);
            //db.execSQL("vacuum");
            db.delete(nazwa_tabeli, null, null);
            db.execSQL("UPDATE SQLITE_SEQUENCE SET SEQ= "+ 0 + " WHERE NAME='" + nazwa_tabeli +"'");
            //db.delete(nazwa_tabeli, null, null);

        } catch(SQLException excepion){
            Log.d("DebugCSQL:", "Wystapil problem przy usuwaniu danych w " + nazwa_tabeli);
            Log.d("wyjatek", excepion.toString());
        }
    }//private void usunDaneZTabeli(String nazwa_tabeli){

    /*public void wstawDanePobraneZCSV(String nazwa_tabeli, ContentValues wartosci){
        Log.d("ObslugaSQL_ZCSV", "poczatek");
        Log.d("osql_nazwaTabeli", nazwa_tabeli);
        if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexSieciTankowania")){
            //"ElektronicznaKsiazkaAutaByDexSieciTankowania"
            //wartosci.getAsInteger("poprzedni_rekord_id");
            Log.d("wstawDanePobraneZCSV", wartosci.getAsString("nazwa_sieci_tankowania"));
            ContentValues wartosci1 = new ContentValues();
            wartosci1.put("nazwa", wartosci.getAsString("nazwa"));

            dodajDaneOSQL(DICTIONARY_TABLE_NAME_6, wartosci);

        }//if (nazwa_tabeli = "ElektronicznaKsiazkaAutaByDexSieciTankowania"){

        if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexMiejscaTankowania")){
            ContentValues wartosci1 = new ContentValues();

            wartosci.put("nazwa", wartosci.getAsString("nazwa"));
            wartosci.put("numer_stacji", wartosci.getAsString("numer_stacji"));
            wartosci.put("ulica_nr", wartosci.getAsString("ulica_nr"));
            wartosci.put("miasto", wartosci.getAsString("miasto"));
            wartosci.put("siec_id", wartosci.getAsInteger("siec_id"));
            Log.d("SQL: dDMT", wartosci.toString());
            dodajDaneOSQL("ElektronicznaKsiazkaAutaByDexMiejscaTankowania", wartosci1);
           

        }//if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexMiejscaTankowania"){
        if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexTankowania")) {
            daneTankowaniaAuta tankowanie_auta = new daneTankowaniaAuta();
            tankowanie_auta.setAuto_id(wartosci.getAsInteger("auto_id"));//        "auto_id Integer, " +
            tankowanie_auta.setData_spr(wartosci.getAsLong("data_spr"));//         "data_spr text, " +
            tankowanie_auta.setPrzebieg(wartosci.getAsInteger("przebieg"));//        "przebieg integer, " +
            tankowanie_auta.setMiejsce_tankowania(wartosci.getAsInteger("miejsce_tankowania_id"));//        "miejsce_tankowania_id integer, " +
            tankowanie_auta.setIlosc_paliwa(wartosci.getAsFloat("ilosc_paliwa"));//        "ilosc_paliwa_1 float, " +
            tankowanie_auta.setCena_paliwa(wartosci.getAsFloat("cena_paliwa"));//         "cena_paliwa_1 float, " +
            tankowanie_auta.setRodzaj_paliwa_id(wartosci.getAsInteger("rodzaj_paliwa_id"));//        "rodzaj_paliwa_1_id integer, " +
            //tankowanie_auta.setRodzaj_paliwa_nazwa(wartosci.getAsString("rodzaj_paliwa_nazwa"));

            dodajDaneTankowania(tankowanie_auta);
        }//if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexTankowania"){

        if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexPrzegladPlynow")) {
            ContentValues wartosci1 = new ContentValues();

            wartosci1.put("olej", wartosci.getAsString("olej"));
            wartosci1.put("plyn_chlodniczy", wartosci.getAsString("plyn_chlodniczy"));
            wartosci1.put("plyn_do_spryskiwaczy", wartosci.getAsString("plyn_do_spryskiwaczy"));
            wartosci1.put("plyn_wspomagania_kierownicy", wartosci.getAsString("plyn_wspomagania_kierownicy"));
            wartosci1.put("plyn_hamulcowy", wartosci.getAsString("plyn_hamulcowy"));
            wartosci1.put("przebieg", wartosci.getAsInteger("przebieg"));
            wartosci1.put("data_spr", wartosci.getAsLong("data_spr"));
            wartosci1.put("auto_id", wartosci.getAsInteger("auto_id"));

            dodajDaneOSQL("ElektronicznaKsiazkaAutaByDexPrzegladPlynow", wartosci1);
        }//if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexPrzegladPlynow")){

        if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexAuta")) {
            ContentValues wartosci1 = new ContentValues();
            wartosci1.put("marka", wartosci.getAsString("marka"));
            wartosci1.put("model", wartosci.getAsString("model"));
            wartosci1.put("silnik", wartosci.getAsString("silnik"));
            wartosci1.put("rok_produkcji", wartosci.getAsInteger("rok_produkcji"));
            wartosci1.put("moc", wartosci.getAsInteger("moc"));
            wartosci1.put("typ_paliwa_id", wartosci.getAsInteger("typ_paliwa_id"));
            wartosci1.put("nazwa", wartosci.getAsString("nazwa"));
            wartosci1.put("nr_vin", wartosci.getAsString("nr_vin"));
            wartosci1.put("nr_rejestracyjny", wartosci.getAsString("nr_rejestracyjny"));
            wartosci1.put("data_1_rejestracji", wartosci.getAsString("data_1_rejestracji"));
            wartosci1.put("data_wydania_dowodu", wartosci.getAsString("data_wydania_dowodu"));
            Log.w("auto", wartosci1.toString());
            dodajDaneOSQL("ElektronicznaKsiazkaAutaByDexAuta", wartosci1);
        }//if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexAuta")){

        if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexTypPaliwa")) {
            daneRodzajPaliwa rodzaj_paliwa = new daneRodzajPaliwa();
            rodzaj_paliwa.setTyp_paliwa(wartosci.getAsString("typ_paliwa"));

            dodajDaneRodzajPaliwa(rodzaj_paliwa);
        }//if (nazwa_tabeli.equals("ElektronicznaKsiazkaAutaByDexTypPaliwa")){
    }//public void wstawDanePobraneZCSV(String nazwa_tabeli, ContentValues wartosci){*/

    protected void dodajDaneOSQL(String nazwa_tabeli, ContentValues wartosci){
        Log.d("DebugCSQL:", nazwa_tabeli);
        
        SQLiteDatabase db = getWritableDatabase();

        try {
            //db.insert(nazwa_tabeli, null, wartosci);
            db.insertOrThrow(nazwa_tabeli, null, wartosci);
        } catch(SQLException excepion){
            Log.d("Wywalilo", nazwa_tabeli);
            Log.d("wyjatek", excepion.toString());
            //tutaj trzeba coś dopisać
            //CheckLista.wyswietlToast("Błąd insertu!!!");
            //CheckLista.
        }
        Log.d("DebugCSQL:", "koniec wstawiania danych do tabeli: " + nazwa_tabeli);
        db.close();
    }//private void dodajDane(){

    protected void updateDaneOSQL(String nazwa_tabeli, ContentValues wartosci, Integer _id_){
        Log.d("DebugCSQL:", nazwa_tabeli);

        SQLiteDatabase db = getWritableDatabase();

        try {
            //db.insert(nazwa_tabeli, null, wartosci);
            db.update(nazwa_tabeli,  wartosci, "_id = ?", new String[] {String.valueOf(_id_)});
        } catch(SQLException excepion){
            Log.d("Wywalilo", nazwa_tabeli);
            Log.d("wyjatek", excepion.toString());
            //tutaj trzeba coś dopisać
            //CheckLista.wyswietlToast("Błąd insertu!!!");
            //CheckLista.
        }
        Log.d("DebugCSQL:", "koniec update danych do tabeli: " + nazwa_tabeli);
        db.close();
    }//private void updateDaneOSQL(){

    protected void dodajDaneW(String nazwa_tabeli, ContentValues wartosci, SQLiteDatabase db){
        Log.d("DebugCSQL:", nazwa_tabeli);

        //SQLiteDatabase db = getWritableDatabase();

        try {
            db.insertOrThrow(nazwa_tabeli, null, wartosci);
        } catch(SQLException excepion){
            //tutaj trzeba coś dopisać
            //CheckLista.wyswietlToast("Błąd insertu!!!");
            //CheckLista.
        }
        Log.d("DebugCSQL:", "koniec wstawiania danych do tabeli: " + nazwa_tabeli);
        //db.close();
    }//private void dodajDane(){

    public void zamknijBaze(SQLiteDatabase db){
        //SQLiteDatabase db = null;
        db.close();
    }//public void zamknijBaze(){

    public void kasujDane(int id, String nazwaTabeli) {
        Log.d("DebugCSQL:", "kasujDane");
        SQLiteDatabase db = getWritableDatabase();
        String[] argumenty = {"" + id};
        db.delete(nazwaTabeli, "_id=?", argumenty);
        Log.d("DebugCSQL:", "po kasujDane");
        db.close();
    }

    public void zrobKopieBazy(String  sciezka, Context context){
        SQLiteDatabase db = getReadableDatabase();
        try {
            BackupExportFromSQL.export(db, sciezka, context);
        } catch (IOException e) {
            e.printStackTrace();
            //Log.d("blad exportu: ", e.printStackTrace())
        }
        db.close();
    }//public void zrobKopieBazy(){

    //podpierdolone z https://stackoverflow.com/questions/31367270/exporting-sqlite-database-to-csv-file-in-android
    public List<String> getTablesOnDataBase(){
        Cursor c = null;
        List<String> tables = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try{
            c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            if (c.moveToFirst()) {
                while ( !c.isAfterLast() ) {
                    tables.add(c.getString(0));
                    c.moveToNext();
                }
            }
        }
        catch(Exception throwable){
            Log.e("ExportPodajTabele", "Could not get the table names from db", throwable);
        }
        finally{
            if(c!=null)
                c.close();
        }
        db.close();
        return tables;
    }
}