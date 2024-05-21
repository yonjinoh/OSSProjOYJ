package com.example.mytestapp.match

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.MainActivity
import com.example.mytestapp.R

class MatchingOption4Activity : AppCompatActivity() {

    private lateinit var firstUptimeRadioGroup: RadioGroup
    private lateinit var secondUptimeRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_option_4)

        // 라디오 그룹 초기화
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
            // 이전 화면으로 이동 (MatchingOption3Activity)
            val intent = Intent(this, MatchingOption3Activity::class.java)
            startActivity(intent)
            finish()
        }

        btnFinish.setOnClickListener {
            // 프로필 완성 버튼 클릭 시, 선택된 값을 가져와서 처리
            val uptime = getSelectedRadioButtonValue(firstUptimeRadioGroup, secondUptimeRadioGroup)

            if (uptime == "선택되지 않음") {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 선택한 값들을 다음 액티비티로 전달하고 해당 액티비티로 이동
                val intent = Intent(this, MatchingOptionTotalActivity::class.java)
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
