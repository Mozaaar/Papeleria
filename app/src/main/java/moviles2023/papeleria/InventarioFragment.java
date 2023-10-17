package moviles2023.papeleria;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import moviles2023.papeleria.data.InventarioDBHelper;
import moviles2023.papeleria.data.Producto;
import moviles2023.papeleria.data.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InventarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventarioFragment extends Fragment implements ProductoAdapter.OnItemClickListener{

    private RecyclerView listaProductos;
    private InventarioDBHelper bdInventario;
    private LinearLayoutManager linearLayoutManager;
    private ProductoAdapter adaptadorProducto;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InventarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InventarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InventarioFragment newInstance(String param1, String param2) {
        InventarioFragment fragment = new InventarioFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventario, container, false);
    }
    ImageButton homee;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        homee = (ImageButton)view.findViewById(R.id.imagebtn_home);
        homee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.opcionesFragment);

            }
        });
        listaProductos =(RecyclerView)getView().findViewById(R.id.listaProductos);
        bdInventario = new InventarioDBHelper( getContext() );

        listaProductos.setHasFixedSize( true );
        linearLayoutManager = new LinearLayoutManager( getContext() );
        listaProductos.setLayoutManager( linearLayoutManager );
        adaptadorProducto = new ProductoAdapter( this );
        listaProductos.setAdapter( adaptadorProducto );
        loadProducto();

    }
    @Override
    public void onClick(ProductoAdapter.ViewHolder view, Producto productoActualizado) {
        bdInventario.updateProducto(productoActualizado,String.valueOf(productoActualizado.getCodigo()));
        loadProducto();
    }

    private void loadProducto() {new ProductoLoaderTask().execute( );}



    private class ProductoLoaderTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return bdInventario.getAllProductos();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                adaptadorProducto.swapCursor( cursor );
            }
        }
    }
}