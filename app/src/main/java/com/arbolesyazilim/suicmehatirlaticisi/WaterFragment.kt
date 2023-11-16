package com.arbolesyazilim.suicmehatirlaticisi

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.arbolesyazilim.suicmehatirlaticisi.databinding.FragmentWaterBinding

class WaterFragment : Fragment() {
    private var _binding: FragmentWaterBinding? = null
    private val binding get() = _binding!!

    private val userDataManager by lazy {
        UserDataManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = WaterFragmentArgs.fromBundle(requireArguments())
        val water = args.water

        binding.newWater.text = userDataManager.getStoredUserData().currentWater.toString()

        binding.changeGlass.setOnClickListener {
            showPopup()
        }

        binding.constantWater.text = "/$water"
        binding.addWater.setOnClickListener {
            val currentWaterValue = userDataManager.getStoredUserData().currentWater
            binding.textView14.text = "Elhamdülillah"
            when (binding.waterMl.text) {
                "150 ml" -> {
                    val newWaterValue = currentWaterValue + 150
                    userDataManager.saveWaterAmount(newWaterValue)
                    binding.newWater.text = newWaterValue.toString()
                }
                "100 ml" -> {
                    val newWaterValue = currentWaterValue + 100
                    userDataManager.saveWaterAmount(newWaterValue)
                    binding.newWater.text = newWaterValue.toString()
                }
                "200 ml" -> {
                    val newWaterValue = currentWaterValue + 200
                    userDataManager.saveWaterAmount(newWaterValue)
                    binding.newWater.text = newWaterValue.toString()
                }
                "500 ml" -> {
                    val newWaterValue = currentWaterValue + 500
                    userDataManager.saveWaterAmount(newWaterValue)
                    binding.newWater.text = newWaterValue.toString()
                }
                // Diğer durumları ekleyebilirsiniz
                else -> {
                    // Belirtilen durumlar dışında bir durum varsa burada işlem yapabilirsiniz
                }
            }
        }
    }

    private fun showPopup() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.popup_layout, null)

        val imageView100ml = view.findViewById<ImageView>(R.id.yuzml)
        val imageView200ml = view.findViewById<ImageView>(R.id.yuzelliml)
        val imageView500ml = view.findViewById<ImageView>(R.id.besyuzml)


        val alertDialog = builder.setView(view)
            .setPositiveButton("Tamam") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("İptal") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

         imageView100ml.setOnClickListener {
            binding.waterMl.text = "100 ml"

        }

        imageView200ml.setOnClickListener {
            binding.waterMl.text = "200 ml"
        }

        imageView500ml.setOnClickListener {

            binding.waterMl.text = "500 ml"
        }

        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}