package com.example.mytestapp.match

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.R
import com.example.mytestapp.MainActivity
import com.example.mytestapp.databinding.ActivityMatchingOption1Binding

class MatchingOption1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMatchingOption1Binding
    private var activityValue = 0
    private var smokingValue: Int = -1
    private var firstClassValue: Int = -1
    private var sleepingHabitValue: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchingOption1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 메인으로 버튼 클릭 시 MainActivity로 이동
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 이전 버튼 클릭 시 MainActivity로 이동
        binding.btnPrev.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // 다음 버튼 클릭 시 선택한 값을 확인하고 처리
        binding.btnNext.setOnClickListener {
            if (isValuesSelected()) {
                // 선택한 값들을 다음 액티비티로 전달하고 해당 액티비티로 이동
                val intent = Intent(this, MatchingOption2Activity::class.java)
                intent.putExtra("activity", activityValue)
                intent.putExtra("smoking", smokingValue)
                intent.putExtra("firstClass", firstClassValue)
                intent.putExtra("sleepingHabit", sleepingHabitValue)
                startActivity(intent)
                finish()
            } else {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 외향형 / 내향형 버튼 클릭 리스너
        binding.activityHigh.setOnClickListener {
            activityValue = 1
            binding.activityHigh.setBackgroundResource(R.drawable.option_bar_left_selected)
            binding.activityLow.setBackgroundResource(R.drawable.option_selector_right)
        }

        binding.activityLow.setOnClickListener {
            activityValue = 0
            binding.activityLow.setBackgroundResource(R.drawable.option_bar_right_selected)
            binding.activityHigh.setBackgroundResource(R.drawable.option_selector_left)
        }

        // 각 라디오 버튼 그룹에 대한 리스너 설정 및 선택된 값 저장
        binding.smokingRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            smokingValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }

        binding.firstclassRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            firstClassValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }

        binding.sleepinghabitRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            sleepingHabitValue = findViewById<RadioButton>(checkedId).text.toString().toInt()
        }
    }

    private fun isValuesSelected(): Boolean {
        // 모든 값이 선택되었는지 확인하는 함수
        return (activityValue != 0 && smokingValue != -1 && firstClassValue != -1 && sleepingHabitValue != -1)
    }
}
