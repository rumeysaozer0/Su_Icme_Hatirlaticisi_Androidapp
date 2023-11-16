package com.arbolesyazilim.suicmehatirlaticisi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arbolesyazilim.suicmehatirlaticisi.databinding.FragmentGenderBinding


class GenderFragment : Fragment() {
    private var _binding: FragmentGenderBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next.setOnClickListener {
            val action = GenderFragmentDirections.actionGenderFragmentToWeightFragment()
            findNavController().navigate(action)
        }
        binding.back.setOnClickListener {
            val action = GenderFragmentDirections.actionGenderFragmentToFirstFragment()
            findNavController().navigate(action)
        }
        binding.girlCircle.visibility = View.INVISIBLE
        binding.boyCircle.visibility = View.INVISIBLE
        binding.girl.setOnClickListener {
            binding.girlCircle.visibility = View.VISIBLE
            binding.boyCircle.visibility = View.INVISIBLE
        }
        binding.boy.setOnClickListener {
            binding.girlCircle.visibility = View.INVISIBLE
            binding.boyCircle.visibility = View.VISIBLE
        }

    }

}