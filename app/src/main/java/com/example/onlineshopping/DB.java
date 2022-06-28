package com.example.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {
    private static String databaseName="ShoppingDatabase";
    SQLiteDatabase ShoppingDatabase;
    public DB(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table customer(CustID integer primary key autoincrement" +
                ",username text not null,password text not null," +
                "gender text not null,Birthdate text not null,job text not null)");
        sqLiteDatabase.execSQL("create table Categories(CatID integer primary key autoincrement,CatName text not null)");

        sqLiteDatabase.execSQL("create table product (id integer primary key autoincrement, name text not null," +
                "price real not null , quantity integer not null , cate_id integer not null ," +
                "foreign key (cate_id)references Categories (CatID))");

        sqLiteDatabase.execSQL("create table Orders(OrdID integer primary key autoincrement" +
                ",OrdDate Date not null,Cust_ID  integer not null," +
                "Address text not null,Foreign KEY (Cust_ID) References customer (CustID))");

        sqLiteDatabase.execSQL("create table Order_details(Ord_ID integer not null" +
                ",pro_ID integer,Quantity integer not null,primary key(Ord_ID,pro_ID)," +
                "Foreign KEY (Ord_ID) References Orders (OrdID)," +
                "Foreign KEY (pro_ID) References Products (proID))");

        sqLiteDatabase.execSQL("insert into Categories(CatName) values('cars')");
        sqLiteDatabase.execSQL("insert into Categories(CatName) values('Dress')");
        sqLiteDatabase.execSQL("insert into Categories(CatName) values('Mobil')");
        sqLiteDatabase.execSQL("insert into Categories(CatName) values('Bags')");


        ContentValues val = new ContentValues();
        val.put("name","BMW");val.put("price",3000000);val.put("quantity",3);val.put("cate_id",1);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","Nissan");val.put("price",4000000);val.put("quantity",5);val.put("cate_id",1);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","Hunda");val.put("price",5000000);val.put("quantity",6);val.put("cate_id",1);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","Mini cooper");val.put("price",600000);val.put("quantity",6);val.put("cate_id",1);
        sqLiteDatabase.insert("Product",null,val);


        val.put("name","long Dress");val.put("price",100);val.put("quantity",10);val.put("cate_id",2);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","maxi dress");val.put("price",400);val.put("quantity",14);val.put("cate_id",2);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","short Dress");val.put("price",500);val.put("quantity",20);val.put("cate_id",2);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","midi dress");val.put("price",600);val.put("quantity",15);val.put("cate_id",2);
        sqLiteDatabase.insert("Product",null,val);


        val.put("name","iphone8");val.put("price",7000);val.put("quantity",10);val.put("cate_id",3);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","iphone11 pro max");val.put("price",12000);val.put("quantity",14);val.put("cate_id",3);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","Realme 5");val.put("price",2500);val.put("quantity",20);val.put("cate_id",3);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","oppo11");val.put("price",6000);val.put("quantity",15);val.put("cate_id",3);
        sqLiteDatabase.insert("Product",null,val);



        val.put("name","cross bag");val.put("price",100);val.put("quantity",10);val.put("cate_id",4);
        sqLiteDatabase.insert("Product",null,val);

        val.put("name","ordinary bag");val.put("price",400);val.put("quantity",14);val.put("cate_id",4);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","Hand bag");val.put("price",500);val.put("quantity",20);val.put("cate_id",4);
        sqLiteDatabase.insert("Product",null,val);
        val.put("name","Back bag");val.put("price",600);val.put("quantity",15);val.put("cate_id",4);
        sqLiteDatabase.insert("Product",null,val);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS customer");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Produsts");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Orders");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Order_details");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Categories");
        onCreate(sqLiteDatabase);
    }
    public void new_customer(String username,String password,String gender,String Birthdate,String job ){
        ContentValues rows=new ContentValues();
        rows.put("username",username);
        rows.put("password",password);
        rows.put("gender",gender);
        rows.put("Birthdate",Birthdate);
        rows.put("job",job);
        ShoppingDatabase=getWritableDatabase();
        ShoppingDatabase.insert("customer",null,rows);
        ShoppingDatabase.close();
    }
    public Boolean checkUserName(String name){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("select * FROM customer where username=?",new String[]{name});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkUserName_Pass(String name,String pass){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("select * FROM customer where username=? and password=?",new String[]{name,pass});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }


    public class_customer getcustomer(String name) {
        ShoppingDatabase = getReadableDatabase();
        class_customer customer=new class_customer();

        Cursor cursor=ShoppingDatabase.rawQuery("select * FROM customer where username=?",new String[]{name});
        if (cursor != null) {
            cursor.moveToFirst();
            customer = new class_customer(cursor.getInt(0),cursor.getInt(3),cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(5));
        }
        ShoppingDatabase.close();
        return customer;
    }


    public Boolean updatepass(String name,String pass){
        SQLiteDatabase mydb=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",pass);
        long res=mydb.update("customer",contentValues,"username=?",new String[]{name});
        if(res==-1)
            return  false;
        else
            return true;
    }
    public ArrayList<products> GetProductWithCat(int catId) {
        ShoppingDatabase = getReadableDatabase();
        String[] rowDetails = {"name", "price", "quantity", "cate_id", "id"};

        Cursor cursor = ShoppingDatabase.query("product", rowDetails, "cate_id=" + catId + "", null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ShoppingDatabase.close();
        ArrayList<products> pro = new ArrayList<products>();
        while (!cursor.isAfterLast()) {
            pro.add(new products(cursor.getInt(2),cursor.getInt(3),cursor.getString(0),cursor.getDouble(1),cursor.getInt(4)));
            cursor.moveToNext();
        }
        return pro;
    }

    public void insertProduct(products product){
        ShoppingDatabase=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",product.getProName());

        values.put("price",product.getPrice());
        values.put("quantity",product.getPro_quantity());
        values.put("cate_id",product.getCatId());

        ShoppingDatabase.insert("Produsts",null,values);
        ShoppingDatabase.close();
    }
    public void insertCategory(CategoryModel cate){
        ShoppingDatabase=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",cate.getCat_name());
        ShoppingDatabase.insert("Categories",null,values);
        ShoppingDatabase.close();
    }
    public Cursor getCategory(){
        ShoppingDatabase=getReadableDatabase();
        String []fields={"CatID","CatName"};
        Cursor cursor= ShoppingDatabase.query("Categories",fields,null,null,null,null,null);

        if (cursor.getCount()>0)
            cursor.moveToFirst();

        ShoppingDatabase.close();

        return cursor;
    }
    public String getCatId(String name ){
        ShoppingDatabase=getReadableDatabase();
        String[]args={name};
        Cursor cursor=ShoppingDatabase.rawQuery("select CatID from Categories where CatName =?",args);

        if (cursor.getCount()>0) {

            cursor.moveToFirst();
            ShoppingDatabase.close();
            return cursor.getString(0);
        }
        ShoppingDatabase.close();

        cursor.close();
        return null;

    }


    public products getProductWithID(int proID) {
        ShoppingDatabase = getReadableDatabase();
        products product=new products();
        String[] rowDetails = {"name", "price", "quantity", "cate_id", "id","Product_Img"};
        Cursor cursor = ShoppingDatabase.query("product", rowDetails, "id=" + proID + "", null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            product = new products(cursor.getInt(2),cursor.getInt(3),cursor.getString(0),cursor.getDouble(1),cursor.getInt(4));
        }
        ShoppingDatabase.close();
        return product;
    }
    public void SaveOrder(String Address,String currentTime,int custemorID) {
        ShoppingDatabase = getReadableDatabase();
        String Date = currentTime;
        ContentValues orderDetailsRow = new ContentValues();
        orderDetailsRow.put("Cust_ID", custemorID);
        orderDetailsRow.put("OrdDate", Date);
        orderDetailsRow.put("Address", Address);
        ShoppingDatabase.insert("Orders", null, orderDetailsRow);
        String[] arg = {String.valueOf(custemorID), Date, Address};
        Cursor c = ShoppingDatabase.rawQuery("select OrdID from Orders where  Cust_ID=? and  OrdDate=? and Address=?", arg);
        if (c != null)
            c.moveToFirst();
        int OrderID = c.getInt(0);
        for (class_orderDetails pro : class_dbFn.cartItems) {
            orderDetailsRow = new ContentValues();
            orderDetailsRow.put("Ord_ID", OrderID);
            orderDetailsRow.put("pro_ID", pro.ProID);
            orderDetailsRow.put("Quantity", pro.Quantity);
            ShoppingDatabase.insert("Order_details", null, orderDetailsRow);
        }
        ShoppingDatabase.close();
    }

}