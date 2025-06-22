package com.example.projectmacadamia

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.projectmacadamia.activiy_login

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonLogout = view.findViewById<Button>(R.id.logout)
        botonLogout.setOnClickListener {
            // Limpiar las SharedPreferences
            val prefs = requireActivity().getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
            prefs.edit().clear().apply()

            // Redirigir a la actividad elegir y limpiar el back stack
            val intent = Intent(requireContext(), elegir::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            requireActivity().finish()
        }

        val prefs = requireActivity().getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)

        val username = prefs.getString("username", "")
        val name = prefs.getString("name", "")
        val lastnames = prefs.getString("lastnames", "")
        val email = prefs.getString("email", "")
        val phone = prefs.getString("phone", "")
        val address = prefs.getString("address", "")

        val userField = view.findViewById<TextView>(R.id.user)
        val nameField = view.findViewById<TextView>(R.id.nombre)
        val lastnamesField = view.findViewById<TextView>(R.id.apellido)
        val emailField = view.findViewById<TextView>(R.id.email)
        val phoneField = view.findViewById<TextView>(R.id.phone)
        val addressField = view.findViewById<TextView>(R.id.address)

        userField.setText(username)
        nameField.setText(name)
        lastnamesField.setText(lastnames)
        emailField.setText(email)
        phoneField.setText(phone)
        addressField.setText(address)

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}