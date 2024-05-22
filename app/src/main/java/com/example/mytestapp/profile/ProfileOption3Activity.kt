package com.example.mytestapp.profile

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.MainActivity
import com.example.mytestapp.databinding.ActivityProfileOption3Binding

class ProfileOption3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileOption3Binding
    // 변수 설정
    private var callingValue: Int = -1
    private var hotTempValue: Int = -1
    private var coldTempValue: Int = -1
    private var drinkingFrequencyValue: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileOption3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이전 버튼 클릭 시 ProfileOption2Activity로 이동
        binding.btnPrev.setOnClickListener {
            startActivity(Intent(this, ProfileOption2Activity::class.java))
            finish()
        }

        // 메인으로 버튼 클릭 시 MainActivity로 이동
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 다음 버튼 클릭 시 선택한 값을 확인하고 처리
        binding.btnNext.setOnClickListener {
            if (isValuesSelected()) {
                // 선택한 값들을 다음 액티비티로 전달하고 해당 액티비티로 이동
                val intent = Intent(this, ProfileOption4Activity::class.java)
                intent.putExtra("calling", callingValue)
                intent.putExtra("hotTemp", hotTempValue)
                intent.putExtra("coldTemp", coldTempValue)
                intent.putExtra("drinkingFrequency", drinkingFrequencyValue)
                startActivity(intent)
                finish()
            } else {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 각 라디오 버튼 그룹에 대한 리스너 설정 및 선택된 값 저장
        binding.callingRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            callingValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }

        binding.hottempRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            hotTempValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }

        binding.coldtempRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            coldTempValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }

        binding.drinkingFrequencyRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            drinkingFrequencyValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }
    }

    private fun isValuesSelected(): Boolean {
        // 모든 값이 선택되었는지 확인을 위한 함수
        return (callingValue != -1 && hotTempValue != -1 && coldTempValue != -1 && drinkingFrequencyValue != -1)
    }
}
