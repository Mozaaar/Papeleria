package moviles2023.papeleria.data;

import android.provider.BaseColumns;

public class ProductosContract {
    public static abstract class ProductoEntry implements BaseColumns{
        public static final String TABLE_NAME = "productos";
        public static final String CODIGO = "codigo";
        public static final String NAMEP = "nombreP";
        public static final String STOCK = "stock";
        public static final String SALIDA = "salidas";
        public static final String VALOR = "valor";
    }
}
