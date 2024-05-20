package com.example.mytestapp.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.MainActivity
import com.example.mytestapp.R

class ProfileOption4Activity : AppCompatActivity() {

    private lateinit var cleaningSensitivityRadioGroup: RadioGroup
    private lateinit var noiseSensitivityRadioGroup: RadioGroup
    private lateinit var firstSleepTimeRadioGroup: RadioGroup
    private lateinit var secondSleepTimeRadioGroup: RadioGroup
    private lateinit var firstUptimeRadioGroup: RadioGroup
    private lateinit var secondUptimeRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_option_4)

        // 라디오 그룹 초기화
        cleaningSensitivityRadioGroup = findViewById(R.id.cleaning_sensitivity_radio_group)
        noiseSensitivityRadioGroup = findViewById(R.id.noise_sensitivity_radio_group)
        firstSleepTimeRadioGroup = findViewById(R.id.first_sleeptime_radio_group)
        secondSleepTimeRadioGroup = findViewById(R.id.second_sleep_time_radio_group)
        firstUptimeRadioGroup = findViewById(R.id.first_uptime_radio_group)
        secondUptimeRadioGroup = findViewById(R.id.second_uptime_radio_group)

        // 버튼 초기화
        val btnBack: Button = findViewById(R.id.btn_back)
        val btnFinish: Button = findViewById(R.id.btn_finish)
        val btnPrev: Button = findViewById(R.id.btn_prev)

        // 버튼 클릭 리스너 설정
        btnBack.setOnClickListener {
            // 메인 화면으로 이동
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnPrev.setOnClickListener {
            // 이전 화면으로 이동 (ProfileOption3Activity)
            val intent = Intent(this, ProfileOption3Activity::class.java)
            startActivity(intent)
            finish()
        }

        btnFinish.setOnClickListener {
            // 프로필 완성 버튼 클릭 시, 선택된 값을 가져와서 처리
            val cleaningSensitivity = getSelectedRadioButtonValue(cleaningSensitivityRadioGroup)
            val noiseSensitivity = getSelectedRadioButtonValue(noiseSensitivityRadioGroup)
            val sleepTime = getSelectedRadioButtonValue(firstSleepTimeRadioGroup, secondSleepTimeRadioGroup)
            val uptime = getSelectedRadioButtonValue(firstUptimeRadioGroup, secondUptimeRadioGroup)

            if (cleaningSensitivity == "선택되지 않음" ||
                noiseSensitivity == "선택되지 않음" ||
                sleepTime == "선택되지 않음" ||
                uptime == "선택되지 않음") {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
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
