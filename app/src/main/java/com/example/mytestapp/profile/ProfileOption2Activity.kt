package com.example.mytestapp.profile

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.MainActivity
import com.example.mytestapp.databinding.ActivityProfileOption2Binding

class ProfileOption2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileOption2Binding
    //선택된 값이 없음을 나타내기 위해 초기값으로 -1을 설정
    private var smokingValue: Int = -1
    private var firstClassValue: Int = -1
    private var sleepingHabitValue: Int = -1
    private var sharingValue: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileOption2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPrev.setOnClickListener {
            // 이전 버튼 클릭 시 ProfileOption1으로 이동
            startActivity(Intent(this, ProfileOption1Activity::class.java))
            finish()
        }

        binding.btnBack.setOnClickListener {
            // 메인으로 버튼 클릭 시 MainActivity 로 이동
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnNext.setOnClickListener {
            // 다음 버튼 클릭 시 선택한 값을 확인하고 처리
            if (isValuesSelected()) {
                // 선택한 값들을 다음 액티비티로 전달하고 해당 액티비티로 이동
                val intent = Intent(this, ProfileOption3Activity::class.java)
                intent.putExtra("smoking", smokingValue)
                intent.putExtra("firstClass", firstClassValue)
                intent.putExtra("sleepingHabit", sleepingHabitValue)
                intent.putExtra("sharing", sharingValue)
                startActivity(intent)
                finish()
            } else {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 각 라디오 그룹에서 선택한 값을 변수에 저장
        binding.smokingRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            smokingValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }

        binding.firstclassRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            firstClassValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }

        binding.sleepinghabitRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            sleepingHabitValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }

        binding.sharingRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            sharingValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }
    }

    // 선택된 값이 모두 있는지 확인하는 함수
    private fun isValuesSelected(): Boolean {
        return smokingValue != -1 && firstClassValue != -1 && sleepingHabitValue != -1 && sharingValue != -1
    }
}
