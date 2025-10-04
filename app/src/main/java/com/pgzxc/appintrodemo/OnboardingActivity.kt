package com.pgzxc.appintrodemo


import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.pgzxc.appintrodemo.view.CustomSlide

class OnboardingActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //AppIntro 支持在引导页中请求权限
//        askForPermissions(
//            permissions = arrayOf(Manifest.permission.CAMERA),
//            slideNumber = 2, // 在第 2 页请求权限
//            required = true // 如果拒绝权限，无法继续
//        )
        //滑动动画
        setTransformer(AppIntroPageTransformerType.Zoom)
        // 添加引导页
        addSlide(AppIntroFragment.newInstance(
            title = "欢迎使用",
            description = "这是我们的应用，带你开启新体验！",
            imageDrawable = R.drawable.ic_intro_1,
            backgroundColor = getColor(R.color.intro_background_1)
        ))

        addSlide(AppIntroFragment.newInstance(
            title = "探索功能",
            description = "发现更多有趣的内容！",
            imageDrawable = R.drawable.ic_intro_2,
            backgroundColor = getColor(R.color.intro_background_2)
        ))
        addSlide(CustomSlide()) //自定义页面

        // Slide 1: 全屏 Lottie 动画
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_lottie_intro))

        addSlide(AppIntroFragment.newInstance(
            title = "立即开始",
            description = "点击开始，进入主界面！",
            imageDrawable = R.drawable.ic_intro_3,
            backgroundColor = getColor(R.color.intro_background_3)
        ))
    }

    // 用户点击“完成”或“跳过”时的回调
    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finishOnboarding()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finishOnboarding()
    }

    private fun finishOnboarding() {
        // 保存引导页已显示的状态
        getSharedPreferences("app_prefs", MODE_PRIVATE)
            .edit()
            .putBoolean("onboarding_shown", true)
            .apply()

        // 跳转到主界面
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}