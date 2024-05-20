package com.example.mytestapp.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.MainActivity
import com.example.mytestapp.databinding.ActivityProfileOption1Binding


class ProfileOption1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileOption1Binding // 변경된 부분

    // 변수 초기화
    private var activityValue = 0
    private var thinkValue = 0
    private var emotionValue = 0
    private var patternValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileOption1Binding.inflate(layoutInflater) // 변경된 부분
        setContentView(binding.root)

        // 외향형 / 내향형 버튼
        binding.activityHigh.setOnClickListener {
            activityValue = 1
        }
        binding.activityLow.setOnClickListener {
            activityValue = 0
        }

        // 감각형 / 직관형 버튼
        binding.thinkHigh.setOnClickListener {
            thinkValue = 1
        }
        binding.thinkLow.setOnClickListener {
            thinkValue = 0
        }

        // 감정형 / 사고형 버튼
        binding.emotionHigh.setOnClickListener {
            emotionValue = 1
        }
        binding.emotionLow.setOnClickListener {
            emotionValue = 0
        }

        // 판단형 / 인식형 버튼
        binding.patternHigh.setOnClickListener {
            patternValue = 1
        }
        binding.patternLow.setOnClickListener {
            patternValue = 0
        }

        // 다음으로 버튼 클릭 시 선택된 값 전달
        binding.btnNext.setOnClickListener {
            if (isValuesSelected()) {
                val intent = Intent(this, ProfileOption2Activity::class.java)
                intent.putExtra("activity", activityValue)
                intent.putExtra("think", thinkValue)
                intent.putExtra("emotion", emotionValue)
                intent.putExtra("pattern", patternValue)
                startActivity(intent)
                finish()
            } else {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 이전으로 버튼 클릭 시 MainActivity 로 이동 (첫 프로필 페이지니까)
        binding.btnPrev.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 메인으로 버튼 클릭 시 MainActivity 로 이동
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // 선택된 값이 모두 있는지 확인하는 함수
    private fun isValuesSelected(): Boolean {
        return activityValue != 0 && thinkValue != 0 && emotionValue != 0 && patternValue != 0
    }
}
