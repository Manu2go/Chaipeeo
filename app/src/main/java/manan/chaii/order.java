package manan.chaii;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class order extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
    GridView mygrid;
    SharedPreferences shared, prce;
    View v;
    Button status;
    View w;
    String nme;
    String mney;
    TextView username;
    TextView amount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);/*
        Intent i=getIntent();
        Bundle bundle = i.getExtras();*/

        shared=getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=shared.edit();
        nme = shared.getString("username","");
        mney = shared.getString("wallet","");


        NavigationView NavView = (NavigationView) findViewById(R.id.nav_view1);
        View testview = NavView.inflateHeaderView(R.layout.nav_header_home1);//add a nav_header_home1.xml file as the header of nav_view
        username = (TextView) testview.findViewById(R.id.wname);
        amount = (TextView) testview.findViewById(R.id.umoney);
        Toast.makeText(this, nme + " " + mney, Toast.LENGTH_LONG).show();

       /* SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putString("username", nme);
        edit.putString("wallet", mney);*/

        Resources res = getResources();
        String ss = res.getString(R.string.Rs);

        username.setText(nme + "");
        amount.setText(ss + " " + mney);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        w = findViewById(R.id.app_bar_main);
        w = w.findViewById(R.id.content_main);
        status = (Button) w.findViewById(R.id.statusbar);

        shared = getSharedPreferences("items", Context.MODE_PRIVATE);
        edit = shared.edit();
        edit.putString("Yellow Tea", "0");
        edit.putString("Black Tea", "0");
        edit.putString("Green Tea", "0");
        edit.putString("Coffee", "0");
        edit.apply();
        prce = getSharedPreferences("price", Context.MODE_PRIVATE);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        v = findViewById(R.id.content_main);
        mygrid = (GridView) v.findViewById(R.id.gridView);
        mygrid.setAdapter(new adapter(this));
        mygrid.setOnItemClickListener(this);

    }


    public void submit(View v) {
        Intent i = new Intent(this, buyeraddress.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, mydialog.class);
        ViewHolder holder = (ViewHolder) view.getTag();
        product pr = (product) holder.myproduct.getTag();
        i.putExtra("productnam", pr.imagenam);
        i.putExtra("productimage", pr.imageid);
        startActivity(i);
    }


    class product {
        int imageid;
        String imagenam;
        String price;
        product(int imageid, String imagenam, String price) {
            this.imageid = imageid;
            this.imagenam = imagenam;
            this.price = price;

            SharedPreferences.Editor edt = prce.edit();
            edt.putString(imagenam, price);
            edt.apply();

        }
    }

    class ViewHolder {
        ImageView myproduct;
        TextView nam;
        TextView price;
        ViewHolder(View v) {
            myproduct = (ImageView) v.findViewById(R.id.imageView);
            nam = (TextView) v.findViewById(R.id.itemname);
            price = (TextView) v.findViewById(R.id.price);
        }
    }

    class adapter extends BaseAdapter {

        Context context;
        ArrayList<product> list;
        adapter(Context context) {
            this.context = context;
            list = new ArrayList<product>();
            Resources resources = context.getResources();
            String[] nam = resources.getStringArray(R.array.products);
            int[] id = {R.drawable.yellowtea, R.drawable.greentea, R.drawable.blacktea, R.drawable.coffee};
            String[] price = resources.getStringArray(R.array.price);
            for (int i = 0; i < 4; i++) {
                product temp = new product(id[i], nam[i], price[i]);
                list.add(temp);
            }
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_item, parent, false);
                holder = new ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();

            }

            product temp = list.get(i);
            holder.myproduct.setImageResource(temp.imageid);
            holder.nam.setText(temp.imagenam);
            holder.myproduct.setTag(temp);
            holder.price.setText("Rs. " + temp.price);

            return row;
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i= new Intent(order.this,settings.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;

        if (id == R.id.order_now) {
            i = new Intent(order.this, order.class);
            startActivity(i);
        } else if (id == R.id.wallet) {
            i = new Intent(order.this, wallet.class);
            startActivity(i);


        } else if (id == R.id.logout) {
            finish();
            SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = user.edit();
            edit.putString("login_status", "0");
            edit.commit();
            i = new Intent(order.this, login1.class);
            startActivity(i);

        }
        else if (id == R.id.settings) {
            i = new Intent(order.this, settings.class);
            startActivity(i);

        }else if (id == R.id.about) {
            i = new Intent(order.this, about.class);
            startActivity(i);

        } else if (id == R.id.contactus) {
            i = new Intent(order.this, contactus.class);
            startActivity(i);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
