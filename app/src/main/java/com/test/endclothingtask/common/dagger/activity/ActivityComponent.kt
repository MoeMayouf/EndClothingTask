package com.test.endclothingtask.common.dagger.activity

import com.test.endclothingtask.common.dagger.session.SessionComponent
import com.test.endclothingtask.presentation.catalog.MainActivity
import dagger.Component

@Component(dependencies = [SessionComponent::class])
@ActivityScope
interface ActivityComponent : SessionComponent {
    fun inject(target: MainActivity)
}