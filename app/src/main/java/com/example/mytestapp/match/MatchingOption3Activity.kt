package com.example.mytestapp.match

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.mytestapp.R
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.MainActivity
import com.example.mytestapp.databinding.ActivityMatchingOption3Binding

class MatchingOption3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchingOption3Binding // 바인딩 객체 선언

    private lateinit var cleaningSensitivityRadioGroup: RadioGroup
    private lateinit var noiseSensitivityRadioGroup: RadioGroup
    private lateinit var firstSleepTimeRadioGroup: RadioGroup
    private lateinit var secondSleepTimeRadioGroup: RadioGroup
    private lateinit var drinkingFrequencyRadioGroup: RadioGroup

    private var drinkingFrequency: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchingOption3Binding.inflate(layoutInflater) // 바인딩 객체 초기화
        setContentView(binding.root)

        // 라디오 그룹 초기화
        cleaningSensitivityRadioGroup = findViewById(R.id.cleaning_sensitivity_radio_group)
        noiseSensitivityRadioGroup = findViewById(R.id.noise_sensitivity_radio_group)
        firstSleepTimeRadioGroup = findViewById(R.id.first_sleeptime_radio_group)
        secondSleepTimeRadioGroup = findViewById(R.id.second_sleep_time_radio_group)
        drinkingFrequencyRadioGroup = binding.drinkingFrequencyRadioGroup

        // 버튼 초기화
        val btnBack: Button = binding.btnBack
        val btnNext: Button = binding.btnNext
        val btnPrev: Button = binding.btnPrev

        // 버튼 클릭 리스너 설정
        btnBack.setOnClickListener {
            // 메인 화면으로 이동
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnPrev.setOnClickListener {
            // 이전 화면으로 이동 (ProfileOption3Activity)
            val intent = Intent(this, MatchingOption2Activity::class.java)
            startActivity(intent)
            finish()
        }

        btnNext.setOnClickListener {
            // 다음 버튼 클릭 시 선택한 값을 확인하고 처리
            val cleaningSensitivity = getSelectedRadioButtonValue(cleaningSensitivityRadioGroup)
            val noiseSensitivity = getSelectedRadioButtonValue(noiseSensitivityRadioGroup)
            val sleepTime = getSelectedRadioButtonValue(firstSleepTimeRadioGroup, secondSleepTimeRadioGroup)
            val drinkingFrequency = getSelectedRadioButtonValue(drinkingFrequencyRadioGroup)

            if (cleaningSensitivity == "선택되지 않음" ||
                noiseSensitivity == "선택되지 않음" ||
                sleepTime == "선택되지 않음" ||
                drinkingFrequency == "선택되지 않음") {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 선택한 값들을 다음 액티비티로 전달하고 해당 액티비티로 이동
                val intent = Intent(this, MatchingOption4Activity::class.java)
                // 여기에 선택한 값들을 intent에 추가하는 코드 추가해야 함
                startActivity(intent)
                finish()
            }
        }
    }

    // 라디오 그룹에서 선택된 값 가져오기
    private fun getSelectedRadioButtonValue(radioGroup: RadioGroup): String {
        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId != -1) {
            val selectedRadioButton: RadioButton = findViewById(selectedId)
            return selectedRadioButton.text.toString()
        }
        return "선택되지 않음"
    }

    // 두 개의 라디오 그룹에서 선택된 값 가져오기 (첫 번째 그룹이 우선)
    private fun getSelectedRadioButtonValue(firstGroup: RadioGroup, secondGroup: RadioGroup): String {
        val firstGroupValue = getSelectedRadioButtonValue(firstGroup)
        if (firstGroupValue != "선택되지 않음") {
            return firstGroupValue
        }
        return getSelectedRadioButtonValue(secondGroup)
    }
}
