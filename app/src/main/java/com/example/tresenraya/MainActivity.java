package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
/*El juego funciona de la siguiente manera,
* Empiezas una partida, existe un marcado que se reinciar cada vez que se pulse al ToggleButton
* Existira un boton reinicar que no reinicar el marcador, pero si que reinica el tablero
* Si se pulsa el ToggleButton se cambiara de oponente y sera aleatorio quien pone primero
* En caso de ser la maquina quien empieza tambien se pondra aleatoriamente el lugar donde pone
* Si se esta jugando contra la maquina y se pulsa reiniciar, el tablero se reiniciara,
* el marcador seguira igual y se volvera a elegir de manera aleatoria quien empieza a jugar*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //Sirve para añadir un sonido al boton
    MediaPlayer mp;
    MediaPlayer mp1;
    MediaPlayer mp2;
    MediaPlayer mp3;


    //private ImageView vacio;

    //Sirve para saber a quien le toca mover
    //De esta manera cada vez empieza un jugador
    private int cont = 0;

    //Cuenta victorias
    private int victoryX = 0;
    private int victoryO = 0;

    //Clase para llamar a los metodos para jugar
    LogicaTresEnRaya tablero1 = new LogicaTresEnRaya();
    //Boton reiniciar
    private Button reiniciar;
    private ToggleButton conmutador;

    //Texto para el contador de victorias
    private TextView x;
    private TextView o;

    //Sirve para saber donde coloca la maquina en caso de que empiece ella.
    private int numAle;

    //Sirve para selecionar si quieres jugar contra otro jugador o contra la maquina
    private boolean oponente = true;

    //Sirve para saber si empieza la maquina o empieza el jugador
    private int turnomaquina=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sirve para añadir sonido al boton
        mp = MediaPlayer.create(this,R.raw.click);
        mp1 = MediaPlayer.create(this,R.raw.ocupada);
        mp2 = MediaPlayer.create(this,R.raw.victoria);
        mp3 = MediaPlayer.create(this,R.raw.cancionfondo);
        mp3.start();
        x = findViewById(R.id.X);
        o = findViewById(R.id.O);
        dibujatablero();

        //Boton reiniciar
        reiniciar = findViewById(R.id.reiniciar);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Quien empieza
                turnomaquina = (int) Math.round(Math.ceil(2 * Math.random()));
                //Donde coloca la maquina
                numAle = (int) Math.round(Math.ceil(9 * Math.random()));

                // En el array se pondran las posiciones a cero
                tablero1.Iniciar();
                //Sirve  para limpiar el tablero de la parte visual, es decir la pantalla que se ve en el movil
                limpiatabla(dibujatablero());
                //Crea un tablero completamente limpio ya que anteriormente se han reiniciado todas las imageenes a casillas vacias
                dibujatablero();
                //Si se pulsa reiniciar al contador de victorias no se reiniciara
                x.setText(String.valueOf(victoryX));
                o.setText(String.valueOf(victoryO));
            }
        });
        //ToggleButton para elegir si se quiere jugar contra el jugador o contra la maquina
        conmutador = findViewById(R.id.conmutador);
        conmutador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conmutador.isChecked()) {
                    //Este booleano se tendra en cuenta en el evento onclick al pulsar la pantalla
                    //Para saber si se mueve ficha automaticamente o no.
                    oponente = false;
                    //El contador de dictorias se reiniciara cada vez que se modifique el oponenete
                    victoryO = 0;
                    victoryX = 0;
                    x.setText(String.valueOf(victoryX));
                    o.setText(String.valueOf(victoryO));

                    turnomaquina = (int) Math.round(Math.ceil(2 * Math.random()));
                    numAle = (int) Math.round(Math.ceil(9 * Math.random()));
                    //Limpia array, limpia el tablero grafico, lo vuelve a pintar
                    tablero1.Iniciar();
                    limpiatabla(dibujatablero());
                    dibujatablero();


                } else {
                    oponente = true;
                    victoryO = 0;
                    victoryX = 0;
                    x.setText(String.valueOf(victoryX));
                    o.setText(String.valueOf(victoryO));



                    tablero1.Iniciar();
                    limpiatabla(dibujatablero());
                    dibujatablero();
                    oponente = true;
                }
            }
        });

    }

    //Recibe la tabla para limpiar su contenido y poder reiniciar el juego
    public void limpiatabla(TableLayout tablalimpia) {
        //Es metodo quita el contenido de la tabla, para luego con otro metodo volver a pintarlos.
        tablalimpia.removeAllViews();
    }

    //Crea un tablero en tiempo de ejecucion y ademas devuelve la tabla que quiero limpiar para reiniciar el juego
    public TableLayout dibujatablero() {
        TableLayout tabla = findViewById(R.id.tabla);
        TableRow fila1 = new TableRow(this);
        fila1.setLayoutParams(getLayoutParams());
        //Añade un TextView con las propiedades que se le pasan por parametro a cada sitio en la fila
        //Una vez añadidos todos se insertan en la  tabla las 3 filas, ademas se le asocia un onclickListener a cada TextView

        fila1.addView(getTextView(1, Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorPrimary)));
        fila1.addView(getTextView(2, Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorPrimary)));
        fila1.addView(getTextView(3, Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorPrimary)));

        TableRow fila2 = new TableRow(this);
        fila2.addView(getTextView(4, Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorPrimary)));
        fila2.addView(getTextView(5, Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorPrimary)));
        fila2.addView(getTextView(6, Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorPrimary)));

        TableRow fila3 = new TableRow(this);
        fila3.addView(getTextView(7, Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorPrimary)));
        fila3.addView(getTextView(8, Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorPrimary)));
        fila3.addView(getTextView(9, Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorPrimary)));

        tabla.addView(fila1, getTblLayoutParams());
        tabla.addView(fila2, getTblLayoutParams());
        tabla.addView(fila3, getTblLayoutParams());

        return tabla;
    }


    @SuppressLint("ResourceType")
    //Sirve para generar textview con los parametros que nosotros queramos y poder agregarlos a la tabla.
    private TextView getTextView(int id, int color, int typeface, int bgColor) {
        //Se crea un objeto TextView
        TextView tv = new TextView(this);
        //Genera los textview necesarios para jugar jugador contra jugador
        if (oponente == true) {
            tv.setId(id);
            //Pinta en el TextView las imagen
            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.vacio1, 0, 0, 0);
            //Color de los margenes
            tv.setTextColor(color);
            //fijamos el relleno (espacio dentro del View)
            tv.setPadding(10, 10, 10, 10);
            //fijamos el tipo de la letra y el estilo
            tv.setBackgroundColor(bgColor);
            //suministra parámetros al padre de la View
            tv.setLayoutParams(getLayoutParams());
            //Asocia listener
            tv.setOnClickListener(this);
            //Entra si se quiere jugar jugador vs maquina
        } else {
            //Entra en caso de que mueva la maquina primero
            //Sirve para dar aleatoriedad a cuando mueve la maquina primero
            if(id==numAle&&turnomaquina==2){
                tv.setId(id);
                tablero1.MueveJugador2(id);
                //Pinta en el TextView las imagen
                tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circulo1, 0, 0, 0);
                tv.setTextColor(color);
                //fijamos el relleno (espacio dentro del View)
                tv.setPadding(10, 10, 10, 10);
                //fijamos el tipo de la letra y el estilo
                tv.setBackgroundColor(bgColor);
                //suministra parámetros al padre de la View
                tv.setLayoutParams(getLayoutParams());
                //Asocia listener
                tv.setOnClickListener(this);
                //Entra si mueve el jugador primero
            }else{
                tv.setId(id);
                //Pinta en el TextView las imagen
                tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.vacio1, 0, 0, 0);
                tv.setTextColor(color);
                //fijamos el relleno (espacio dentro del View)
                tv.setPadding(10, 10, 10, 10);
                //fijamos el tipo de la letra y el estilo
                tv.setBackgroundColor(bgColor);
                //suministra parámetros al padre de la View
                tv.setLayoutParams(getLayoutParams());
                //Asocia listener
                tv.setOnClickListener(this);
            }

        }


        return tv;
    }

    private TableRow.LayoutParams getLayoutParams() {
        //ancho (tan grande como el padre - rellleno) y alto (tan grande como su contenido interno + relleno)
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(60, 5, 6, 60);
        return params;
    }

    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }

    @Override
    //Onclick al que se llamara cada vez que pulsamos en algunos de los TextView
    public void onClick(View v) {

        TextView tv = findViewById(v.getId());
        //Jugardor vs Jugador
        if (oponente == true) {
            //Desactiva el Onclick del TextView si gana alguien o no quedan movimientos
            if (tablero1.GanaJugador1() || tablero1.GanaJugador2() || tablero1.QuedanMovimientos() == false) {
                mp1.start();
                tv.setEnabled(false);
            } else {
                //Para colocar primero 1 jugador luego el otro
                if (cont % 2 == 0) {
                    if (tablero1.MovimientoValido(v.getId()) == true) {
                        tablero1.MueveJugador1(v.getId());
                        mp.start();
                        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circulo1, 0, 0, 0);
                    } else {
                        Toast.makeText(this, "Movimiento no valido", Toast.LENGTH_SHORT).show();
                        mp1.start();
                        cont++;
                    }
                    if (tablero1.QuedanMovimientos() == false&&tablero1.GanaJugador1() == false && tablero1.GanaJugador2() == false) {
                        Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
                    }
                    if (tablero1.GanaJugador1()) {
                        Toast.makeText(this, "Gana jugador O ", Toast.LENGTH_SHORT).show();
                        mp2.start();
                        victoryO++;
                    }
                    cont++;
                } else {
                    if (tablero1.MovimientoValido(v.getId()) == true) {
                        tablero1.MueveJugador2(v.getId());
                        mp.start();
                        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cruz1, 0, 0, 0);
                    } else {
                        Toast.makeText(this, "Movimiento no valido", Toast.LENGTH_SHORT).show();
                        mp1.start();
                        cont++;
                    }
                    if (tablero1.QuedanMovimientos() == false && tablero1.GanaJugador1() == false && tablero1.GanaJugador2() == false) {
                        Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
                    }
                    if (tablero1.GanaJugador2()) {
                        Toast.makeText(this, "Gana jugador X ", Toast.LENGTH_SHORT).show();
                        mp2.start();
                        victoryX++;
                    }
                    cont++;
                }

            }
            //Entra Jugador vs Maquina
        } else if (oponente == false) {
            if (tablero1.GanaJugador1() || tablero1.GanaJugador2() || tablero1.QuedanMovimientos() == false) {
                mp1.start();
                tv.setEnabled(false);
            } else {
                if (tablero1.MovimientoValido(v.getId()) == true) {
                    tablero1.MueveJugador1(v.getId());
                    mp.start();
                    tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cruz1, 0, 0, 0);
                    if (tablero1.GanaJugador1() == false && tablero1.QuedanMovimientos() == true) {
                        int num = tablero1.MueveOrdenador2();
                        TextView tv1 = findViewById(num);
                        tv1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circulo1, 0, 0, 0);
                        if (tablero1.GanaJugador2() == true) {
                            Toast.makeText(this, "Gana jugador O ", Toast.LENGTH_SHORT).show();
                            victoryO++;
                        }
                        if (tablero1.GanaJugador2() == false && tablero1.GanaJugador1() == false && tablero1.QuedanMovimientos() == false) {
                            Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
                        }

                    } else if (tablero1.GanaJugador1() == false && tablero1.GanaJugador2() == false&&tablero1.QuedanMovimientos() == false ) {
                        Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Movimiento no valido", Toast.LENGTH_SHORT).show();
                    mp1.start();
                    cont++;
                }
                if (tablero1.QuedanMovimientos() == false && tablero1.GanaJugador1() == false && tablero1.GanaJugador2() == false) {
                    Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
                }
                if (tablero1.GanaJugador1()) {
                    Toast.makeText(this, "Gana jugador X ", Toast.LENGTH_SHORT).show();
                    mp2.start();
                    victoryX++;
                }
            }


        }
        /*Esto sirve para impedir que una vez se ocupe todo el tablero o gane algun jugador se puedan serguir haciendo movimientos*/

        x.setText(String.valueOf(victoryX));
        o.setText(String.valueOf(victoryO));

    }

}