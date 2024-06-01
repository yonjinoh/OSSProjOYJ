package com.example.mytestapp.sign

import com.example.mytestapp.entitiy.KiriServicePool
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.databinding.ActivityRegisterBinding
import com.example.mytestapp.model.request.signuprequest            //구현 필요
import com.example.mytestapp.model.response.signupresponse         //구현 필요
import retrofit2.Call           //구현 필요
import retrofit2.Callback       //구현 필요


class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val SignupService = KiriServicePool.signupService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.Name
        val iD = binding.ID
        val password = binding.pwd
        val password_check = binding.pwdCheck
        // 학번 변수 선언
        val studentID = binding.StudentID
        // 성별 라디오 그룹 변수 선언
        val genderGroup = binding.Gender

        //var check = false, 이전으로 돌아가는 버튼
        binding.btnBack.setOnClickListener {
            val intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
            finish()
        }
        binding.registerBtn.setOnClickListener {

            //이메일이 비어있을 때
            if (TextUtils.isEmpty(iD.text.toString()))
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()

            //이름이 비어있을 때
            else if (TextUtils.isEmpty(name.text.toString()))
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()

            // 추가
            // 학번이 비어있을 때
            else if (TextUtils.isEmpty(studentID.text.toString()))
                Toast.makeText(this, "학번을 입력해주세요", Toast.LENGTH_SHORT).show()

            //비밀번호가 같지 않을 때
            else if (!password.text.toString().equals(password_check.text.toString()))
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()

            //비밀번호 확인이 비어있을 때
            else if (TextUtils.isEmpty(password_check.text.toString()))
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()

            //비밀번호가 비어있을 때
            else if (TextUtils.isEmpty(password.text.toString()))
                Toast.makeText(this, "비밀번호 확인을 입력해주세요", Toast.LENGTH_SHORT).show()

            //추가
            // 성별이 선택되지 않았을 때
            else if (genderGroup.checkedRadioButtonId == -1)
                Toast.makeText(this, "성별을 선택해주세요", Toast.LENGTH_SHORT).show()

            else {
                // 선택된 성별 라디오 버튼의 텍스트 가져오기
                val gender = findViewById<RadioButton>(genderGroup.checkedRadioButtonId).text.toString()

                Log.d("signupbutton", "asdf")
                SignupService.signup(
                    signuprequest(
                        iD.text.toString(),
                        password.text.toString(),
                        name.text.toString(),
                        studentID.text.toString(),
                        gender
                    )
                ).enqueue(object : Callback<signupresponse> {
                    override fun onResponse(
                        call: Call<signupresponse>,
                        response: retrofit2.Response<signupresponse>
                    ) {

                        Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT)
                            .show()
                        finish()

                    }

                    override fun onFailure(call: Call<signupresponse>, t: Throwable) {
                        Toast.makeText(applicationContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        Log.d("signupfail", t.toString())
                    }

                })

            }
        }

    }
}