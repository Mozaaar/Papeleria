package moviles2023.papeleria;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import moviles2023.papeleria.data.InventarioDBHelper;
import moviles2023.papeleria.data.Producto;
import moviles2023.papeleria.data.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntradaDatosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntradaDatosFragment extends Fragment {
    // Base de datos
    private EditText codigo;
    private EditText nomProducto;
    private EditText stock;
    private EditText valor;
    private Button registrarDatos;
    private InventarioDBHelper baseDatos;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EntradaDatosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EntradaDatosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EntradaDatosFragment newInstance(String param1, String param2) {
        EntradaDatosFragment fragment = new EntradaDatosFragment();
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


    ImageButton homee;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        homee = (ImageButton) view.findViewById(R.id.imagebtn_home);
        homee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.opcionesFragment);

            }
        });

        //Base de datos
        codigo = (EditText) getView().findViewById(R.id.input_codigo);
        nomProducto = (EditText) getView().findViewById(R.id.input_Producto);
        stock = (EditText) getView().findViewById(R.id.input_Stock);
        valor = (EditText) getView().findViewById(R.id.input_Valor);
        registrarDatos = (Button) getView().findViewById(R.id.btn_registrardatos);

        baseDatos = new InventarioDBHelper(getContext());

        registrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int codeValue = Integer.parseInt(codigo.getText().toString());
                String productoValue = nomProducto.getText().toString();
                int stockValue = Integer.parseInt(stock.getText().toString());
                int valorValue = Integer.parseInt(valor.getText().toString());

                Producto ProductoNuevo = new Producto(codeValue, productoValue, stockValue, 0, valorValue);
                baseDatos.saveProduct(ProductoNuevo);
            }
        });
    }


    CheckBox escritura, oficina, otros;
    TextView resultados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entrada_datos, container, false);
        escritura = view.findViewById(R.id.cb_escritura);
        oficina = view.findViewById(R.id.cb_oficina);
        otros = view.findViewById(R.id.cb_otros);
        resultados = view.findViewById(R.id.txt_tiposeleccionado);
        escritura.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextViewResult();
            }
        });
        oficina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextViewResult();
            }
        });
        otros.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextViewResult();
            }
        });


        return view;
    }

    private void updateTextViewResult() {
        StringBuilder result = new StringBuilder();

        if (escritura.isChecked()) {
            result.append("Escritura seleccionado\n");
        }

        if (oficina.isChecked()) {
            result.append("Oficina seleccionado\n");
        }
        if (otros.isChecked()) {
            result.append("Otros seleccionado\n");
        }

        // Establece el texto actualizado en el TextView
        resultados.setText(result.toString());
    }

}