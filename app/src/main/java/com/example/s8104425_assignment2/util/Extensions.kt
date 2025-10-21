package com.example.s8104425_assignment2.util

import android.content.Context
import android.view.View
import android.widget.Toast

//extension to show a view
fun View.show() { this.visibility = View.VISIBLE }

//extension to hid a view
fun View.hide() { this.visibility = View.GONE }

//extension to show a toast
fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
