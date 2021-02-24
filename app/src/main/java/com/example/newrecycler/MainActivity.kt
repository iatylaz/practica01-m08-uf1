package com.example.newrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnMarcaItemClickListner{
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var carlist: ArrayList<Marcas>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carlist = ArrayList()




        carRecycler.layoutManager = LinearLayoutManager(this)
        carRecycler.addItemDecoration(DividerItemDecoration(this,1))
        carRecycler.adapter = CarAdapter(this, carlist,this)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val retrofit = Retrofit.Builder().baseUrl("https://run.mocky.io/").addConverterFactory(
            GsonConverterFactory.create()).build()

        val marcasApi = retrofit.create(MarcasApi::class.java)
        val call = marcasApi.marcas

        call.enqueue(object : Callback<List<Marcas>> {
            override fun onResponse(call: Call<List<Marcas>>, response: Response<List<Marcas>>) {
                if (response.code() != 200) {
                    //mostrar erros
                }
                val marcas = response.body()!!
                for (marca in marcas) {
                    (carlist).add(marca)
                }
                PutDataIntoRecyclerView(carlist)

            }
            override fun onFailure(call: Call<List<Marcas>>, t: Throwable) {}
        })
    }


    private fun PutDataIntoRecyclerView(item: List<Marcas>){
        carRecycler.adapter = CarAdapter(this, carlist,this)
        refreshRecycler()

    }
    private fun refreshRecycler(){
        refreshLayout.setOnRefreshListener {
            Toast.makeText(this, "Se ha refrescado la BBDD", Toast.LENGTH_SHORT).show()
            refreshLayout.isRefreshing = false;
            Thread.sleep(2000)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){

            return true
        }





        return super.onOptionsItemSelected(item)

    }






    override fun onItemClick(item: Marcas, position: Int) {
 Toast.makeText(this, item.descripcio , Toast.LENGTH_SHORT).show()
         val intent = Intent(this, MarcaDetailsActivity::class.java)

        intent.putExtra("Marcas",item)




        //  intent.putExtra("CARNAME", item.empresa_organitzadora)
        //intent.putExtra("CARDESC", item.descripcio)


        //intent.putExtra("CARLOGO", item.logo.toString())







        startActivity(intent)


    }


}
