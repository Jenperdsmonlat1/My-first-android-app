package com.example.solver;

import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {

    private TextInputLayout a;
    private TextInputLayout b;
    private TextInputLayout c;
    private WebView webView;
    private SolverFunctions solver;
    private double a_double, b_double, c_double, discriminant, solution1, solution2, solution_double;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button validButton = (Button) this.findViewById(R.id.valider);
        MaterialToolbar topAppBar = (MaterialToolbar) this.findViewById(R.id.topAppBar);
        AppBarLayout appBar = (AppBarLayout) this.findViewById(R.id.appBar);

        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                solver = new SolverFunctions();

                a = (TextInputLayout) findViewById(R.id.entiera);
                b = (TextInputLayout) findViewById(R.id.entierb);
                c = (TextInputLayout) findViewById(R.id.entierc);

                String entierA = a.getEditText().getText().toString().trim();
                String entierB = b.getEditText().getText().toString().trim();
                String entierC = c.getEditText().getText().toString().trim();

                try {
                    a_double = Double.parseDouble(entierA);
                    b_double = Double.parseDouble(entierB);
                    c_double = Double.parseDouble(entierC);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Erreur vous devez saisir des nombres.", Toast.LENGTH_SHORT).show();
                }

                discriminant = solver.calculerDiscriminant(a_double, b_double, c_double);

                if (a_double == 0) {
                    if (b_double == 0) {
                        if (c_double == 0) {
                            AllReelPopUp();
                        } else {
                            NoSolutions();
                        }
                    } else {
                        solution_double = solver.calculerSolutionDouble(b_double, c_double);
                        OneSolution(solution_double);
                    }
                } else {
                    if (discriminant >= 0) {
                        solution1 = solver.calculerSolutionUne(a_double, b_double, discriminant);
                        solution2 = solver.calculerSolutionDeux(a_double, b_double, discriminant);
                        afficherDeuxSolutions(solution1, solution2);
                    } else {
                        NoSolutions();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.github:
                openGithub();
                return true;
            case R.id.website:
                Toast.makeText(this, "Impossible d'ouvrir le navigateur. Lol.", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.historique:
                Toast.makeText(this, "Impossible d'accéder à l'historique lol.", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.quitter:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void AllReelPopUp()
    {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Solutions")
                .setMessage("Tous les réels sont solutions.")
                .setPositiveButton("Fermer", null)
                .show();
    }

    public void NoSolutions()
    {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Solutions")
                .setMessage("Il n'y a pas de solutions pour cette équation.")
                .setPositiveButton("Fermer", null)
                .show();
    }

    public void OneSolution(double solution)
    {
        String sol = String.valueOf(solution);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Solutions")
                .setMessage("La solution est: " + sol)
                .setPositiveButton("Fermer", null)
                .show();
    }

    public void afficherDeuxSolutions(double solution1, double solution2)
    {
        String sol1 = String.valueOf(solution1);
        String sol2 = String.valueOf(solution2);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Solutions")
                .setMessage("Les solutions sont: \nSolution 1: " + sol1 + "\nSolution 2: " + sol2)
                .setPositiveButton("Fermer", null)
                .show();
    }

    public void openGithub() {
        webView.setWebViewClient(new WebViewClient() {
            public boolean load_url(WebView view) {
                view.loadUrl("https://github.com/jesuisroot123/");
                return true;
            }
        });
    }
}
