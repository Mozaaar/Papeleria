package moviles2023.papeleria;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import moviles2023.papeleria.data.Producto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder>{

    private Cursor cursorListaProductos;
    private OnItemClickListener listenerClick;

    public  ProductoAdapter(OnItemClickListener listenerClick) {
        this.listenerClick = listenerClick;
    }

    interface OnItemClickListener{
        public void onClick(ViewHolder view, Producto productoactualizado);
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.list_product_item,parent,false );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        if (cursorListaProductos != null && cursorListaProductos.moveToPosition(position)) {
            Producto ProductoSeleccionado = new Producto(cursorListaProductos);
            holder.codigoP.setText(String.valueOf(ProductoSeleccionado.getCodigo()));
            holder.nombrep.setText(ProductoSeleccionado.getNombreProducto());
            holder.stock.setText(String.valueOf(ProductoSeleccionado.getStock()));
            holder.salida.setText(String.valueOf(ProductoSeleccionado.getSalidas()));
            holder.valor.setText(String.valueOf(ProductoSeleccionado.getValor()));
        }
    }


    @Override
    public int getItemCount() {
        if (cursorListaProductos != null)
            return cursorListaProductos.getCount();
        return 0;
    }

    public void swapCursor(Cursor nuevoCursor){
        if(nuevoCursor!=null){
            cursorListaProductos = nuevoCursor;
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView codigoP;
        TextView nombrep;
        TextView stock;
        TextView salida;
        TextView valor;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            codigoP = (TextView) itemView.findViewById( R.id.txt_codigo );
            nombrep = (TextView) itemView.findViewById( R.id.txt_producto );
            stock = (TextView) itemView.findViewById( R.id.txt_stock );
            salida = (TextView) itemView.findViewById( R.id.txt_salidas );
            valor = (TextView) itemView.findViewById( R.id.txt_valor );
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View view) {
            Producto productoClickeado = obtenerProducto( getAdapterPosition());
            int  nuevoStock = productoClickeado.getStock() - 1;
            productoClickeado.setStock( nuevoStock);
            listenerClick.onClick( this,productoClickeado );

            int nuevaSalida = productoClickeado.getSalidas() + 1;
            productoClickeado.setSalidas( nuevaSalida );
            listenerClick.onClick( this,productoClickeado );
        }
        }


    private Producto obtenerProducto(int posicion){
        if (cursorListaProductos!=null){
            cursorListaProductos.moveToPosition( posicion );
            Producto nuevoProducto = new Producto( cursorListaProductos );
            return nuevoProducto;
        }
        return null;
    }
}
