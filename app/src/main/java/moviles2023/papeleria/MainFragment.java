package moviles2023.papeleria;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import moviles2023.papeleria.data.InventarioDBHelper;
import moviles2023.papeleria.data.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    Switch ocultar;
    boolean isVisible = false;
    EditText passwordEditText;
    Switch showPasswordSwitch;
    Button ingresar;
// Base de datos
    private EditText nomUsuario;
    private EditText password;
    private Button loginBoton;
    private Button registrarBoton;
    private InventarioDBHelper baseDatos;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        passwordEditText = view.findViewById(R.id.input_contrasena);
        showPasswordSwitch = view.findViewById(R.id.sw_Mostrar);
            showPasswordSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isVisible = isChecked;

                // Aplicamos el cambio al EditText
                passwordEditText.setTransformationMethod(isVisible ?
                        null : PasswordTransformationMethod.getInstance());
            }
        });

        return view;


    }

    // En el método onClick() del botón, cambiamos el estado del switch
    public void onShowPasswordButtonClick(View view) {
        isVisible = !isVisible;

        // Aplicamos el cambio al EditText
        passwordEditText.setTransformationMethod(isVisible ?
                null : PasswordTransformationMethod.getInstance());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ingresar = (Button)view.findViewById(R.id.boton_iniciar_sesion);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.opcionesFragment);

            }
        });

        //Base de datos
        nomUsuario = (EditText) getView().findViewById(R.id.input_usuario);
        password = (EditText) getView().findViewById(R.id.input_contrasena);
        loginBoton = (Button) getView().findViewById(R.id.boton_iniciar_sesion);
        registrarBoton = (Button) getView().findViewById(R.id.btn_registrar);

        baseDatos = new InventarioDBHelper( getContext());
        loginBoton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor usuarioConsultado = baseDatos.getUsuarioByIdPassword( nomUsuario.getText().toString(),
                        password.getText().toString());
                if(usuarioConsultado.moveToFirst()){
                    Navigation.findNavController(view).navigate(R.id.usuariosFragment);
                }else{
                    Toast.makeText( getActivity(),"Datos incorrectos",Toast.LENGTH_LONG ).show();
                }
            }
        } );

        registrarBoton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idValue = Integer.parseInt(nomUsuario.getText().toString());
                int passwordValue = Integer.parseInt( password.getText().toString() );
                Usuario usuarioNuevo = new Usuario(idValue,passwordValue,"Pepe");
                baseDatos.saveUser( usuarioNuevo );
            }
        } );
    }


    }





