package com.android04.godfisherman.ui.stopwatch

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.android04.godfisherman.R
import com.android04.godfisherman.ui.main.MainActivity
import kotlin.math.roundToInt

class StopwatchInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_stopwatch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val motionLayout = view.findViewById<MotionLayout>(R.id.ml_stopwatch)
        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {

            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
                Log.d("TAG", "onTransitionStarted: start!!!")
                motionLayout?.apply {
                    layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
                    requestLayout()
                }
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                if (currentId == R.id.end) {
                    Log.d("TAG", "onTransitionCompleted: Yes it is a end ${dpToPx((56 + 80 + 10).toFloat())}")
                    motionLayout?.apply {
                        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 450)
                        params.gravity = Gravity.BOTTOM
                        layoutParams = params
                        requestLayout()
                    }
                }
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}
        })

        view.setOnTouchListener { v,event ->
            Log.d("TAG", "onViewCreated: StopwatchFragment")
            true
        }
    }

    private fun dpToPx(dp: Float): Int{
        val density = requireContext().resources.displayMetrics.density
        return (dp * density).roundToInt()
    }
}
