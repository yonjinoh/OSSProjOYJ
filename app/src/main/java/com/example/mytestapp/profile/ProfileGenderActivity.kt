package com.example.mytestapp.profile

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.mytestapp.R
import com.example.mytestapp.MainActivity
import com.example.mytestapp.databinding.ActivityProfileGenderBinding

class ProfileGenderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileGenderBinding

    // 변수 초기화
    private var genderValue = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileGenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 성별 선택 버튼 클릭 이벤트 처리
        binding.male.setOnClickListener {   // 남
            genderValue = 1
            binding.male.setBackgroundResource(R.drawable.option_bar_left_selected)
            binding.female.setBackgroundResource(R.drawable.option_selector_right)
        }
        binding.female.setOnClickListener { // 녀
            genderValue = 0
            binding.female.setBackgroundResource(R.drawable.option_bar_right_selected)
            binding.male.setBackgroundResource(R.drawable.option_selector_left)
        }

        // 다음으로 버튼 클릭 시 선택된 값 전달
        binding.btnNext.setOnClickListener {
            if (isValuesSelected()) {
                val intent = Intent(this, ProfileOption1Activity::class.java)
                intent.putExtra("gender", genderValue)
                startActivity(intent)
                finish()
            } else {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "성별을 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 이전으로 버튼 클릭 시 MainActivity로 이동 (프로필 첫 페이지니까)
        binding.btnPrev.setOnClickListener {
            val intent = Intent(this, ProfileGenderActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 메인으로 버튼 클릭 시 MainActivity로 이동
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // 선택된 값이 모두 있는지 확인하는 함수
    private fun isValuesSelected(): Boolean {
        return genderValue != -1
    }
}