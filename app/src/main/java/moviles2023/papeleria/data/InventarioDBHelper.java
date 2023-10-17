package moviles2023.papeleria.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import moviles2023.papeleria.data.UsuarioContract.UsuarioEntry;
import moviles2023.papeleria.data.ProductosContract.ProductoEntry;


public class InventarioDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Papeleria3.db";

    public InventarioDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + UsuarioEntry.TABLE_NAME + " ("+
                    UsuarioEntry.ID + " INTEGER PRIMARY KEY," +
                    UsuarioEntry.NAME+ " TEXT NOT NULL," +
                    UsuarioEntry.PASSWORD+ " INTEGER NOT NULL)");

            sqLiteDatabase.execSQL("CREATE TABLE " + ProductoEntry.TABLE_NAME + " (" +
                    ProductoEntry.CODIGO + " INTEGER PRIMARY KEY," +
                    ProductoEntry.NAMEP + " TEXT NOT NULL, " +
                    ProductoEntry.STOCK + " INTEGER NOT NULL, " +
                    ProductoEntry.SALIDA + " INTEGER NOT NULL, " +
                    ProductoEntry.VALOR + " INTEGER NOT NULL)");
        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long saveUser(Usuario usuario) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                UsuarioEntry.TABLE_NAME,
                null,
                usuario.toContentValues());
    }
    public long saveProduct(Producto producto){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                ProductoEntry.TABLE_NAME,
                null,
                producto.toContentValues());

    }

    public Cursor getAllUsuarios() {
        return getReadableDatabase()
                .query(
                        UsuarioEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }
    public Cursor getAllProductos(){
        return  getReadableDatabase()
                .query(
                        ProductoEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

    }

    public Cursor getUsuarioById(String usuarioId) {
        Cursor c = getReadableDatabase().query(
                UsuarioEntry.TABLE_NAME,
                null,
                UsuarioEntry.ID + " LIKE ?",
                new String[]{usuarioId},
                null,
                null,
                null);
        return c;
    }

    public Cursor getProductoByCode(String productoCode){
        Cursor cur = getReadableDatabase().query(
                ProductoEntry.TABLE_NAME,
                null,
                ProductoEntry.CODIGO + " LIKE ?",
                new String[]{productoCode},
                null,
                null,
                null);
        return cur;
    }

    public Cursor getUsuarioByIdPasswordNombre(String usuarioId,String usuarioPassword,String usuarioNombre) {
        Cursor c = getReadableDatabase().query(
                UsuarioEntry.TABLE_NAME,
                null,
                UsuarioEntry.ID + " LIKE ? AND "+UsuarioEntry.PASSWORD + " LIKE ? AND " + UsuarioEntry.NAME + " LIKE ?",
                new String[]{usuarioId,usuarioPassword, usuarioNombre},
                null,
                null,
                null);
        return c;
    }

    public int deleteUsuario(String usuarioId) {
        return getWritableDatabase().delete(
                UsuarioEntry.TABLE_NAME,
                UsuarioEntry.ID+ " LIKE ?",
                new String[]{usuarioId});
    }
    public int deleteProducto(String productoCode) {
        return getWritableDatabase().delete(
                ProductoEntry.TABLE_NAME,
                ProductoEntry.CODIGO+ " LIKE ?",
                new String[]{productoCode});
    }


    public int updateUsuario(Usuario usuarioModificar, String usuarioId) {
        return getWritableDatabase().update(
                UsuarioEntry.TABLE_NAME,
                usuarioModificar.toContentValues(),
                UsuarioEntry.ID + " LIKE ?",
                new String[]{usuarioId}
        );
    }
    public int updateProducto(Producto productoModificar, String productoCode) {
        return getWritableDatabase().update(
                ProductoEntry.TABLE_NAME,
                productoModificar.toContentValues(),
                ProductoEntry.CODIGO + " LIKE ?",
                new String[]{productoCode}
        );
    }


}