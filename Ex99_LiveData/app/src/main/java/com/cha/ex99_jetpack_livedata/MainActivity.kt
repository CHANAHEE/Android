package com.cha.ex99_jetpack_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.cha.ex99_jetpack_livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        //binding.vm = MyViewModel()

        // 6_ vm 참조변수를 이용해야 해서 밑에 처럼 분리해서 작성해주었음.
        val vm = MyViewModel()
        binding.vm = vm
        
        // 7_ 뷰모델 안에 있는 LiveData 변수를 관찰하는 설정
        // 8_ object 로 작성하는건 너무 기니까.. 축약형으로 쓰자. SAM 변환을 이용할수도 있다.
        // 파라미터가 하나일때는 바로 중괄호를 쓰면 되지만, 두개 이상일 떄는 아래와 같이 작성.
        // 9_ 이제 값을 변경해야한다. 여기서 값을 변경하기 위해서는 뷰바인딩을 사용해야함.
        // 10_ 이건 커스텀시에 사용하는 방법.. 일반적으로 LiveData 사용할때 이 방식으로 하진 않는다.
        vm.name.observe(this){
            Toast.makeText(this, "데이터 변경 감지 - $it", Toast.LENGTH_SHORT).show()
            binding.tv.text = it

        }
//        vm.name.observe(this, object : Observer<String>{
//            override fun onChanged(t: String?) {
//                
//            }
//        })

        // 12_ 자동 갱신 2번째 방법. 이 한줄로 끝. binding 객체의 lifecycleOwner 에게 Activity 를 넘겨주면 끝.
        binding.lifecycleOwner = this
    }
}