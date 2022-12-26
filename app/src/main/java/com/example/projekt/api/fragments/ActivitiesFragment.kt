package com.example.projekt.api.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projekt.R
import com.example.projekt.repository.TrackerRepository
import com.example.projekt.viewmodel.ActivitiesViewModel
import com.example.projekt.viewmodel.ActivitiesViewModelFactory

class ActivitiesFragment: Fragment() {
    private lateinit var activitiesViewModel: ActivitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ActivitiesViewModelFactory(TrackerRepository())
        activitiesViewModel = ViewModelProvider(this, factory).get(ActivitiesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activities_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}