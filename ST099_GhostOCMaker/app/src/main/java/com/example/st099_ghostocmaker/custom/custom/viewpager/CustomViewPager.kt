import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import androidx.viewpager2.widget.ViewPager2

@SuppressLint("ClickableViewAccessibility")
class CustomViewPager(private val viewPager: ViewPager2, context: Context) {

    // 0: vuốt bình thường, 1: chặn vuốt sang phải, -1: chặn vuốt sang trái
    private var swipeEnabled = 0
    private var initialXValue = 0f

    fun setPagingEnabled(enabled: Int) {
        this.swipeEnabled = enabled
    }

    init {
        // Thêm OnTouchListener để xử lý vuốt
        viewPager.getChildAt(0).setOnTouchListener { _, event ->
            if (event != null) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        initialXValue = event.x
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val diffX = event.x - initialXValue
                        if ((swipeEnabled == 1 && diffX < 0) ||    // Chặn vuốt sang phải
                            (swipeEnabled == -1 && diffX > 0)) {   // Chặn vuốt sang trái
                            return@setOnTouchListener true
                        }
                    }
                }
            }
            false
        }
    }
}
