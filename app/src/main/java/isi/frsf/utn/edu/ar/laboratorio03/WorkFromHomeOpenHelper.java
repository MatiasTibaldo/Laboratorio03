package isi.frsf.utn.edu.ar.laboratorio03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class WorkFromHomeOpenHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME_TRABAJO = "trabajo";
    public static final String DATABASE_NAME_CATEGORIA = "categoria";

    public static final String CAMPO_ID_TRABAJO = "_id";
    public static final String CAMPO_DESCRIPCION_TRABAJO = "descripcion";
    public static final String CAMPO_HORASPRESUPUESTADAS_TRABAJO = "horasPresupuestas";
    public static final String CAMPO_IDCATEG_TRABAJO = "idCateg";
    public static final String CAMPO_PRECIOMAXIMOHORA_TRABAJO = "precioMaximoHora";
    public static final String CAMPO_FECHAENTREGA_TRABAJO = "fechaEntrega";
    public static final String CAMPO_MONEDAPAGO_TRABAJO = "monedaPago";
    public static final String CAMPO_REQUIEREINGLES_TRABAJO = "requiereIngles";

    public static final String CAMPO_ID_CATEGORIA = "_id";
    public static final String CAMPO_DESCRIPCION_CATEGORIA = "descripcion";

    private static final String SQL_CREATE_CATEGORIA = "create table " + DATABASE_NAME_CATEGORIA + " (" + CAMPO_ID_CATEGORIA + " integer primary key autoincrement, " + CAMPO_DESCRIPCION_CATEGORIA + " text);";

    private static final String SQL_CREATE_TRABAJO= "create table " + DATABASE_NAME_TRABAJO + " (" + CAMPO_ID_TRABAJO + " integer primary key autoincrement, " + CAMPO_DESCRIPCION_TRABAJO + " text, " + CAMPO_HORASPRESUPUESTADAS_TRABAJO + " integer, " + CAMPO_IDCATEG_TRABAJO + " integer, " + CAMPO_PRECIOMAXIMOHORA_TRABAJO + " real, " + CAMPO_FECHAENTREGA_TRABAJO + " long, " + CAMPO_MONEDAPAGO_TRABAJO + " integer, " + CAMPO_REQUIEREINGLES_TRABAJO + " integer);";

    private static WorkFromHomeOpenHelper _INSTANCE;

    private WorkFromHomeOpenHelper(Context ctx){
        super(ctx,"WORK_FROM_HOME",null,1);
    }

    public static WorkFromHomeOpenHelper getInstance(Context ctx){
        if(_INSTANCE==null) _INSTANCE = new WorkFromHomeOpenHelper(ctx);
        return _INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORIA);
        sqLiteDatabase.execSQL(SQL_CREATE_TRABAJO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ DATABASE_NAME_TRABAJO);
        sqLiteDatabase.execSQL("drop table if exists "+ DATABASE_NAME_CATEGORIA);
        onCreate(sqLiteDatabase);
    }
}
