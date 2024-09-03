package com.example.todoapplication.View.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplication.View.MainActivity
import com.example.todoapplication.databinding.FragmentDashboardBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textDashboard
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var namex = sharedPreferences.getString("key_name", "default_value")
        Log.d("test", namex.toString())
        profileViewModel.name.observe(viewLifecycleOwner, Observer {it ->
            textView.text = "Welcome back $it"
        })
        profileViewModel.putName(namex!!)

        val btnLogout : Button = binding.btnLogout
        btnLogout.setOnClickListener{
            sharedPreferences.edit {
                putBoolean("key_is_logged_in", false)
                startActivity(Intent(requireContext(),MainActivity::class.java))
                activity?.finish()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}