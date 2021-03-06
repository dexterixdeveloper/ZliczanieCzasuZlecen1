package pl.vot.dexterix.zliczanieczasuzlecen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class FragmentFirma extends FragmentPodstawowy {

    daneFirma danaKlasy = new daneFirma();
    private int przeniesioneID = 0;
    private TextInputEditText textInputEditTextNazwa;
    private TextInputEditText textInputEditTextUlicaNr;
    private TextInputEditText textInputEditTextMiasto;
    private TextInputEditText textInputEditTextUwagi;
    private TextInputEditText textInputEditTextNrTelefonu;


    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //wyłapujemy dane przekazane z formatki wywołującej
        Bundle bundleArgumenty = getArguments();
        if (bundleArgumenty != null) {
            przeniesioneID = bundleArgumenty.getInt("id");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_firma, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //odpalamy klasę odpowiedzialną za datę:\
        daneData aktualnaData = new daneData();
        //Odpalamy okienko opis:
        textInputEditTextNazwa = (TextInputEditText) view.findViewById(R.id.textInputEditTextNazwa);
        textInputEditTextUlicaNr = (TextInputEditText) view.findViewById(R.id.textInputEditTextUlicaNr);
        textInputEditTextMiasto = (TextInputEditText) view.findViewById(R.id.textInputEditTextMiasto);
        textInputEditTextUwagi = (TextInputEditText) view.findViewById(R.id.textInputEditTextUwagi);
        textInputEditTextNrTelefonu = (TextInputEditText) view.findViewById(R.id.textInputEditTextNrTelefonu);
        //sprawdzamy czy mamy zapisane kalendarze w bazie
        OSQLdaneKalendarzy osqlk = new OSQLdaneKalendarzy(getActivity());
        List<daneKalendarza> kalendarze_w_bazie = osqlk.dajWszystkie();


        dodajDoSpinnerWybierzKalendarz(R.string.wybierz, 0L);

        //Fragment z1 = getVisibleFragment();

        Button buttonDodaj = (Button) view.findViewById(R.id.buttonDodaj);
        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                zapiszDane();
                //finish();
                //getActivity().finish();
                //(FragmentFirma.this).
                cofnijDoPoprzedniegoFragmentu();

            }
        });
        //sprawdzamy czy przekazano id z poprzedniej klasy
        if (przeniesioneID > 0) {
            buttonDodaj.setText("Aktualizuj");
            OSQLdaneFirma osql = new OSQLdaneFirma(getActivity());
            danaKlasy = osql.dajOkreslonyRekord(przeniesioneID);
            Log.d("FragmentFirma:  ", String.valueOf(przeniesioneID));
            if (danaKlasy instanceof daneFirma){//.getNazwa().isEmpty()){
                //tu podobna sytuacja, nie ma kalendarza wiec sie niew wyswietli
                Log.d("FragmentFirma1:  ", String.valueOf(przeniesioneID));
                danaKlasy = osql.dajOkreslonyRekordBezKalendarza(przeniesioneID);
            }
            //ustawiamy parametry klasy
            textInputEditTextUwagi.setText(String.valueOf(danaKlasy.getUwagi()));
            textInputEditTextNazwa.setText(String.valueOf(danaKlasy.getNazwa()));
            textInputEditTextUlicaNr.setText(String.valueOf(danaKlasy.getUlicaNr()));
            textInputEditTextMiasto.setText(String.valueOf(danaKlasy.getMiasto()));
            textInputEditTextNrTelefonu.setText(String.valueOf(danaKlasy.getNr_telefonu()));
            dodajDoSpinnerWybierzKalendarz(R.string.wybierz, danaKlasy.getKalendarz_id());
            Log.d("danePojedyncze", danaKlasy.toStringDoRecyclerView());


        }


    }

    //to daje nam możliwość przekazania danych do tego fragmentu
    public static FragmentFirma newInstance(int someInt) {
        FragmentFirma fragmentDemo = new FragmentFirma();
        Bundle args = new Bundle();
        args.putInt("id", someInt);

        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }

    //listener na jkiś tam guzik przykładowy
    /*public void addListenerOnButtonGuzik(Integer guzik){
        Log.d("DodajAuto", "addListenerOnButtonDodajRodzajPaliwa");
        Button buttonGuzik = (Button) view.findViewById(guzik);
        buttonGuzik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1 to nazwa tej formatki, 2 to wywołanej
                if (!String.valueOf(textInputEditTextNazwa.getText()).isEmpty()) {
                    //danaKlasy.toString();
                    zapiszDane();
                    finish();
                }//if (!String.valueOf(textInputEditTextEksploatacjaJednostka.getText()).isEmpty()) {
                else {
                    Toast.makeText(pl.vot.dexterix.elektronicznaksiazkaautabydex.ActivityFirmaDodaj.this,
                            "Musisz przynajmnie podać nazwę",
                            Toast.LENGTH_SHORT).show();
                    textInputEditTextNazwa.setError("Wymagana nazwa!");

                }

            }//public void onClick(View v) {
        });//buttonDodajRodzajPaliwa.setOnClickListener(
    }//public void addListenerOnButtonGuzik(){*/



    public void dodajDoSpinnerWybierzKalendarz(final Integer wybierz, long wybor) {
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinnerWybierzKalendarz);
        //OSQLdaneFirma dA = new OSQLdaneFirma(getActivity());
        Log.d("Spinner", "2)");
        //List<daneKalendarza> danaSpinneraKlasa = new LinkedList<>();//;//List<daneKalendarza> wybierzKalendarzDoZapisu()
        //pobieramy kalendarze z telefonu
        //danaSpinneraKlasa = wybierzKalendarzDoZapisu();
        //pobieramy kalendarze z bazy
        OSQLdaneKalendarzy osql = new OSQLdaneKalendarzy(getActivity());
        ArrayList<String[]> danaSpinnera = osql.podajNazwa();//dajWszystkieDoRecyclerView();//dajWszystkie();

        //List<String> danaSpinnera =null;
        /*for (int i = 0; i < danaSpinneraKlasa.size(); i++){
            String dana_for ="";
            dana_for = String.valueOf(i) + " " + danaSpinneraKlasa.get(i).getCalendarDisplayName();
            danaSpinnera.add(dana_for);
        }*/

        //danaSpinneraKlasa.
        //danaSpinnera = danaSpinneraKlasa.
        //danaSpinnera.add("0 Dodaj");
        //danaSpinnera.add(0, getString(dodaj));
        String[] staleSpinnera = {String.valueOf(0), getString(wybierz)};
        danaSpinnera.add(0, staleSpinnera);
        //Spinner spinner = (Spinner) findViewById(rSpinner);
        //RFWSpinner.przygotujSpinner(danaSpinnera,this,spinner);
        //adapter stary
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, danaSpinnera);
        SpinnerCustomAdapter adapter = new SpinnerCustomAdapter(getActivity(), danaSpinnera);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Log.d("FragmentFirma: " + "wybor: ", String.valueOf(wybor));
        if (wybor > 0L ) {
            Log.d("Firma wybor", String.valueOf(wybor));
            for (int i = 1; i < danaSpinnera.size(); i++) {
                Log.d("FragmentFirma: " + "wybor: " + "dana: " , danaSpinnera.get(i)[0]);
                if (danaSpinnera.get(i)[0].equals(String.valueOf(wybor))){
                    Log.d("Firma wybor 2", String.valueOf(i));
                    spinner.setSelection(i);
                }
            }
        }
        //List<daneKalendarza> finalDanaSpinnera = danaSpinnera;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String poszukiwanie = String.valueOf(adapterView.getSelectedItem());
                Log.d("poszukiwanie: ", poszukiwanie);
                if (!(poszukiwanie==getString(wybierz))){
                    //int kalendarz = Integer.valueOf(poszukiwanie.substring(0,String.valueOf(poszukiwanie).indexOf(" ")));

                    danaKlasy.setKalendarz_id(Long.valueOf(danaSpinnera.get(i)[0]));
                    Log.d("ID Kalendarza ", String.valueOf(danaKlasy.getKalendarz_id()));

                    danaKlasy.setKalendarz_nazwa(danaSpinnera.get(i)[1]);
                    Log.d("ID Kalendarza ", danaKlasy.getKalendarz_nazwa());
                    //danaKlasy.setKalendarz_nazwa(poszukiwanie.substring(0,String.valueOf(poszukiwanie).indexOf(" ")));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }//public void dodajDoSpinnerEksploatacjaDodajCzynnosc(Integer rSpinner) {

    private void zapiszDane(){

        danaKlasy.setNazwa(String.valueOf(textInputEditTextNazwa.getText()));
        danaKlasy.setUlicaNr(String.valueOf(textInputEditTextUlicaNr.getText()));
        danaKlasy.setMiasto(String.valueOf(textInputEditTextMiasto.getText()));
        Log.d("FragmentFirma: set Miasto: ", danaKlasy.getMiasto());
        danaKlasy.setUwagi(String.valueOf(textInputEditTextUwagi.getText()));
        danaKlasy.setNr_telefonu(Integer.valueOf(String.valueOf(textInputEditTextNrTelefonu.getText())));

        Log.d("dana klasy: ", danaKlasy.toString());
        OSQLdaneFirma osql = new OSQLdaneFirma(getActivity());

        if (przeniesioneID > 0) {
            osql.updateDane(danaKlasy);
        }else{
            osql.dodajDane(danaKlasy);
        }
        //setPrzebieg(Integer.parseInt(String.valueOf(textInputEditTextPrzebiegTankowania.getText())));
    }//private void zapiszDane(){
}