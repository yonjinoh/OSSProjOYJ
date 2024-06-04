package com.example.mytestapp.match

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.MainActivity
import com.example.mytestapp.R
import com.example.mytestapp.entitiy.KiriServicePool
import com.example.mytestapp.entitiy.KiriServicePool.RoommateService
import com.example.mytestapp.entitiy.KiriServicePool.AlgorithmService
import com.example.mytestapp.model.request.Algorequest
import com.example.mytestapp.model.response.Algoresponse

import com.example.mytestapp.model.request.roommaterequest
import com.example.mytestapp.model.response.MatchResponse
import com.example.mytestapp.model.response.profileresponse
import com.example.mytestapp.model.response.roommateresponse
import com.example.mytestapp.profile.profileList
import com.example.mytestapp.sign.LoginActivity
import retrofit2.Call
import retrofit2.Callback

var preflist : MutableList<Int> = mutableListOf()

class MatchingOption4Activity : AppCompatActivity() {

    private lateinit var firstYUpscheduleRadioGroup: RadioGroup
    private lateinit var secondYUpscheduleRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_option_4)

        // 라디오 그룹 초기화
        firstYUpscheduleRadioGroup = findViewById(R.id.first_Y_up_schedule_radio_group)
        secondYUpscheduleRadioGroup = findViewById(R.id.second_Y_up_schedule_radio_group)

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
            // 매칭 조건 입력 완성 버튼 클릭 시, 선택된 값을 가져와서 처리
            val upSchedule = getSelectedUpSchedule()

            if (upSchedule == -1) {
                // 필수 정보를 선택하지 않았을 경우에 대한 처리
                Toast.makeText(this, "모든 선택지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                sendPrefdata(upSchedule)
            }
        }
    }

    private fun getSelectedUpSchedule(): Int {
        val firstGroupCheckedId = firstYUpscheduleRadioGroup.checkedRadioButtonId
        val secondGroupCheckedId = secondYUpscheduleRadioGroup.checkedRadioButtonId

        return when {
            firstGroupCheckedId != -1 -> { // 첫 번째 라디오 그룹에서 선택된 경우
                when (firstGroupCheckedId) {
                    R.id.Y_up_early -> 0
                    R.id.Y_up_seven -> 1
                    R.id.Y_up_normal -> 2
                    else -> -1 // 선택된 값이 없는 경우 -1을 반환
                }
            }
            secondGroupCheckedId != -1 -> { // 두 번째 라디오 그룹에서 선택된 경우
                when (secondGroupCheckedId) {
                    R.id.Y_up_late -> 3
                    R.id.Y_up_very_late -> 4
                    else -> -1 // 선택된 값이 없는 경우 -1을 반환
                }
            }
            else -> -1 // 선택된 값이 없는 경우 -1을 반환
        }
    }
    private fun sendPrefdata(upSchedule: Int) {
        val UEmbti = preflist[0]
        val UfirstLesson = preflist[1]
        val Usmoke = preflist[2]
        val UsleepHabit = preflist[3]
        val Ugrade = preflist[4]
        val UshareNeeds = preflist[5]
        val UinComm = preflist[6]
        val UheatSens = preflist[7]
        val UcoldSens = preflist[8]
        val UdrinkFreq = preflist[9]
        val Ucleanliness = preflist[10]
        val UnoiseSens = preflist[11]
        val UsleepSche = preflist[12]

        val UupSche = upSchedule

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val UuserId = sharedPreferences.getString("UserID", null)

        if (UuserId == null) {
            Toast.makeText(this, "User ID를 찾을 수 없습니다. 다시 로그인 해주세요.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // 프로필 요청 객체 생성
        val prefRequest = roommaterequest(
            UuserId, UEmbti,
            UfirstLesson, Usmoke, UsleepHabit, Ugrade, UshareNeeds,
            UinComm, UheatSens, UcoldSens, UdrinkFreq,
            Ucleanliness, UnoiseSens, UsleepSche, UupSche
        )

        val userId = UuserId

        val AlgoRequest = Algorequest(
            userId = userId
        )

        // 프로필 데이터 서버로 전송
        RoommateService.roommate(prefRequest).enqueue(object :
            Callback<roommateresponse> {
            override fun onResponse(call: Call<roommateresponse>, response: retrofit2.Response<roommateresponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "매칭을 시작합니다.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MatchingOption4Activity, MatchingING::class.java))


                    AlgorithmService.algoOPS(AlgoRequest).enqueue(object : Callback<Algoresponse> {
                        override fun onResponse(call: Call<Algoresponse>, response: retrofit2.Response<Algoresponse>) {
                            if (response.isSuccessful) {
                                Toast.makeText(applicationContext, "매칭이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                Toast.makeText(applicationContext, "매칭 실패", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<Algoresponse>, t: Throwable) {
                            Toast.makeText(applicationContext, "매칭 실패", Toast.LENGTH_SHORT).show()
                        }
                    })
                    finish()
                }



                else {
                    Toast.makeText(applicationContext, "매칭 시작 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<roommateresponse>, t: Throwable) {
                Toast.makeText(applicationContext, "매칭 시작 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }

}






