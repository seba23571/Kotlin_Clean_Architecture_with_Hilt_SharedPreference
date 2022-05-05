package com.supraweb.users.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.supraweb.users.R
import com.supraweb.users.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var toolbarHome: Toolbar? = null
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.idToolbar)


       // setContentView(R.layout.activity_home)

        val navController = Navigation.findNavController(this, R.id.id_fragmentNavigation)
        NavigationUI.setupWithNavController(binding.navView!!, navController)

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayoutSebas)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.id_fragmentNavigation),
            binding.drawerLayoutSebas

        )
    }



}