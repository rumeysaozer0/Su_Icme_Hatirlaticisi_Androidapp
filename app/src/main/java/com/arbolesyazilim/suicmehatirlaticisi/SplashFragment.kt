package com.arbolesyazilim.suicmehatirlaticisi

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arbolesyazilim.suicmehatirlaticisi.databinding.FragmentSplashBinding



class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = SplashFragmentArgs.fromBundle(requireArguments())
        val water = args.water
        binding.drop2.visibility = INVISIBLE
        binding.drop3.visibility = INVISIBLE
        binding.textView7.visibility = INVISIBLE
        binding.button.visibility = INVISIBLE
        Handler().postDelayed({
        binding.drop2.visibility = VISIBLE
        },1000)

        Handler().postDelayed({
            binding.drop3.visibility = VISIBLE
        },1500)
        Handler().postDelayed({
            binding.drop3.visibility = VISIBLE
        },1500)
        Handler().postDelayed({

            binding.textView7.text =  "$water ml"
            binding.textView7.visibility = VISIBLE
        },2000)
        Handler().postDelayed({
            binding.button.visibility = VISIBLE

        },2000)
        binding.button.setOnClickListener {
            val action = SplashFragmentDirections.actionSplashFragmentToWaterFragment3(water)
            findNavController().navigate(action)
        }
    }


}