package com.example.mytestapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytestapp.databinding.FragmentRoomieBinding

class RoomieFragment : Fragment() {
    private var binding: FragmentRoomieBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // onViewCreated() 메서드에서 추가적인 초기화나 UI 관련 작업 수행 가능
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
