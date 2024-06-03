package com.example.mytestapp.profile


import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.MainActivity
import com.example.mytestapp.R
import android.util.Log

import com.example.mytestapp.model.request.profilerequest
import com.example.mytestapp.model.response.profileresponse
import com.example.mytestapp.entitiy.KiriServicePool.ProfileService
import com.example.mytestapp.sign.LoginActivity


import retrofit2.Call
import retrofit2.Callback


var profileList: MutableList<Int> = mutableListOf()


class ProfileOption4Activity : AppCompatActivity() {

    private lateinit var cleanlinessRadioGroup: RadioGroup
    private lateinit var noiseSensitivityRadioGroup: RadioGroup
    private lateinit var firstSleepScheduleRadioGroup: RadioGroup
    private lateinit var secondSleepScheduleRadioGroup: RadioGroup
    private lateinit var firstUpScheduleRadioGroup: RadioGroup
    private lateinit var secondUpScheduleRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_option_4)

        // 라디오 그룹 초기화
        cleanlinessRadioGroup = findViewById(R.id.cleanliness_radio_group)
        noiseSensitivityRadioGroup = findViewById(R.id.noise_sensitivity_radio_group)
        firstSleepScheduleRadioGroup = findViewById(R.id.first_sleep_schedule_radio_group)
        secondSleepScheduleRadioGroup = findViewById(R.id.second_sleep_schedule_radio_group)
        firstUpScheduleRadioGroup = findViewById(R.id.first_up_schedule_radio_group)
        secondUpScheduleRadioGroup = findViewById(R.id.second_up_schedule_radio_group)

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
            val cleanliness = getSelectedCleanliness()
            val noiseSensitivity = getSelectedNoiseSensitivity()
            val sleepSchedule = getSelectedSleepSchedule()
            val upSchedule = getSelectedUpSchedule()

            if (cleanliness == -1 ||
                noiseSensitivity == -1 ||
                sleepSchedule == -1 ||
                upSchedule == -1) {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 프로필 생성이 완료되었음을 알리는 토스트 메시지 출력
                sendProfileData(cleanliness, noiseSensitivity, sleepSchedule, upSchedule)
            }
        }
    }

    // 청결에 대한 선택된 값을 반환하는 함수
    private fun getSelectedCleanliness(): Int {
        return when (cleanlinessRadioGroup.checkedRadioButtonId) {
            R.id.cleanliness_sensitive -> 0
            R.id.cleanliness_normal -> 1
            R.id.cleanliness_not_sensitive -> 2
            else -> -1 // 선택된 값이 없는 경우 -1을 반환
        }
    }

    // 소음 민감도에 대한 선택된 값을 반환하는 함수
    private fun getSelectedNoiseSensitivity(): Int {
        return when (noiseSensitivityRadioGroup.checkedRadioButtonId) {
            R.id.noise_sensitive -> 0
            R.id.noise_normal -> 1
            R.id.noise_not_sensitive -> 2
            else -> -1 // 선택된 값이 없는 경우 -1을 반환
        }
    }

    // 취침 시간에 대한 선택된 값을 반환하는 함수
    private fun getSelectedSleepSchedule(): Int {
        val firstGroupCheckedId = firstSleepScheduleRadioGroup.checkedRadioButtonId
        val secondGroupCheckedId = secondSleepScheduleRadioGroup.checkedRadioButtonId

        return when {
            firstGroupCheckedId != -1 -> { // 첫 번째 라디오 그룹에서 선택된 경우
                when (firstGroupCheckedId) {
                    R.id.sleep_early -> 0
                    R.id.sleep_ten -> 1
                    R.id.sleep_normal -> 2
                    else -> -1 // 선택된 값이 없는 경우 -1을 반환
                }
            }
            secondGroupCheckedId != -1 -> { // 두 번째 라디오 그룹에서 선택된 경우
                when (secondGroupCheckedId) {
                    R.id.sleep_late -> 3
                    R.id.sleep_very_late -> 4
                    else -> -1 // 선택된 값이 없는 경우 -1을 반환
                }
            }
            else -> -1 // 선택된 값이 없는 경우 -1을 반환
        }
    }

    // 기상 시간에 대한 선택된 값을 반환하는 함수
    private fun getSelectedUpSchedule(): Int {
        val firstGroupCheckedId = firstUpScheduleRadioGroup.checkedRadioButtonId
        val secondGroupCheckedId = secondUpScheduleRadioGroup.checkedRadioButtonId

        return when {
            firstGroupCheckedId != -1 -> { // 첫 번째 라디오 그룹에서 선택된 경우
                when (firstGroupCheckedId) {
                    R.id.up_early -> 0
                    R.id.up_seven -> 1
                    R.id.up_normal -> 2
                    else -> -1 // 선택된 값이 없는 경우 -1을 반환
                }
            }
            secondGroupCheckedId != -1 -> { // 두 번째 라디오 그룹에서 선택된 경우
                when (secondGroupCheckedId) {
                    R.id.up_late -> 3
                    R.id.up_very_late -> 4
                    else -> -1 // 선택된 값이 없는 경우 -1을 반환
                }
            }
            else -> -1 // 선택된 값이 없는 경우 -1을 반환
        }
    }

    private fun sendProfileData(cleanliness: Int, noiseSensitivity: Int, sleepSchedule: Int, upSchedule: Int) {
        val Embti = profileList[0]
        val Smbti = profileList[1]
        val Tmbti = profileList[2]
        val Jmbti = profileList[3]

        val grade = profileList[4]
        val smoke = profileList[5]
        val firstLesson = profileList[6]
        val sleepHabit = profileList[7]
        val shareNeeds = profileList[8]

        val inComm = profileList[9]
        val heatSens = profileList[10]
        val coldSens = profileList[11]
        val drinkFreq = profileList[12]

        //        val intent = intent
//
//        // 액티비티 1, 2, 3에서 전달된 값을 받음
//        val Embti = intent.getIntExtra("m", -1)
//        val Smbti = intent.getIntExtra("b", -1)
//        val Tmbti = intent.getIntExtra("t", -1)
//        val Jmbti = intent.getIntExtra("i", -1)
//
//        val grade = intent.getIntExtra("grade", -1)
//        val smoke = intent.getIntExtra("smoking", -1)
//        val firstLesson = intent.getIntExtra("firstLesson", -1)
//        val sleepHabit = intent.getIntExtra("sleepingHabit", -1)
//        val shareNeeds = intent.getIntExtra("sharingDailyNeeds", -1)
//
//        val inComm = intent.getIntExtra("internalCommunication", -1)
//        val heatSens = intent.getIntExtra("heatSensitive", -1)
//        val coldSens = intent.getIntExtra("coldSensitive", -1)
//        val drinkFreq = intent.getIntExtra("drinkingFrequency", -1)

        val cleanliness = cleanliness
        val noiseSens = noiseSensitivity
        val sleepSche = sleepSchedule
        val upSche = upSchedule

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("UserID", null)

        if (userId == null) {
            Toast.makeText(this, "User ID를 찾을 수 없습니다. 다시 로그인 해주세요.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // 프로필 요청 객체 생성
        val profileRequest = profilerequest(
            userId, Embti, Smbti, Tmbti, Jmbti,
            firstLesson, smoke, sleepHabit, grade, shareNeeds,
            inComm, heatSens, coldSens, drinkFreq,
            cleanliness, noiseSens, sleepSche, upSche
        )

        // 프로필 데이터 서버로 전송
        ProfileService.profile(profileRequest).enqueue(object : Callback<profileresponse> {
            override fun onResponse(call: Call<profileresponse>, response: retrofit2.Response<profileresponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "프로필 생성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@ProfileOption4Activity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(applicationContext, "프로필 생성 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<profileresponse>, t: Throwable) {
                Toast.makeText(applicationContext, "프로필 생성 실패", Toast.LENGTH_SHORT).show()
                Log.d("ProfileCreationFail", t.toString())
            }
        })
    }
}