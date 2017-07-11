package com.tailoredapps.template.ui.main

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.tailoredapps.template.BR
import com.tailoredapps.template.injection.scopes.PerActivity
import com.tailoredapps.template.ui.base.viewmodel.PartialState
import com.tailoredapps.template.ui.base.viewmodel.StateReducer
import com.tailoredapps.template.util.kotlin.NotifyPropertyChangedDelegate
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import javax.inject.Inject

/* Copyright 2017 Tailored Media GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

@PaperParcel
class MainState : BaseObservable(), PaperParcelable {

    @get:Bindable @delegate:Transient
    var count : Int by NotifyPropertyChangedDelegate<Int>(0, BR.count)

    @get:Bindable @delegate:Transient
    var resetCount : Int by NotifyPropertyChangedDelegate<Int>(0, BR.resetCount)

    companion object {
        @JvmField val CREATOR = PaperParcelMainState.CREATOR
    }
}


sealed class MainPartialState : PartialState<MainState> {
    object Increment : MainPartialState() {
        override fun reduce(state: MainState): MainState = state.apply { count++ }
    }

    object Reset : MainPartialState() {
        override fun reduce(state: MainState): MainState = state.apply { count = 0; resetCount++ }
    }
}


@PerActivity
class MainStateReducer
@Inject
constructor(): StateReducer<MainState, MainPartialState>()