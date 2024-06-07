package com.example.mytestapp.match
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mytestapp.R
class MatchingING : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_ing)
        // 1.5초 후에 MatchingFragment로 전환
        Handler(Looper.getMainLooper()).postDelayed({
            showMatchingFragment() // 1.5초 후 showMatchingFragment() 호출
        }, 1500)
    }
    // 기존 레이아웃을 숨기고 프래그먼트를 표시하는 함수
    private fun showMatchingFragment() {
        // 기존 레이아웃 숨기기
        findViewById<ImageView>(R.id.imageView).visibility = View.GONE
        // 프래그먼트 컨테이너 표시
        findViewById<FrameLayout>(R.id.fragment_container).visibility = View.VISIBLE
        replaceFragment(MatchingFragment()) // MatchingFragment를 프래그먼트 컨테이너에 교체
    }
    // 프래그먼트를 교체하는 함수
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment) // fragment_container에 fragment 교체
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}