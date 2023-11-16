package com.arbolesyazilim.suicmehatirlaticisi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.arbolesyazilim.suicmehatirlaticisi.databinding.FragmentFirstBinding
import java.util.*

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    // Saat 12'de veriyi sıfırlamak için kullanılacak SharedPreference anahtarı
    private val midnightResetKey = "MIDNIGHT_RESET_KEY"
    private val userDataManager by lazy {
        UserDataManager(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AlarmReceiver.setAlarm(requireContext())

        // Saat 12'de veriyi sıfırla
        resetDataAtMidnight()

        // Kilo verisi kontrolü
        val weightDataIsCaptured = checkIfWeightDataIsCaptured()
        if (weightDataIsCaptured) {
            val storedUserData = userDataManager.getStoredUserData()
            val action = FirstFragmentDirections.actionFirstFragmentToWaterFragment3(storedUserData.weight.toInt())
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.start.setOnClickListener {
            val action: NavDirections = FirstFragmentDirections.actionFirstFragmentToGenderFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkIfWeightDataIsCaptured(): Boolean {
        val storedUserData = userDataManager.getStoredUserData()
        return storedUserData.weight != 0f
    }

    private fun resetDataAtMidnight() {
        val currentTime = System.currentTimeMillis()
        val midnight = getMidnightTime()

        if (currentTime >= midnight && !hasResetToday()) {
            // Saat gece 12'den sonraysa ve bugün daha önce sıfırlanmamışsa
            // Veriyi sıfırla
            userDataManager.resetWaterAmount()

            // Sıfırlama işlemi tamamlandı, günü kaydet
            requireActivity().getPreferences(AppCompatActivity.MODE_PRIVATE).edit()
                .putBoolean(midnightResetKey, true).apply()
        }
    }

    private fun getMidnightTime(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
    }

    private fun hasResetToday(): Boolean {
        return requireActivity().getPreferences(AppCompatActivity.MODE_PRIVATE)
            .getBoolean(midnightResetKey, false)
    }
}
