/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //Binding drawerLAyout
        drawerLayout = binding.drawerLayout

        /**Set up button (<-) in action bar*/
        /**Set navdrawer in action bar*/
        //Find NavController, if use KTX so:
        val navController = this.findNavController(R.id.myNavHostFragment)
        //Link navController to ActionBar
        //Include drawerLayout al action bar
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        //Listener del navdrawer que se ejecuta cada vez que se cambia de vista
        navController.addOnDestinationChangedListener { nc:NavController, nd:NavDestination, args: Bundle? ->

            //Bloquear el navdrawer lateral en todas las vistas diferentes a la principal
            if(nd.id == nc.graph.startDestination){
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }else{
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        //Set navView to navUI
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)

        /**Reemplazar up button(<-) por el navdrawer button en la pantalla principal*/
        //return navController.navigateUp()
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}
