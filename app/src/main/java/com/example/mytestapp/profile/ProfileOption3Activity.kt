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
import com.example.mytestapp.profile.ProfileOption4Activity

class ProfileOption3Activity : AppCompatActivity() {

    private lateinit var callingRadioGroup: RadioGroup
    private lateinit var hotTempRadioGroup: RadioGroup
    private lateinit var coldTempRadioGroup: RadioGroup
    private lateinit var drinkingFrequencyRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_option_3)

        // 라디오 버튼 그룹 초기화
        callingRadioGroup = findViewById(R.id.calling_radio_group)
        hotTempRadioGroup = findViewById(R.id.hottemp_radio_group)
        coldTempRadioGroup = findViewById(R.id.coldtemp_radio_group)
        drinkingFrequencyRadioGroup = findViewById(R.id.drinking_frequency_radio_group)

        // "이전으로" 버튼 클릭 시 ProfileOption2Activity로 이동
        findViewById<Button>(R.id.btn_prev).setOnClickListener {
            startActivity(Intent(this, ProfileOption2Activity::class.java))
            finish()
        }

        // "메인으로" 버튼 클릭 시 MainActivity로 이동
        findViewById<Button>(R.id.btn_back).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // "다음으로" 버튼 클릭 시 선택한 값 확인 후 처리
        findViewById<Button>(R.id.btn_next).setOnClickListener {
            if (isValuesSelected()) {
                // 선택한 값들을 다음 액티비티로 전달하고 해당 액티비티로 이동
                val intent = Intent(this, ProfileOption4Activity::class.java)
                // 각 라디오 버튼에서 선택한 값 전달
                intent.putExtra("calling", getSelectedRadioButtonValue(callingRadioGroup))
                intent.putExtra("hotTemp", getSelectedRadioButtonValue(hotTempRadioGroup))
                intent.putExtra("coldTemp", getSelectedRadioButtonValue(coldTempRadioGroup))
                intent.putExtra("drinkingFrequency", getSelectedRadioButtonValue(drinkingFrequencyRadioGroup))
                startActivity(intent)
                finish()
            } else {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 선택된 값이 모두 있는지 확인하는 함수
    private fun isValuesSelected(): Boolean {
        return (getSelectedRadioButtonValue(callingRadioGroup) != -1 &&
                getSelectedRadioButtonValue(hotTempRadioGroup) != -1 &&
                getSelectedRadioButtonValue(coldTempRadioGroup) != -1 &&
                getSelectedRadioButtonValue(drinkingFrequencyRadioGroup) != -1)
    }

    // 선택된 라디오 버튼의 값을 반환하는 함수
    private fun getSelectedRadioButtonValue(radioGroup: RadioGroup): Int {
        val checkedRadioButtonId = radioGroup.checkedRadioButtonId
        if (checkedRadioButtonId != -1) {
            val radioButton = findViewById<RadioButton>(checkedRadioButtonId)
            return radioButton.text.toString().toInt()
        }
        return -1 // 선택된 라디오 버튼이 없는 경우
    }
}
