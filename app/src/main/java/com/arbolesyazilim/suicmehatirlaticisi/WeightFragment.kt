package com.arbolesyazilim.suicmehatirlaticisi
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arbolesyazilim.suicmehatirlaticisi.databinding.FragmentWeightBinding

class WeightFragment : Fragment() {
    private var _binding: FragmentWeightBinding? = null
    private val binding get() = _binding!!

    private val userDataManager by lazy {
        UserDataManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextW.setOnClickListener {
            val kgValue = binding.kgEdit.text.toString()

            if (kgValue.isNotEmpty()) {
                val result = kgValue.toFloat() * 35
                userDataManager.saveWeight(result)

                val action = WeightFragmentDirections.actionWeightFragmentToSplashFragment(result.toInt())
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Lütfen kilonuzu giriniz..", Toast.LENGTH_LONG).show()
            }
        }
        binding.backW.setOnClickListener {
            val action = WeightFragmentDirections.actionWeightFragmentToGenderFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}