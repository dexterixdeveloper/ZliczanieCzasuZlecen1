package pl.vot.dexterix.zliczanieczasuzlecen;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "PowiadomienieZliczanieCzasuZlecen";
    public final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createNotificationChannel();


        String tagBackStack = "FragmentStart";
        zmianaFragmentu(new FragmentZadaniaDoZrobienia(), tagBackStack, 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            String tagBackStack = "FragmentSettings";
            zmianaFragmentu(new FragmentSettings(), tagBackStack, 1);
            return true;
        }

        if (id == R.id.action_firmy) {
            String tagBackStack = "FragmentFirmy";
            zmianaFragmentu(new FragmentFirmy(), tagBackStack, 1);
            return true;
        }

        if (id == R.id.action_zadania_archiwalne) {
            String tagBackStack = "FragmentZadaniaArchiwalne";
            zmianaFragmentu(new FragmentZadaniaArchiwalne(), tagBackStack, 1);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void zmianaFragmentu(Fragment fragmencik, String tagBackStack, Integer iCzyBackStack){
        // Begin the transaction
        Log.d("Back2", "2");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container_main, fragmencik);
        if (iCzyBackStack > 0) {
            ft.addToBackStack(tagBackStack);
        }
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
    }

    protected String getVisibleFragment(FragmentManager fragmentManager){
        //FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        Log.d("Ilo???? fragment??w: ", String.valueOf(fragments.size()));
        if(fragments != null){
            for(Fragment fragment : fragments){
                //fragment.
                Log.d("fragment1", String.valueOf(fragment.toString()));
                if(fragment != null && fragment.isVisible()) {
                    Log.d("fragment", String.valueOf(fragment.getTag()));
                    return fragment.getTag();
                }
            }
        }
        return null;
    }

    @Override
    public void onBackPressed(){
        //FragmentManager fm = getSupportFragmentManager();

        //fm.findFragmentByTag("FragmentZadanie");
        //Log.d("MainActivity", getVisibleFragment(fm));

        if (fm.getBackStackEntryCount() > 0) {
            if (("FragmentZadanie").equals(getVisibleFragment(fm)) || ("FragmentFirma").equals(getVisibleFragment(fm))) {

                Log.d("MaaiActivity", "chyba si?? uda??o");
                //otwieramy okienko dialogowe
                pokazOkienkoAlertuZeNieZapisalesDanych("Chc?? wyj????", "Chc?? zapisa??","Uwaga", "Nie zapisa??e?? danych. Czy na pewno chcesz wyj?????", false);


            }else {

                Log.i("MainActivity", "cofamy si?? do poprzedniego fragmentu");
                //Log.d("MainActivity 3", String.valueOf(wynikGuzika));
                fm.popBackStack();
            }
        } else {
            Log.i("MainActivity", "nic w backstack, calling super");
            pokazOkienkoAlertuZeNieZapisalesDanych("Chc?? wyj????", "Zostaj??", "Koniec programu?", "Czy chcesz zako??czy?? program?", true);

                //super.onBackPressed();

        }
    }

    private void pokazOkienkoAlertuZeNieZapisalesDanych(String sPositive, String sNegative, String sTytul, String sWiadomowsc, boolean bCzyKoniec){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setTitle(sTytul);
        alertDialog.setMessage(sWiadomowsc);
        /*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "nNeutralny",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });*/
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, sPositive,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (!bCzyKoniec) {
                            fm.popBackStack();
                        }else {
                            finish();
                        }
                        dialog.dismiss();

                        //onBackPressed()..super.onBackPressed();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, sNegative,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

        alertDialog.show();


    }
    /*public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                Log.d("fragment1", fragment.toString());
                if(fragment != null && fragment.isVisible())
                    Log.d("fragment", fragment.toString());
                    return fragment;
            }
        }
        return null;
    }*/

    //Nadpisujemy akcj?? przy naci??nie??iu przycisku powrotu/tr??jk??ta
    /*@Override
    public void onBackPressed(){
        // do something here and don't write super.onBackPressed()
        Log.d("naci??ni??ty guzik", "back");
        FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.get.findFragmentById(R.id.fragment_zadanie);
        FragmentZadanie fragment =
                (FragmentZadanie ) fragmentManager.findFragmentById(R.id.fragment_zadanie);

        //Fragment f = this.FragmentManager().findFragmentById(R.id.fragment_zadanie);
        //if (f instanceof FragmentZadanie) {

            Log.d("Guzik klasa", "FragmentZadanie");
        //}
    }*/

    //otwieramy kana?? dla powiadomie?? : Andek 8+
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



}