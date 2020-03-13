package com.fearlessbear.uiexperimentproject.animation;

import com.fearlessbear.uiexperimentproject._base.BaseActivity;
import com.fearlessbear.uiexperimentproject.animation.object.TranslationAnimationActivity;
import com.fearlessbear.uiexperimentproject.animation.tween.TranslateAnimActivity;

public class AnimationListActivity extends BaseActivity {
    {
        mTitle = new String[]{
                "Tween Translate",
                "Object translation"
        };
        mClasses = new Class[]{
                TranslateAnimActivity.class,
                TranslationAnimationActivity.class
        };
    }
}
